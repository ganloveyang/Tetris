package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerDatabase extends Layer{

	
	public LayerDatabase(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		g.drawImage(Img.DB, this.x+PADDING, this.y+PADDING, null);
	}

}
