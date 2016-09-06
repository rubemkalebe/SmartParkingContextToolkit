package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import enums.StatusEnum;
import enums.TypeEnum;

/**
 * Vaga de um estacionamento
 * Possui um alarme para constranger o motorista a se identificar
 * Possui um atuador para notificar o motorista caso o carro deixe a vaga 
 * 
 */
public class SpotWidget extends Widget {
	
	public static final String CLASSNAME = SpotWidget.class.getName();
	
	public static final String ID = "identifier"; // Idetificador da vaga
	public static final String STATUS = "status"; // Estado da vaga (ocupada, livre)
	public static final String SENSOR = "sensor"; // Sensor para detectar carro (0/1)
	public static final String TYPE = "type";	  // Tipo da vaga (normal, idoso, deficiente)
	public static final String ALARM = "alarm";   // Alarme para constranger o motorista a se identificar
	public static final String DRIVER = "driver"; // Identificador do motorista que está estacionado
	public static final String NOTIFY = "notify"; // Atuador para notificar o motorista caso o carro deixe a vaga
	
	// Constantes para alarme
	public static final Boolean ALARM_ON = true;
	public static final Boolean ALARM_OFF = false;
	
	// Constantes para notificador
	public static final Boolean NOTIFY_ON = true;
	public static final Boolean NOTIFY_OFF = false;
	
	// Identificador da vaga
	private String spotID;

	public SpotWidget(String id) {
		super(CLASSNAME, CLASSNAME);
		this.spotID = id;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(STATUS, StatusEnum.class));
		addAttribute(Attribute.instance(SENSOR, Boolean.class));
		addAttribute(Attribute.instance(TYPE, TypeEnum.class));
		addAttribute(Attribute.instance(ALARM, Boolean.class));
		addAttribute(Attribute.instance(DRIVER, String.class));
		addAttribute(Attribute.instance(NOTIFY, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(ID, spotID), true);
	}
	
}
