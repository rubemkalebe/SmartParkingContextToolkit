package enactors;

import context.arch.discoverer.ComponentDescription;
import context.arch.discoverer.component.ConstantAttributeElement;
import context.arch.discoverer.component.NonConstantAttributeElement;
import context.arch.discoverer.query.ANDQueryItem;
import context.arch.discoverer.query.AbstractQueryItem;
import context.arch.discoverer.query.ElseQueryItem;
import context.arch.discoverer.query.RuleQueryItem;
import context.arch.discoverer.query.comparison.AttributeComparison;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorReference;
import context.arch.service.helper.ServiceInput;
import context.arch.storage.AttributeNameValue;
import context.arch.storage.Attributes;
import context.arch.widget.Widget;
import context.arch.widget.Widget.WidgetData;
import widgets.driver.DriverWidget;
import widgets.parking.AccessControlWidget;
import widgets.parking.NewVehicleWidget;
import widgets.parking.ParkingWidget;

/**
 * Enactor para quando um novo veículo chegar ao estacionamento.
 *
 */
public class NewVehicleEnactor extends Enactor {
	
	public NewVehicleEnactor(AbstractQueryItem<?,?> inWidgetQuery, AbstractQueryItem<?,?> outWidgetQuery) {
		this(new AbstractQueryItem<?,?>[] {inWidgetQuery}, new AbstractQueryItem<?,?>[] {outWidgetQuery});
	}

	@SuppressWarnings("serial")
	public NewVehicleEnactor(AbstractQueryItem<?, ?>[] inWidgetQuery,
			AbstractQueryItem<?, ?>[] outWidgetQuery) {
		super(inWidgetQuery, outWidgetQuery, AccessControlWidget.ACCESS_STATUS, "");
		
		// Quando chega um novo veículo, o acesso dele é liberado se o estacionamento tiver vagas e ele tiver permissão
		AbstractQueryItem<?, ?> accessQI = new ANDQueryItem(
			RuleQueryItem.instance(
					new NonConstantAttributeElement(AttributeNameValue.instance(NewVehicleWidget.NEW_VEHICLE,
							NewVehicleWidget.NEW_VEHICLE_YES)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
			),
			RuleQueryItem.instance(
					new ConstantAttributeElement(AttributeNameValue.instance(DriverWidget.PERMISSION,
							DriverWidget.PERMISSION_YES)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
			),
			RuleQueryItem.instance(
					new NonConstantAttributeElement(AttributeNameValue.instance(ParkingWidget.VACANCY,
							ParkingWidget.VACANCY_YES)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
			)
		);
		
		EnactorReference er = new NewVehicleEnactorReference(
			accessQI,
			AccessControlWidget.ACCESS_OK // Libera cancela
		);
		
		er.addServiceInput(new ServiceInput("NewVehicleService", "NewVehicleControl",
				new Attributes() {{
					addAttribute(AccessControlWidget.ACCESS_STATUS, Boolean.class);
				}}));
		addReference(er);
		
		er = new NewVehicleEnactorReference(
			new ElseQueryItem(accessQI),
			AccessControlWidget.ACCESS_BLOCK // Não libera
		);
		
		er.addServiceInput(new ServiceInput("NewVehicleService", "NewVehicleControl",
				new Attributes() {{
					addAttribute(AccessControlWidget.ACCESS_STATUS, Boolean.class);
				}}));
		addReference(er);
		
		start();
		
	}
	
	private class NewVehicleEnactorReference extends EnactorReference {
		
		public NewVehicleEnactorReference(AbstractQueryItem<?,?> conditionQuery, Boolean outcomeValue) {
			super(NewVehicleEnactor.this, conditionQuery, outcomeValue.toString());
		}
		
		@Override
		protected Attributes conditionSatisfied(ComponentDescription inWidgetState, Attributes outAtts) {
			long timestamp = outAtts.getAttributeValue(Widget.TIMESTAMP);
			WidgetData data = new WidgetData(AccessControlWidget.CLASSNAME, timestamp);
			boolean access_status;
			if(outcomeValue.equals(String.valueOf(AccessControlWidget.ACCESS_OK))) {
				access_status = true;
			} else {
				access_status = false;
			}
			
			data.setAttributeValue(AccessControlWidget.ACCESS_STATUS, access_status);
			outAtts.putAll(data.toAttributes());
			
	        return outAtts;
		}
		
	}

}
