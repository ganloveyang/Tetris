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
	
	private static final int LEFT_SIDE=0;
	//TODO Ӳ����
	private static final int RIGHT_SIDE=9;
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//��÷������鼯��
		Point[] points=this.dto.getGameAct().getActPoints();
		//������Ӱ
		//TODO ��Ӱ�ر�
		this.drawShadow(points,true,g);
		
		
		//��÷������ͱ��(0~6)
		int typeCode=this.dto.getGameAct().getTypeCode();
		//���Ʒ���
		for(int i=0;i<points.length;i++){
			drawActByPoint(points[i].x,points[i].y,typeCode+1,g);
		}
		//���Ƶ�ͼ
		boolean[][] map=this.dto.getGameMap();
		//���㵱ǰ�ѻ���ɫ
		int lv=this.dto.getNowLevel();
		int imgIdx=lv==0?0:(lv-1)%7+1;
		//TODO ����������� imgIdx=8
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[x].length;y++){
				if(map[x][y]){
					drawActByPoint(x,y,imgIdx,g);
				}
				
			}
		}
		
	}
	/**
	 * ������Ӱ
	 */
	private void drawShadow(Point[] points, boolean isShowShadow,Graphics g) {
		if(!isShowShadow){
			
		}
		int leftX=RIGHT_SIDE;
		int rightX=LEFT_SIDE;
		for(Point p:points){
			leftX=p.x<leftX?p.x:leftX;
			rightX=p.x>rightX?p.x:rightX;
		}
		g.drawImage(Img.SHODOW,
				this.x+SIZE+(leftX<<SIZE_ROL),
				this.y+SIZE,
				(rightX-leftX+1)<<SIZE_ROL,
				this.h-(SIZE<<1),
				null
		);
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
