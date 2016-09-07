package widgets.parking;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

/**
 * Controlador de acesso ao estacionamento privado (cancela)
 * Tem dois atributos estaticos: identificador do estacionamento e o identificador da cancela
 *
 */
public class AccessControlWidget extends Widget {
	
	public static final String CLASSNAME = AccessControlWidget.class.getName();
	
	// Identificador do estacionamento
	public static final String PARKING = "parking";
	private String parking;
	
	// Identificador da cancela
	public static final String GATE = "gate";
	private String gate;
	
	// Libera cancela ou não? (0/1)
	public static final String ACCESS_STATUS = "access_status";
	
	// Constantes para a cancela
	public static final Boolean ACCESS_OK = true;
	public static final Boolean ACCESS_BLOCK = false;

	public AccessControlWidget(String parking, String gate) {
		super(CLASSNAME, CLASSNAME);
		this.parking = parking;
		this.gate = gate;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(ACCESS_STATUS, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(PARKING, parking), true);
		addAttribute(AttributeNameValue.instance(GATE, gate), true);
	}
}
