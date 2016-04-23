package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerBackground extends Layer {
    //TODO£∫¡Ÿ ±±≥æ∞
	private static Image IMG_BG=new ImageIcon("graphics/background/aaa.jpg").getImage();
	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
        g.drawImage(IMG_BG, 0, 0,1200,700, null);
	}

}
