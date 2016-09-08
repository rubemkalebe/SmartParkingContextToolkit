package model;

import java.awt.Graphics;
import java.util.ArrayList;

import context.arch.discoverer.query.AbstractQueryItem;
import context.arch.widget.WidgetXmlParser;
import enactors.AlarmEnactor;
import enactors.NewVehicleEnactor;
import enums.TypeEnum;
import services.NewVehicleService;
import ui.Panel;
import widgets.driver.DriverWidget;
import widgets.parking.AccessControlWidget;
import widgets.parking.NewVehicleWidget;
import widgets.parking.ParkingWidget;
import widgets.spot.SensorWidget;
import widgets.spot.SpotWidget;

public class Parking {
	
	public String parking;
	public ArrayList<Spot> spots= new ArrayList<>(); 
	
	public Spot s1;
	public Spot s2;
	public Spot s3;
	public Spot s4;
	public Spot s5;
	public Spot s6;
	public Spot s7;
	public Spot s8;
	public Spot s9;
	public Spot s10;
	public Spot s11;
	public Spot s12;
	
	public ParkingWidget parkingWidget;
	public NewVehicleWidget newVehicleWidget;
	public AccessControlWidget accessControlWidget;
	
	public NewVehicleEnactor newVehicleEnactor;
	
	public NewVehicleService newVehicleService;
	
	public Parking(String parking, Panel p, Graphics g ) {
		this.parking = parking;
		this.s1 = new Spot("S1", TypeEnum.HANDICAPPED, p, g);
		this.s2 = new Spot("S2", TypeEnum.HANDICAPPED, p, g);
		this.s3 = new Spot("S3", TypeEnum.ELDERLY, p, g);
		this.s4 = new Spot("S4", TypeEnum.ELDERLY, p, g);
		this.s5 = new Spot("S5", TypeEnum.NORMAL, p, g);
		this.s6 = new Spot("S6", TypeEnum.NORMAL, p, g);
		this.s7 = new Spot("S7", TypeEnum.NORMAL, p, g);
		this.s8 = new Spot("S8", TypeEnum.NORMAL, p, g);
		this.s9 = new Spot("S9", TypeEnum.NORMAL, p, g);
		this.s10 = new Spot("S10", TypeEnum.NORMAL, p, g);
		this.s11 = new Spot("S11", TypeEnum.NORMAL, p, g);
		this.s12 = new Spot("S12", TypeEnum.NORMAL, p, g);
		spots.add(s1);
		spots.add(s2);
		spots.add(s3);
		spots.add(s4);
		spots.add(s5);
		spots.add(s6);
		spots.add(s7);
		spots.add(s8);
		spots.add(s9);
		spots.add(s10);
		spots.add(s11);
		spots.add(s12);
		
		parkingWidget = new ParkingWidget(parking);
		newVehicleWidget = new NewVehicleWidget(parking, "G1");
		accessControlWidget = new AccessControlWidget(parking, "G1");
		
		// Inicialização
		parkingWidget.updateData(ParkingWidget.VACANCY, true);
		newVehicleWidget.updateData(NewVehicleWidget.NEW_VEHICLE, false);
		accessControlWidget.updateData(AccessControlWidget.ACCESS_STATUS, false);
		
		newVehicleService = new NewVehicleService(parkingWidget);
		parkingWidget.addService(newVehicleService);
		
		newVehicleEnactor = null;
	}
	
	public void newVehicle(DriverWidget driver) {
		AbstractQueryItem<?, ?>[] inAccessWidgetQuery = {
			WidgetXmlParser.createWidgetSubscriptionQuery(newVehicleWidget),
			WidgetXmlParser.createWidgetSubscriptionQuery(parkingWidget),
			WidgetXmlParser.createWidgetSubscriptionQuery(driver)
		};
		AbstractQueryItem<?, ?>[] outAccessWidgetQuery = {
			WidgetXmlParser.createWidgetSubscriptionQuery(accessControlWidget)
		};
		newVehicleEnactor = new NewVehicleEnactor(inAccessWidgetQuery, outAccessWidgetQuery);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//newVehicleWidget.updateData(NewVehicleWidget.NEW_VEHICLE, true);
	}
	
	public void occupySpot(int i) {
		spots.get(i).sensorWidget.updateData(SensorWidget.SENSOR, true);
	}
	
	public void linkDriver(int i, String driver) {
		spots.get(i).spotWidget.updateData(SpotWidget.DRIVER, driver);
	}
	
	public void freeSpot(int i) {
		spots.get(i).sensorWidget.updateData(SensorWidget.SENSOR, false);
	}
	
	
}
