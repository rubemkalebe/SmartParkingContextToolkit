package enactors;

import context.arch.discoverer.component.NonConstantAttributeElement;
import context.arch.discoverer.query.AbstractQueryItem;
import context.arch.discoverer.query.ORQueryItem;
import context.arch.discoverer.query.RuleQueryItem;
import context.arch.discoverer.query.comparison.AttributeComparison;
import context.arch.enactor.Enactor;
import context.arch.storage.AttributeNameValue;
import enums.StatusEnum;
import widgets.SpotWidget;

public class LinkDriverEnactor extends Enactor {

	public LinkDriverEnactor(AbstractQueryItem<?,?> inWidgetQuery, AbstractQueryItem<?,?> outWidgetQuery) {
		this(new AbstractQueryItem<?,?>[] {inWidgetQuery}, new AbstractQueryItem<?,?>[] {outWidgetQuery});
	}

	public LinkDriverEnactor(AbstractQueryItem<?, ?>[] inWidgetQuery,
			AbstractQueryItem<?, ?>[] outWidgetQuery) {
		super(inWidgetQuery, outWidgetQuery, "LinkDriver", "");
		
		AbstractQueryItem<?, ?> occupiedQI = new ORQueryItem(
			RuleQueryItem.instance(
					new NonConstantAttributeElement(AttributeNameValue.instance(SpotWidget.STATUS, StatusEnum.WAITING_AUTH)),
					new AttributeComparison(AttributeComparison.Comparison.EQUAL)
			)
		);
		
	}
	
}
