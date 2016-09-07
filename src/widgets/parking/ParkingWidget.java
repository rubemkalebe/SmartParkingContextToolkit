package widgets.parking;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

/**
 * Estacionamento
 *
 */
public class ParkingWidget extends Widget {

	public static final String CLASSNAME = ParkingWidget.class.getName();
	
	// Identificador do estacionamento
	public static final String PARKING = "parking";
	private String parking;
	
	// Tem vaga? (0/1)
	public static final String VACANCY = "spot available";
	
	// Constantes para vagas
	public static final Boolean VACANCY_YES = true;
	public static final Boolean VACANCY_NO = false;

	public ParkingWidget(String parking) {
		super(CLASSNAME, CLASSNAME);
		this.parking = parking;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(VACANCY, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(PARKING, parking), true);
	}
}
