package model;

import javax.swing.JFrame;
import enums.TypeEnum;
import ui.Panel;

public class Parking {
	
	public String parking;
	
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
	
	public Parking(String parking, JFrame f, Panel p ) {
		this.parking = parking;
		this.s1 = new Spot("S1", TypeEnum.HANDICAPPED, p);
		this.s2 = new Spot("S2", TypeEnum.HANDICAPPED, p);
		this.s3 = new Spot("S3", TypeEnum.ELDERLY, p);
		this.s4 = new Spot("S4", TypeEnum.ELDERLY, p);
		this.s5 = new Spot("S5", TypeEnum.NORMAL, p);
		this.s6 = new Spot("S6", TypeEnum.NORMAL, p);
		this.s7 = new Spot("S7", TypeEnum.NORMAL, p);
		this.s8 = new Spot("S8", TypeEnum.NORMAL, p);
		this.s9 = new Spot("S9", TypeEnum.NORMAL, p);
		this.s10 = new Spot("S10", TypeEnum.NORMAL, p);
		this.s11 = new Spot("S11", TypeEnum.NORMAL, p);
		this.s12 = new Spot("S12", TypeEnum.NORMAL, p);
		// TODO Auto-generated constructor stub
	}
}
