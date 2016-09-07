package widgets.spot;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

/**
 * Atuador para notificar o motorista caso o carro deixe a vaga
 * Notificação via app
 *
 */
public class NotifyWidget extends Widget {
	
	public static final String CLASSNAME = NotifyWidget.class.getName();
	
	// Vaga ao qual está associado
	public static final String SPOT = "spot";
	private String spot;
	
	// Atuador para notificar o motorista caso o carro deixe a vaga
	public static final String NOTIFY = "notify";
	
	// Constantes para notificador
	public static final Boolean NOTIFY_ON = true;
	public static final Boolean NOTIFY_OFF = false;
	
	public NotifyWidget(String spot) {
		super(CLASSNAME, CLASSNAME);
		this.spot = spot;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(NOTIFY, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(SPOT, spot), true);
	}

}
