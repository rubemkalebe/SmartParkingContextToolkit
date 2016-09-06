package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import enums.TypeEnum;

/**
 * Motorista
 *
 */
public class DriverWidget extends Widget {
	
	public static final String CLASSNAME = DriverWidget.class.getName();
	
	public static final String ID = "identifier"; // Leia-se CPF
	public static final String PERMISSION = "permission"; // Identifica se ele tem permissão ou não (0/1) 
	public static final String CREDIT_CARD = "credit_card"; // Cartão de credito
	public static final String CURRENT_SPOT = "current_spot"; // Vaga em que ele está estacionado
	public static final String TYPE = "type"; // Tipo de motorista (normal, idoso, deficiente)
	
	// Identificador do motorista (CPF)
	private String driverID;

	public DriverWidget(String id) {
		super(CLASSNAME, CLASSNAME);
		this.driverID = id;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(PERMISSION, Boolean.class));
		addAttribute(Attribute.instance(CREDIT_CARD, String.class));
		addAttribute(Attribute.instance(CURRENT_SPOT, String.class));
		addAttribute(Attribute.instance(TYPE, TypeEnum.class));	
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(ID, driverID), true);
	}

	
	
}
