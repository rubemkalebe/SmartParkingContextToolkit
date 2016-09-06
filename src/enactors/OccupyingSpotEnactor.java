package enactors;

import context.arch.discoverer.ComponentDescription;
import context.arch.discoverer.component.NonConstantAttributeElement;
import context.arch.discoverer.query.AbstractQueryItem;
import context.arch.discoverer.query.ElseQueryItem;
import context.arch.discoverer.query.ORQueryItem;
import context.arch.discoverer.query.RuleQueryItem;
import context.arch.discoverer.query.comparison.AttributeComparison;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorReference;
import context.arch.service.helper.ServiceInput;
import context.arch.storage.AttributeNameValue;
import context.arch.storage.Attributes;
import context.arch.widget.Widget;
import context.arch.widget.Widget.WidgetData;
import enums.StatusEnum;
import widgets.SpotWidget;

/**
 * Enactor para quando o carro estacionar em uma vaga
 *
 */
public class OccupyingSpotEnactor extends Enactor {

	public static final String SPOT_STATUS = "spot_status";
	
	public OccupyingSpotEnactor(AbstractQueryItem<?,?> inWidgetQuery, AbstractQueryItem<?,?> outWidgetQuery) {
		this(new AbstractQueryItem<?,?>[] {inWidgetQuery}, new AbstractQueryItem<?,?>[] {outWidgetQuery});
	}

	@SuppressWarnings("serial")
	public OccupyingSpotEnactor(AbstractQueryItem<?, ?>[] inWidgetQuery,
			AbstractQueryItem<?, ?>[] outWidgetQuery) {
		super(inWidgetQuery, outWidgetQuery, "OccupyingSpot", "");
		
		// Checa se o sensor identificou o carro
		AbstractQueryItem<?, ?> occupiedQI = new ORQueryItem(
			RuleQueryItem.instance(
					new NonConstantAttributeElement(AttributeNameValue.instance(SpotWidget.SENSOR, true)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
			)
		);
		
		EnactorReference er = new OccupyingSpotEnactorReference(
			occupiedQI,
			StatusEnum.OCCUPIED // Muda status para ocupada
		);
		
		er.addServiceInput(new ServiceInput("OccupyingSpotService", "OccupyingSpotControl",
				new Attributes() {{
					addAttribute(SPOT_STATUS, StatusEnum.class);
				}}));
		addReference(er);
		
		er = new OccupyingSpotEnactorReference(
			new ElseQueryItem(occupiedQI),
			StatusEnum.FREE // Caso contrário a vaga está livre
		);
		
		er.addServiceInput(new ServiceInput("OccupyingSpotService", "OccupyingSpotControl",
				new Attributes() {{
					addAttribute(SPOT_STATUS, StatusEnum.class);
				}}));
		addReference(er);
		
		start();
	}
	
	private class OccupyingSpotEnactorReference extends EnactorReference {
		
		public OccupyingSpotEnactorReference(AbstractQueryItem<?,?> conditionQuery, StatusEnum outcomeValue) {
			super(OccupyingSpotEnactor.this, conditionQuery, outcomeValue.toString());
		}
		
		@Override
		protected Attributes conditionSatisfied(ComponentDescription inWidgetState, Attributes outAtts) {
			long timestamp = outAtts.getAttributeValue(Widget.TIMESTAMP);
			WidgetData data = new WidgetData(SpotWidget.CLASSNAME, timestamp);
			StatusEnum new_status;
			if(outcomeValue.equals(String.valueOf(StatusEnum.OCCUPIED))) {
				new_status = StatusEnum.OCCUPIED;
			} else {
				new_status = StatusEnum.FREE;
			}
			
			data.setAttributeValue(SPOT_STATUS, new_status);
			outAtts.putAll(data.toAttributes());
			
	        return outAtts;
		}
		
	}
	
}
