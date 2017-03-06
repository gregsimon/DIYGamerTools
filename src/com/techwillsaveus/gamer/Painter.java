/**
 * @author Greg Simon gregsimon@
 **/

package com.techwillsaveus.gamer;

import java.awt.Point;

import javax.swing.*;

import processing.app.Editor;

public class Painter implements processing.app.tools.Tool
{
	private static Editor editor;
	private PainterApp app;
	private final String kTitle = "DIY Gamer Image Painter";
	
	public void init(Editor editor) {
	    Painter.editor = editor; 
	}
	
	public String getMenuTitle() {
		return kTitle;
	}

	public void run() {
		
		JFrame frame = new JFrame(kTitle);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app = new PainterApp();
		frame.add(app);

		frame.pack();
        frame.setVisible(true);
        
        Point pos = editor.getLocation();
        pos.translate(20, 20);
        frame.setLocation(pos);
	}
	
	
}