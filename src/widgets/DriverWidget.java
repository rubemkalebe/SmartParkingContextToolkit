package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import enums.TypeEnum;

public class DriverWidget extends Widget {
	
	public static final String CLASSNAME = DriverWidget.class.getName();
	public static final String ID = "identifier"; //leia-se CPF
	public static final String PERMISSION = "permission"; 
	public static final String CREDIT_CARD = "credit_card"; 
	public static final String CURRENT_SPOT = "current_spot"; 
	public static final String TYPE = "type";
	
	private String driverID;

	public DriverWidget(String id) {
		super(CLASSNAME,CLASSNAME);
		this.driverID = id;
		super.start(true);
	}
	
	@Override
	protected void init() {
		
		addAttribute(Attribute.instance(PERMISSION, Boolean.class));
		addAttribute(Attribute.instance(CREDIT_CARD, String.class));
		addAttribute(Attribute.instance(CURRENT_SPOT, String.class));
		addAttribute(Attribute.instance(TYPE, TypeEnum.class));	
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(ID, driverID), true);
	}

	
	
}
