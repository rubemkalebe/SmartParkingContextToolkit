package enactors;

import context.arch.discoverer.ComponentDescription;
import context.arch.discoverer.component.ConstantAttributeElement;
import context.arch.discoverer.component.NonConstantAttributeElement;
import context.arch.discoverer.query.ANDQueryItem;
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
import enums.TypeEnum;
import widgets.spot.AlarmWidget;
import widgets.spot.SensorWidget;
import widgets.spot.SpotWidget;

/**
 * Enactor para alarme, que contrange o motorista a fazer a autenticação em vagas especiais
 *
 */
public class AlarmEnactor extends Enactor {
	
	public AlarmEnactor(AbstractQueryItem<?,?> inWidgetQuery, AbstractQueryItem<?,?> outWidgetQuery) {
		this(new AbstractQueryItem<?,?>[] {inWidgetQuery}, new AbstractQueryItem<?,?>[] {outWidgetQuery});
	}

	@SuppressWarnings("serial")
	public AlarmEnactor(AbstractQueryItem<?, ?>[] inWidgetQuery,
			AbstractQueryItem<?, ?>[] outWidgetQuery) {
		super(inWidgetQuery, outWidgetQuery, "Alarm", "");
		
		// alarm goes on when (SENSOR == true && DRIVER == "") && (ELDERLY || HANDICAPPED || RESERVED)
		AbstractQueryItem<?, ?> alarmQI = new ANDQueryItem(
			new ANDQueryItem(
				RuleQueryItem.instance(
					new NonConstantAttributeElement(AttributeNameValue.instance(SensorWidget.SENSOR,
							SensorWidget.OCCUPIED)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
				),
				RuleQueryItem.instance(
					new NonConstantAttributeElement(AttributeNameValue.instance(SpotWidget.DRIVER, "")),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
				)
			),
			new ORQueryItem(
				RuleQueryItem.instance(
					new ConstantAttributeElement(AttributeNameValue.instance(SpotWidget.TYPE, TypeEnum.ELDERLY)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
				),
				RuleQueryItem.instance(
					new ConstantAttributeElement(AttributeNameValue.instance(SpotWidget.TYPE, TypeEnum.HANDICAPPED)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
				),
				RuleQueryItem.instance(
					new ConstantAttributeElement(AttributeNameValue.instance(SpotWidget.TYPE, TypeEnum.RESERVED)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
				)
			)
		);
		
		EnactorReference er = new AlarmEnactorReference(
			alarmQI,
			AlarmWidget.ALARM_ON
		);
		
		er.addServiceInput(new ServiceInput("AlarmService", "AlarmControl",
				new Attributes() {{
					addAttribute(AlarmWidget.ALARM, Boolean.class);
				}}));
		addReference(er);
		
		er = new AlarmEnactorReference(
			new ElseQueryItem(alarmQI),
			AlarmWidget.ALARM_OFF
		);
		
		er.addServiceInput(new ServiceInput("AlarmService", "AlarmControl",
				new Attributes() {{
					addAttribute(AlarmWidget.ALARM, Boolean.class);
				}}));
		addReference(er);
		
		start();
	}
	
	private class AlarmEnactorReference extends EnactorReference {
		
		public AlarmEnactorReference(AbstractQueryItem<?,?> conditionQuery, Boolean outcomeValue) {
			super(AlarmEnactor.this, conditionQuery, outcomeValue.toString());
		}
		
		@Override
		protected Attributes conditionSatisfied(ComponentDescription inWidgetState, Attributes outAtts) {
			long timestamp = outAtts.getAttributeValue(Widget.TIMESTAMP);
			WidgetData data = new WidgetData(AlarmWidget.CLASSNAME, timestamp);
			boolean new_alarm;
			if(outcomeValue.equals(String.valueOf(AlarmWidget.ALARM_ON))) {
				new_alarm = true;
			} else {
				new_alarm = false;
			}
			
			data.setAttributeValue(AlarmWidget.ALARM, new_alarm);
			outAtts.putAll(data.toAttributes());
			
	        return outAtts;
		}
		
	}

}
