package widgets.driver;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import enums.TypeEnum;

/**
 * Motorista
 * Possui 3 atributos estáticos: id, tipo do motorista e permissão (este poderia não ser estático, depende 
 * da lógica a ser usada e implementada).
 *
 */
public class DriverWidget extends Widget {
	
	public static final String CLASSNAME = DriverWidget.class.getName();
	
	// Identificador do motorista (CPF/CNH)
	public static final String DRIVER_ID = "driver_id";
	private String driverID;
	
	// Tipo de motorista (normal, idoso, deficiente)
	public static final String TYPE = "type";
	private TypeEnum type;
	
	// Identifica se ele tem permissão ou não (0/1)
	public static final String PERMISSION = "permission";
	private Boolean permission;
	
	// Vaga em que ele está estacionado
	public static final String CURRENT_SPOT = "current_spot";	

	public DriverWidget(String driverID, TypeEnum type, Boolean permission) {
		super(CLASSNAME, CLASSNAME);
		this.driverID = driverID;
		this.type = type;
		this.permission = permission;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(CURRENT_SPOT, String.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(DRIVER_ID, driverID), true);
		addAttribute(AttributeNameValue.instance(TYPE, type), true);
		addAttribute(AttributeNameValue.instance(PERMISSION, permission), true);
	}	
	
}
