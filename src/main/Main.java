package main;

import context.arch.discoverer.Discoverer;
import model.Parking;

public class Main {

	public static void main(String[] args) {

		System.setProperty("java.net.preferIPv4Stack", "true"); // Rubem: no meu só executa setando isso
		Discoverer.start();
		
		// TODO Auto-generated method stub
		Parking p1 = new Parking("SmartParking");

	}

}
