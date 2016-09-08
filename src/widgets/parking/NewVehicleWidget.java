package widgets.parking;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

/**
 * Quando chega um novo veiculo no estacionamento
 * Tem dois atributos estaticos: identificador do estacionamento e o seu proprio identificador
 *
 */
public class NewVehicleWidget extends Widget {
	
	public static final String CLASSNAME = NewVehicleWidget.class.getName();
	
	// Identificador do estacionamento
	public static final String PARKING = "parking";
	private String parking;
	
	// Identificador da cancela
	public static final String GATE = "gate";
	private String gate;
	
	// Quando chega um novo veículo (0/1)
	public static final String NEW_VEHICLE = "new_vehicle";
	
	// Constantes para novo veiculo
	public static final Boolean NEW_VEHICLE_YES = true;
	public static final Boolean NEW_VEHICLE_NO = false;

	public NewVehicleWidget(String parking, String gate) {
		super(CLASSNAME, CLASSNAME);
		this.parking = parking;
		this.gate = gate;
		super.start(true);
	}
	
	@Override
	protected void init() {
		// non-constant attributes
		addAttribute(Attribute.instance(NEW_VEHICLE, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(PARKING, parking), true);
		addAttribute(AttributeNameValue.instance(GATE, gate), true);
	}

}
