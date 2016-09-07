package model;

import context.arch.discoverer.query.AbstractQueryItem;
import context.arch.widget.WidgetXmlParser;
import enactors.AlarmEnactor;
import enactors.LeavingSpotEnactor;
import enums.TypeEnum;
import services.AlarmService;
import services.LeavingSpotService;
import widgets.spot.AlarmWidget;
import widgets.spot.NotifyWidget;
import widgets.spot.SensorWidget;
import widgets.spot.SpotWidget;

public class Spot {

	public String parking;
	public String spot;
	public TypeEnum type;
	
	public SpotWidget spotWidget;
	public SensorWidget sensorWidget;
	public AlarmWidget alarmWidget;
	public NotifyWidget notifyWidget;
	
	public AlarmEnactor alarmEnactor;
	public LeavingSpotEnactor leavingSpotEnactor;
	
	public AlarmService alarmService;
	public LeavingSpotService leavingSpotService;
	
	public Spot(String spot, TypeEnum type) {
		this.spot = spot;
		this.type = type;
		
		spotWidget = new SpotWidget(spot, type);
		sensorWidget = new SensorWidget(spot);
		alarmWidget = new AlarmWidget(spot);
		notifyWidget = new NotifyWidget(spot);
		
		alarmService = new AlarmService(spotWidget, spot);
		alarmWidget.addService(alarmService);
		
		leavingSpotService = new LeavingSpotService(spotWidget, spot);
		notifyWidget.addService(leavingSpotService);
		
		AbstractQueryItem<?, ?>[] inAlarmWidgetQuery = {
			WidgetXmlParser.createWidgetSubscriptionQuery(sensorWidget),
			WidgetXmlParser.createWidgetSubscriptionQuery(spotWidget)
		};
		AbstractQueryItem<?, ?>[] outAlarmWidgetQuery = {
			WidgetXmlParser.createWidgetSubscriptionQuery(alarmWidget)
		};
		alarmEnactor = new AlarmEnactor(inAlarmWidgetQuery, outAlarmWidgetQuery);
		
		AbstractQueryItem<?, ?>[] inNotifyWidgetQuery = {
			WidgetXmlParser.createWidgetSubscriptionQuery(sensorWidget),
			WidgetXmlParser.createWidgetSubscriptionQuery(spotWidget)
		};
		AbstractQueryItem<?, ?>[] outNotifyWidgetQuery = {
			WidgetXmlParser.createWidgetSubscriptionQuery(notifyWidget)
		};
		leavingSpotEnactor = new LeavingSpotEnactor(inNotifyWidgetQuery, outNotifyWidgetQuery);
		
		// Inicialização
		spotWidget.updateData(SpotWidget.DRIVER, "");
		sensorWidget.updateData(SensorWidget.SENSOR, false);
		alarmWidget.updateData(AlarmWidget.ALARM, false);
		notifyWidget.updateData(NotifyWidget.NOTIFY, false);
	}
	
}
