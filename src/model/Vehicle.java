package model;

import context.arch.discoverer.query.AbstractQueryItem;

import enactors.NewVehicleEnactor;
import enums.TypeEnum;
import services.NewVehicleService;
import widgets.driver.DriverWidget;
import widgets.parking.NewVehicleWidget;

public class Vehicle {
	//Instanciar wcomponentes do veiculo
	public String id;
	public TypeEnum type;
	public Boolean permission;
	
	public DriverWidget driverWidget;
	public NewVehicleWidget newVehicleWidget;
	
	public NewVehicleEnactor newVehicleEnactor;
	
	public NewVehicleService newVehicleService;

	public Vehicle(String id, TypeEnum type, Boolean permission) {
		super();
		this.id = id;
		this.type = type;
		this.permission = permission;
		
		driverWidget = new DriverWidget(id, type, permission);
		newVehicleService = new NewVehicleService(driverWidget);
		driverWidget.addService(newVehicleService);
		
		
		AbstractQueryItem<?, ?>[] inNewVehicleWidgetQuery = {
				//TODO
//			WidgetXmlParser.createWidgetSubscriptionQuery(sensorWidget),
//			WidgetXmlParser.createWidgetSubscriptionQuery(spotWidget)
		};
		
		AbstractQueryItem<?, ?>[] outNewVehicleWidgetQuery = {
				//TODO
//          WidgetXmlParser.createWidgetSubscriptionQuery(alarmWidget)
		};
							
		newVehicleEnactor = new NewVehicleEnactor(inNewVehicleWidgetQuery, outNewVehicleWidgetQuery);
		
		//Inicialização
		driverWidget.updateData(DriverWidget.PERMISSION, true);
		
	}

	public String toString(){		
		return "vehicle : " + id+ "  " + type.toString() + "Permission: "+ permission ;
	}
	
		
}
