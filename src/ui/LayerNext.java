package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerNext extends Layer{
	
	private static Image[] NEXT_ACT;
	
	static {
		NEXT_ACT =new Image[7];
		for(int i=0;i<NEXT_ACT.length;i++){
			 NEXT_ACT[i]=new ImageIcon("graphics/game/"+i+".png").getImage();
		}
	}
	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		this.drawImageAtCenter(NEXT_ACT[this.dto.getNext()], g);
		
	}

	/**
	 * ���л�ͼ
	 * @param g
	 */
	private void drawImageAtCenter(Image img,Graphics g){
		int imgW=img.getWidth(null);
		int imgH=img.getHeight(null);
		int imgX=this.x+(this.w-imgW>>1);
		int imgY=this.y+(this.h-imgH>>1);
		g.drawImage(img, imgX, imgY, null);
		
	}
}
