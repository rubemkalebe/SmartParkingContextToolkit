package main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import context.arch.discoverer.Discoverer;
import enums.TypeEnum;
import model.Parking;
import model.Vehicle;
import ui.Panel;
import widgets.parking.NewVehicleWidget;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		Graphics g;
		int i;
		
		System.setProperty("java.net.preferIPv4Stack", "true"); // Rubem: no meu só executa setando isso
		Discoverer.start();
		
		// Inicializa a interface
		JFrame janela = new JFrame("SmartParking");
		Panel meuPainel = new Panel(janela.getGraphics());		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		janela.add(meuPainel);
		janela.setSize(600,600);
		janela.setVisible(true);
		
		g = janela.getGraphics();
		Parking p1 = new Parking("SmartParking", meuPainel, janela.getGraphics());
		
		do {
			System.out.println("-----MENU-----");
			System.out.println("1 - NOVO VEICULO");
			System.out.println("2 - SOLICITAR ENTRADA NO ESTACIONAMENTO");
			System.out.println("3 - ESTACIONAR VEICULO");
			System.out.println("4 - REGISTRAR VAGA");		
			System.out.println("0 - SAIR");
			
			scanner = new Scanner(System.in); 		
			i = scanner.nextInt();
			
			int idVehicle = 0;
			
			switch (i){
			case 1: //Instanciar Novo veículo
				System.out.println("1 - NORMAL");
				System.out.println("2 - DEFICIENTE");
				System.out.println("3 - IDOSO");
				System.out.println("");
				System.out.println("Entre o tipo do Motorista:");
				int type = scanner.nextInt();
				idVehicle ++;	
				
				switch (type){					
				case 1:
					Vehicle v = new Vehicle("V"+idVehicle, TypeEnum.NORMAL, true);
					vehicles.add(v);
					break;
				case 2:
					Vehicle v1 = new Vehicle("V"+idVehicle, TypeEnum.HANDICAPPED, true);
					vehicles.add(v1);
					break;
				case 3:
					Vehicle v2 = new Vehicle("V"+idVehicle, TypeEnum.ELDERLY, true);
					vehicles.add(v2);
					break;				
				}
				break;											
				
			case 2://Solicitar entrada no estacionamento
				
				//Imprimir todos os veículos
				System.out.println("SIZE: " + vehicles.size());
				for(int j = 0; j < vehicles.size(); j++){
					System.out.println("ID : "+ j + vehicles.get(j).toString());
				}
				
				System.out.println("Entre com o ID do Veículo para entrar:");
				int v1 = scanner.nextInt();
				
				//Setar new vehicle widget para veículo solicitado
				//vehicles.get(v1).newVehicleWidget.NEW_VEHICLE = vehicles.get(v1).id;
				//vehicles.get(v1).newVehicleWidget.updateData(NewVehicleWidget.NEW_VEHICLE, true);
				p1.newVehicle(vehicles.get(v1).driverWidget);
				
				break;
					
			case 3://Ocupar Vaga
				System.out.println("Entre uma vaga:");
				int v3 = scanner.nextInt();
				meuPainel.waitingSpot(v3, g);
				p1.occupySpot(v3); 
				break;
			case 4: //Registrar vaga
				
				//Imprimir todos os veículos
				for(int j = 0; i < vehicles.size(); i++){
					System.out.println("ID :"+ j + vehicles.get(j).toString());
				}
				
				System.out.println("Entre com o ID do Veículo para registrar:");
				int v2 = scanner.nextInt();
				System.out.println("Entre com o numero da vaga:");
				int vaga = scanner.nextInt();
				
				//Setar widget do veículo current_spot na vaga escolhida
				vehicles.get(v2).linkSpot(p1.spots.get(vaga).spotWidget.ID);
				p1.linkDriver(vaga, vehicles.get(v2).driverWidget.DRIVER_ID);
				meuPainel.occupingSpot(vaga, g);
				break;
			}			
		} while(i > 0);
		
		System.exit(0);
		
	}

}
