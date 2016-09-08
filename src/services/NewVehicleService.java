package services;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class NewVehicleService extends Service {

	@SuppressWarnings("serial")
	public NewVehicleService(final Widget widget) {
		super(widget, "NewVehicleService", 
				new FunctionDescriptions() {
					{ // constructor
						// define function for the service
						add(new FunctionDescription(
								"NewVehicleControl", 
								"Open/close the gate", 
								widget.getNonConstantAttributes()));
					}
				});
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataObject execute(ServiceInput serviceInput) {
		System.out.println("Carro Liberado!");
		return new DataObject();
	}

}
