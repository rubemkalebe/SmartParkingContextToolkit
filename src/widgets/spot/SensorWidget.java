package widgets.spot;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

/**
 * Sensor para detectar carro em uma vaga do estacionamento.
 *
 */
public class SensorWidget extends Widget {
	
	public static final String CLASSNAME = SensorWidget.class.getName();
	
	// Vaga ao qual está associado
	public static final String SPOT = "spot";
	private String spot;
	
	// Sensor para detectar carro (0/1)
	public static String SENSOR = "sensor";
	
	// Contantes para o sensor
	public static final Boolean OCCUPIED = true;
	public static final Boolean FREE = false;
	
	public SensorWidget(String spot) {
		super(CLASSNAME, CLASSNAME);
		this.spot = spot;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(SENSOR, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(SPOT, spot), true);
	}

}
