package model;

import java.awt.Graphics;
import java.util.ArrayList;

import enums.TypeEnum;
import ui.Panel;

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
		// TODO Auto-generated constructor stub
	}
}
