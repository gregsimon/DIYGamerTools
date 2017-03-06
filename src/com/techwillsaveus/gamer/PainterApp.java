/**
 * @author Greg Simon gregsimon@
 **/

package com.techwillsaveus.gamer;


import javax.swing.*;


import java.awt.*;              //for layout managers and more
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*; 

@SuppressWarnings("serial")
public class PainterApp extends JPanel implements java.awt.event.MouseListener {

	private byte img[] = new byte[64]; // 8 x 8 x 1
	
	public PainterApp() {		
		super.addMouseListener(this);		
		setPreferredSize(new Dimension(480, 480));
		
		for (int i=0; i<8; i++ ) img[i] = 0x00;
	}
	
	public void mouseClicked(MouseEvent evt) { }
	
	private void exportToClipboard() {
		/* 
		 * produce something like this
		 * byte character[8] = {B00010000,
                     B01100000,
                     B01100000,
                     B10010000,
                     B00000000,
                     B00000000,
                     B00000000,
                     B00000000};
		 */
		
		String code = "{";
		
		for( int y=0; y<8; y++) {
			code += "B";
	    	for( int x=0; x<8; x++) {
	    		if (img[(y*8)+x] != 0) 
	    			code += "1";
	    		else 
	    			code += "0";	    		
	    	}
	    	code += ",\n";
	    }
		
		code += "};";
	    StringSelection selection = new StringSelection(code);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);		
	}
	
	public void mousePressed(MouseEvent evt) { 
		int pixel_size = this.getSize().height / 8;		
	    int x = (byte)(evt.getX() / pixel_size);
	    int y = evt.getY() / pixel_size;
	    
	    if (img[(y*8) + x] == 0)
	    	img[(y*8) + x] = 1;
	    else
	    	img[(y*8) + x] = 0;
	    	
	    repaint();
	    System.out.println(x + ", " + y);
	    
	    exportToClipboard();
		
	}
	
	public void mouseReleased(MouseEvent e) { }	
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	
	public void paint(Graphics g) {

	    super.paint(g); //To change body of generated methods, choose Tools | Templates.
	    Graphics2D g2 = (Graphics2D) g;
	    
	    int w = this.getSize().width;
	    int h = this.getSize().height;
	    int pixel_size = this.getSize().height / 8;
	    
	    g2.setColor(Color.black);
	    g2.fillRect(0, 0, w, h);
	    
	    g2.setColor(Color.white);
	    
	    for( int y=0; y<8; y++) {
	    	for( int x=0; x<8; x++) {
	    		if (img[(y*8)+x] != 0) {
	    			g2.fillRect(x*pixel_size, y*pixel_size, pixel_size-1, pixel_size-1);
	    		}
	    	}
	    }
	}
	
}
