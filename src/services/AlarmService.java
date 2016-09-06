package services;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class AlarmService extends Service {
	
	@SuppressWarnings("serial")
	public AlarmService(final Widget widget) {
		super(widget, "AlarmService", 
				new FunctionDescriptions() {
					{ // constructor
						// define function for the service
						add(new FunctionDescription(
								"AlarmControl", 
								"Request driver authentication", 
								widget.getNonConstantAttributes()));
					}
				});
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataObject execute(ServiceInput serviceInput) {
		// TODO Auto-generated method stub
		return null;
	}

}