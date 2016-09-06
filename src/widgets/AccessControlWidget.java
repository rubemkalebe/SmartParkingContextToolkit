package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

/**
 * Controlador de acesso ao estacionamento privado (cancela)
 *
 */
public class AccessControlWidget extends Widget {
	
	public static final String CLASSNAME = AccessControlWidget.class.getName();
	
	public static final String ID = "identifier"; // Identificador da cancela	
	public static final String VACANCY = "spot available"; // Tem vaga? (0/1)
	public static final String ACCESS_STATUS = "access_status"; // Libera cancela ou não? (0/1)
	public static final String NEW_VEHICLE = "new_vehicle"; // Quando chega um novo veículo (0/1)
	
	// Constantes para a cancela
	public static final Boolean ACCESS_OK = true;
	public static final Boolean ACCESS_BLOCK = false;
	
	// Identificador da cancela
	private String gateID;

	public AccessControlWidget(String id) {
		super(CLASSNAME, CLASSNAME);
		this.gateID = id;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(VACANCY, Boolean.class));
		addAttribute(Attribute.instance(ACCESS_STATUS, Boolean.class));
		addAttribute(Attribute.instance(NEW_VEHICLE, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(ID, gateID), true);
	}
}
