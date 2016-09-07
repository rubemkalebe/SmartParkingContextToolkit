package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.MenuPeer;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        //numbers
        for(int i = 0; i < 6; i++){
        	g.setColor( Color.BLACK );
        	g.drawString(""+(i+1), 150+(i*80), 70);
        	g.drawString(""+(i+7), 150+(i*80), 175);
        }
        
        //Images
//	    ImageIcon idoso = new ImageIcon("images/idoso.jpg");	
//		Image deficiente = new ImageIcon("images/deficiente.jpg").getImage();
//		g.drawImage(deficiente, 130, 100, this);
        
	}
	
	public void paintNumber(int spot, Graphics g){
		g.setColor( Color.BLACK );
		if (spot < 7){
			g.drawString(""+(spot+1), 160+(spot*80), 100);
		}
		else{
			g.drawString(""+(spot+7), 160+(spot*80), 200);
		}		
	}
	
	public void occupingSpot( int spot, Graphics g ){		
	    if (spot < 7){
	    	g.setColor( Color.RED );	    	
	    	g.fillRect(100+((spot-1)*80), 81 , 77, 100);
	    	paintNumber(spot-1, g);
	    }	    
	    else{
	    	g.setColor( Color.RED );
	    	g.fillRect( 100+((spot-7)*80), 186 , 77, 100);
	    	paintNumber(spot-1, g);
	    }
	}
	
	public void freeingSpot( int spot, Graphics g ){		
	    if (spot < 7){
	    	g.setColor( Color.GREEN );
	    	g.fillRect( 100+((spot-1)*80), 81 , 77, 100);
	    	paintNumber(spot-1, g);
	    }	    
	    else{
	    	g.setColor( Color.GREEN );
	    	g.fillRect( 100+((spot-7)*80), 186 , 77, 100);
	    	paintNumber(spot-1, g);
	    }
	}
	
	
	public void waitingSpot( int spot, Graphics g ){		
	    if (spot < 7){
	    	g.setColor( Color.BLUE );
	    	g.fillRect( 100+((spot-1)*80), 81 , 77, 100);
	    	paintNumber(spot-1, g);
	    }	    
	    else{
	    	g.setColor( Color.BLUE );
	    	g.fillRect( 100+((spot-7)*80), 186 , 77, 100);
	    	paintNumber(spot-1, g);
	    }
	}
	
	public static void main(String[] args) {
		JFrame janela = new JFrame("SmartParking");
		Panel meuPainel = new Panel();		
		
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.add(meuPainel);
		janela.setSize(600,600);
		janela.setVisible(true);
		
		//lÊ operação
		Scanner scanner = new Scanner(System.in); 		
		int i = scanner.nextInt();
		while(i > 0){
			switch (i){
				case 1:
					int j = scanner.nextInt();
					meuPainel.occupingSpot(j, janela.getGraphics());
					break;
				case 2:
					int j1 = scanner.nextInt();
					meuPainel.waitingSpot(j1, janela.getGraphics());
					break;
				case 3:
					int j2 = scanner.nextInt();
					meuPainel.freeingSpot(j2, janela.getGraphics());
					break;	
			}
			i = scanner.nextInt();
		}
				
	    }
	
}
