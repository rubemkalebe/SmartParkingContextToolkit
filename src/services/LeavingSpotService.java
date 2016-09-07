package services;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class LeavingSpotService extends Service {
	
	public String spot;
	
	@SuppressWarnings("serial")
	public LeavingSpotService(final Widget widget, String spot) {
		super(widget, "LeavingSpotService", 
				new FunctionDescriptions() {
					{ // constructor
						// define function for the service
						add(new FunctionDescription(
								"LeavingSpotControl", 
								"Notify driver when his car lefts the spot", 
								widget.getNonConstantAttributes()));
					}
				});
		this.spot = spot;
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataObject execute(ServiceInput serviceInput) {
		// TODO Auto-generated method stub
		return null;
	}

}
