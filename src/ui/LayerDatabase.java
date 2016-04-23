package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerDatabase extends Layer{

	private static Image IMG_DB=new ImageIcon("graphics/string/db.png").getImage();
	public LayerDatabase(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		g.drawImage(IMG_DB, this.x+PADDING, this.y+PADDING, null);
	}

}
