package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.MenuPeer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel {	
	
	public void paintComponent( Graphics g ){
	    super.paintComponent( g );
	    
	    //Background color
	    this.setBackground( Color.WHITE );
	    
	    //Parking lot color
	    g.setColor( Color.GRAY );
        g.fillRect( 0, 0, 600, 300 );
        
        //Lines
        g.setColor( Color.YELLOW );
        g.drawLine( 90, 150, 570, 150 );
        
        for(int i = 0; i < 7; i++){
        	g.drawLine( 90+(i*80), 100, 90+(i*80), 200 );
        }                
        
        //Spots
        for(int i = 0; i < 6; i++){
        	g.setColor( Color.GREEN );
            g.fillRect( 92+(i*80), 50 , 77, 100);
            g.fillRect( 92+(i*80), 155 , 77, 100);
        }
	}
	
	public void occupingSpot( int spot, Graphics g ){		
	    if (spot < 7){
	    	g.setColor( Color.RED );
	    	g.fillRect( 92+((spot-1)*80), 50 , 77, 100);	    	
	    }	    
	    else{
	    	g.setColor( Color.RED );
	    	g.fillRect( 92+((spot-7)*80), 155 , 77, 100);	    	
	    }
	}
	
	public static void main(String[] args) {
		JFrame janela = new JFrame("SmartParking");
		Panel meuPainel = new Panel();		
		
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.add(meuPainel);
		janela.setSize(600,600);
		janela.setVisible(true);
		
		//lÊ vaga ocupada
		Scanner scanner = new Scanner(System.in); 		
		int i = scanner.nextInt();
		
		meuPainel.occupingSpot(i, janela.getGraphics());
				
	    }
	
}
