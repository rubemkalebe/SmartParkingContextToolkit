package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphics g;
	private static Scanner scanner;
	
	public Panel(Graphics g){
		this.g = g;
				
	}
	
	public void paintComponent( Graphics g){
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
            
            //Type spot             
            if( i == 0 || i ==1){
            	g.setColor( Color.WHITE );
            	g.drawString("DEFICIENTE", 95+(i*80), 140);            	
            }            
            else if (i == 2 || i ==3){
            	g.setColor( Color.WHITE );
            	g.drawString("IDOSO", 110+(i*80), 140);            	
            }
        }
        
        //numbers
        for(int i = 0; i < 6; i++){
        	g.setColor( Color.BLACK );
        	g.drawString(""+(i+1), 150+(i*80), 70);
        	g.drawString(""+(i+7), 150+(i*80), 175);
        } 
        
	}
	
	public void paintNumber(int spot){
		g.setColor( Color.BLACK );
		if (spot < 7){
			g.drawString(""+(spot+1), 160+(spot*80), 100);
		}
		else{
			g.drawString(""+(spot+7), 160+(spot*80), 200);
		}		
	}
	
	public void paintType(int spot){
		int i = spot - 1;
		
		 if( i == 0 || i ==1){
         	g.setColor( Color.WHITE );
         	g.drawString("DEFICIENTE", 102+(i*80), 170);            	
         }            
         else if (i == 2 || i ==3){
         	g.setColor( Color.WHITE );
         	g.drawString("IDOSO", 120+(i*80), 170);            	
         }				
	}
	
	public void occupingSpot( int spot ){		
	    if (spot < 7){
	    	g.setColor( Color.RED );	    	
	    	g.fillRect(100+((spot-1)*80), 81 , 77, 100);
	    	paintNumber(spot-1);
	    	paintType(spot);	    	
	    }	    
	    else{
	    	g.setColor( Color.RED );
	    	g.fillRect( 100+((spot-7)*80), 186 , 77, 100);
	    	paintNumber(spot-1);
	    }
	}
	
	public void freeingSpot( int spot ){		
	    if (spot < 7){
	    	g.setColor( Color.GREEN );
	    	g.fillRect( 100+((spot-1)*80), 81 , 77, 100);
	    	paintNumber(spot-1);
	    	paintType(spot);
	    }	    
	    else{
	    	g.setColor( Color.GREEN );
	    	g.fillRect( 100+((spot-7)*80), 186 , 77, 100);
	    	paintNumber(spot-1);
	    }
	}
	
	
	public void waitingSpot( int spot ){		
	    if (spot < 7){
	    	g.setColor( Color.BLUE );
	    	g.fillRect( 100+((spot-1)*80), 81 , 77, 100);
	    	paintNumber(spot-1);
	    	paintType(spot);
	    }	    
	    else{
	    	g.setColor( Color.BLUE );
	    	g.fillRect( 100+((spot-7)*80), 186 , 77, 100);
	    	paintNumber(spot-1);
	    }
	}
	
	public static void main(String[] args) {
		JFrame janela = new JFrame("SmartParking");
		Panel meuPainel = new Panel(janela.getGraphics());		
		
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.add(meuPainel);
		janela.setSize(600,600);
		janela.setVisible(true);
		
		scanner = new Scanner(System.in); 		
		int i = scanner.nextInt();
		while(i > 0){
			
			switch (i){
				case 1:
					int j = scanner.nextInt();
					meuPainel.occupingSpot(j);
					break;
				case 2:
					int j1 = scanner.nextInt();
					meuPainel.waitingSpot(j1);
					break;
				case 3:
					int j2 = scanner.nextInt();
					meuPainel.freeingSpot(j2);
					break;	
			}
			i = scanner.nextInt();
		}
				
	    }
	
}
