package widgets.spot;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import enums.TypeEnum;

/**
 * Vaga de um estacionamento
 * Possui dos atributos estáticos: seu identificador e o tipo da vaga
 * 
 */
public class SpotWidget extends Widget {
	
	public static final String CLASSNAME = SpotWidget.class.getName(); 
	
	// Identificador da vaga
	public static final String SPOT = "spot";
	private String spot;
	
	// Tipo da vaga (normal, idoso, deficiente)
	public static final String TYPE = "type";
	private TypeEnum type;
	
	// Motorista
	public static final String DRIVER = "driver";

	public SpotWidget(String spot, TypeEnum type) {
		super(CLASSNAME, CLASSNAME);
		this.spot = spot;
		this.type = type;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// constant attributes
		addAttribute(AttributeNameValue.instance(SPOT, spot), true);
		addAttribute(AttributeNameValue.instance(TYPE, type), true);
		
		// non-constant attributes
		addAttribute(Attribute.instance(DRIVER, String.class));
	}
	
}
