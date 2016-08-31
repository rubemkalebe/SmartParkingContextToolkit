package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import enums.StatusEnum;
import enums.TypeEnum;

public class SpotWidget extends Widget {
	
	public static final String CLASSNAME = SpotWidget.class.getName();
	public static final String ID = "identifier";
	public static final String STATUS = "status";
	public static final String SENSOR = "sensor";
	public static final String TYPE = "type";
	public static final String NOTIFIER = "notifier";
		
	private String spotID;

	public SpotWidget(String id) {
		super(CLASSNAME,CLASSNAME);
		this.spotID = id;
		super.start(true);
	}
	
	@Override
	protected void init() {
		
		addAttribute(Attribute.instance(STATUS, StatusEnum.class));
		addAttribute(Attribute.instance(SENSOR, Boolean.class));
		addAttribute(Attribute.instance(TYPE, TypeEnum.class));
		addAttribute(Attribute.instance(NOTIFIER, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(ID, spotID), true);
	}

	
	
}
