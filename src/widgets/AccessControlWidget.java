package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import enums.TypeEnum;

public class AccessControlWidget extends Widget {
	
	public static final String CLASSNAME = AccessControlWidget.class.getName();
	public static final String ID = "identifier";	
	public static final String VACANCY = "spot available";
	
	private String gateID;

	public AccessControlWidget(String id) {
		super(CLASSNAME,CLASSNAME);
		this.gateID = id;
		super.start(true);
	}
	
	@Override
	protected void init() {
		
		addAttribute(Attribute.instance(VACANCY, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(ID, gateID), true);
	}
}
