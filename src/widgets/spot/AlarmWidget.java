package widgets.spot;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

/**
 * Alarme para constranger o motorista a se identificar
 * Presente em uma vaga do estacionamento
 *
 */
public class AlarmWidget extends Widget {
	
	public static final String CLASSNAME = AlarmWidget.class.getName();
	
	// Vaga ao qual está associado
	public static final String SPOT = "spot";
	private String spot;
	
	// Alarme para constranger o motorista a se identificar
	public static final String ALARM = "alarm"; 
	
	// Constantes para alarme
	public static final Boolean ALARM_ON = true;
	public static final Boolean ALARM_OFF = false;
	
	public AlarmWidget(String spot) {
		super(CLASSNAME, CLASSNAME);
		this.spot = spot;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(ALARM, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(SPOT, spot), true);
	}

}
