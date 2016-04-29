package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class LayerGame extends Layer {
	

	//TODO �����ļ�
	/**
	 * ��λ��ƫ����
	 */
	private static final int SIZE_ROL=5;
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		
		//��÷������鼯��
		Point[] points=this.dto.getGameAct().getActPoints();
		//��÷������ͱ��(0~6)
		int typeCode=this.dto.getGameAct().getTypeCode();
		//���Ʒ���
		for(int i=0;i<points.length;i++){
			drawActByPoint(points[i].x,points[i].y,typeCode+1,g);
		}
		//��ӡ��ͼ
		boolean[][] map=this.dto.getGameMap();
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[x].length;y++){
				if(map[x][y]){
					drawActByPoint(x,y,0,g);
				}
				
			}
		}
		
	}
	/**
	 * ���������ο�
	 */
	private void drawActByPoint(int x,int y,int imgIdx, Graphics g){
		g.drawImage(Img.ACT, 
				this.x+(x<<SIZE_ROL)+7, 
				this.y+(y<<SIZE_ROL)+7,
				this.x+(x+1<<SIZE_ROL)+7,
				this.y+(y+1<<SIZE_ROL)+7,
				imgIdx<<SIZE_ROL, 0, (imgIdx+1)<<SIZE_ROL,1<<SIZE_ROL, null);
	}

}
