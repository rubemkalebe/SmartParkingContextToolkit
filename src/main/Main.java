package main;

import javax.swing.JFrame;

import context.arch.discoverer.Discoverer;
import model.Parking;
import ui.Panel;

public class Main {

	public static void main(String[] args) {

		System.setProperty("java.net.preferIPv4Stack", "true"); // Rubem: no meu só executa setando isso
		Discoverer.start();
		
		// Inicializa a interface
		JFrame janela = new JFrame("SmartParking");
		Panel meuPainel = new Panel(janela.getGraphics());		
		
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.add(meuPainel);
		janela.setSize(600,600);
		janela.setVisible(true);
		
		Parking p1 = new Parking("SmartParking", janela, meuPainel );

	}

}
