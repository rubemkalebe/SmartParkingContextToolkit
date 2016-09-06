package enactors;

import context.arch.discoverer.ComponentDescription;
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
import widgets.SpotWidget;

/**
 * Enactor para quando o motorista tirar o carro da vaga
 * Precisa notificar o motorista
 *
 */
public class LeavingSpotEnactor extends Enactor {
	
	public LeavingSpotEnactor(AbstractQueryItem<?,?> inWidgetQuery, AbstractQueryItem<?,?> outWidgetQuery) {
		this(new AbstractQueryItem<?,?>[] {inWidgetQuery}, new AbstractQueryItem<?,?>[] {outWidgetQuery});
	}

	@SuppressWarnings("serial")
	public LeavingSpotEnactor(AbstractQueryItem<?, ?>[] inWidgetQuery,
			AbstractQueryItem<?, ?>[] outWidgetQuery) {
		super(inWidgetQuery, outWidgetQuery, "LeavingSpot", "");
		
		// Notifica se o carro sair da vaga
		AbstractQueryItem<?, ?> leavingQI = new ANDQueryItem(
			RuleQueryItem.instance(
					new NonConstantAttributeElement(AttributeNameValue.instance(SpotWidget.SENSOR, false)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
			),
			RuleQueryItem.instance(
					new NonConstantAttributeElement(AttributeNameValue.instance(SpotWidget.DRIVER, "")),
					new AttributeComparison(AttributeComparison.Comparison.DIFFERENT)
			)
		);
		
		EnactorReference er = new LeavingSpotEnactorReference(
			leavingQI,
			SpotWidget.NOTIFY_ON // Ativa notificação
		);
		
		er.addServiceInput(new ServiceInput("LeavingSpotService", "LeavingSpotControl",
				new Attributes() {{
					addAttribute(SpotWidget.NOTIFY, Boolean.class);
				}}));
		addReference(er);
		
		er = new LeavingSpotEnactorReference(
			new ElseQueryItem(leavingQI),
			SpotWidget.NOTIFY_OFF // Não tem o que notificar
		);
		
		er.addServiceInput(new ServiceInput("LeavingSpotService", "LeavingSpotControl",
				new Attributes() {{
					addAttribute(SpotWidget.NOTIFY, Boolean.class);
				}}));
		addReference(er);
		
		start();
	}
	
	private class LeavingSpotEnactorReference extends EnactorReference {
		
		public LeavingSpotEnactorReference(AbstractQueryItem<?,?> conditionQuery, Boolean outcomeValue) {
			super(LeavingSpotEnactor.this, conditionQuery, outcomeValue.toString());
		}
		
		@Override
		protected Attributes conditionSatisfied(ComponentDescription inWidgetState, Attributes outAtts) {
			long timestamp = outAtts.getAttributeValue(Widget.TIMESTAMP);
			WidgetData data = new WidgetData(SpotWidget.CLASSNAME, timestamp);
			Boolean new_status;
			if(outcomeValue.equals(String.valueOf(SpotWidget.NOTIFY_ON))) {
				new_status = true;
			} else {
				new_status = false;
			}
			
			data.setAttributeValue(SpotWidget.NOTIFY, new_status);
			outAtts.putAll(data.toAttributes());
			
	        return outAtts;
		}
		
	}

}
