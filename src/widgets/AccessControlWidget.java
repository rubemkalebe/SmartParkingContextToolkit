package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

public class AccessControlWidget extends Widget {
	
	public static final String CLASSNAME = AccessControlWidget.class.getName();
	public static final String ID = "identifier";	
	public static final String VACANCY = "spot available";
	public static final String ACCESS_STATUS = "access_status";
	public static final String NEW_VEHICLE = "new_vehicle";
	
	public static final Boolean ACCESS_OK = true;
	public static final Boolean ACCESS_BLOCK = false;
	
	private String gateID;

	public AccessControlWidget(String id) {
		super(CLASSNAME,CLASSNAME);
		this.gateID = id;
		super.start(true);
	}
	
	@Override
	protected void init() {
		
		addAttribute(Attribute.instance(VACANCY, Boolean.class));
		addAttribute(Attribute.instance(ACCESS_STATUS, Boolean.class));
		addAttribute(Attribute.instance(NEW_VEHICLE, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(ID, gateID), true);
	}
}
