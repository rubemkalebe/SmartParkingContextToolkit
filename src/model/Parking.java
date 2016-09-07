package model;

import enums.TypeEnum;

public class Parking {
	
	public String parking;
	
	public Spot s1;
	public Spot s2;
	public Spot s3;
	public Spot s4;
	
	public Parking(String parking) {
		this.parking = parking;
		this.s1 = new Spot("S1", TypeEnum.HANDICAPPED);
		this.s2 = new Spot("S2", TypeEnum.ELDERLY);
		this.s3 = new Spot("S3", TypeEnum.NORMAL);
		this.s4 = new Spot("S4", TypeEnum.NORMAL);
		// TODO Auto-generated constructor stub
	}
}
