package model;

import enums.TypeEnum;
import widgets.driver.DriverWidget;

public class Vehicle {

	// Instanciar wcomponentes do veiculo
	public String id;
	public TypeEnum type;
	public Boolean permission;
	
	public DriverWidget driverWidget;

	public Vehicle(String id, TypeEnum type, Boolean permission) {
		this.id = id;
		this.type = type;
		this.permission = permission;
		driverWidget = new DriverWidget(id, type, permission);
	}

	public void linkSpot(String current_spot) {
		driverWidget.updateData(DriverWidget.CURRENT_SPOT, current_spot);
	}
	
	public String toString() {		
		return "vehicle : " + id + " " + type.toString() + " Permission: " + permission ;
	}
	
}
