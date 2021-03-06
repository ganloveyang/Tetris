package ui;

import java.awt.Graphics;
import java.awt.Point;

import config.GameConfig;

public class LayerGame extends Layer {
	
	/**
	 * 左位移偏移量
	 */
	private static final int SIZE_ROL=GameConfig.getFrameConfig().getSizeRol();
	
	private static final int LEFT_SIDE=0;
	
	private static final int RIGHT_SIDE=GameConfig.getSystemConfig().getMaxX();
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//获得方块数组集合
		Point[] points=this.dto.getGameAct().getActPoints();
		//绘制阴影
		
		this.drawShadow(points,true,g);
		
		
		//获得方块类型编号(0~6)
		int typeCode=this.dto.getGameAct().getTypeCode();
		//绘制方块
		for(int i=0;i<points.length;i++){
			drawActByPoint(points[i].x,points[i].y,typeCode+1,g);
		}
		//绘制地图
		boolean[][] map=this.dto.getGameMap();
		//计算当前堆积颜色
		int lv=this.dto.getNowLevel();
		int imgIdx=lv==0?0:(lv-1)%7+1;
		//TODO 如果是输的情况 imgIdx=8
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[x].length;y++){
				if(map[x][y]){
					drawActByPoint(x,y,imgIdx,g);
				}
				
			}
		}
		
	}
	/**
	 * 绘制阴影
	 */
	private void drawShadow(Point[] points, boolean isShowShadow,Graphics g) {
		//TODO 阴影关闭
		if(!isShowShadow){
			
		}
		int leftX=RIGHT_SIDE;
		int rightX=LEFT_SIDE;
		for(Point p:points){
			leftX=p.x<leftX?p.x:leftX;
			rightX=p.x>rightX?p.x:rightX;
		}
		g.drawImage(Img.SHODOW,
				this.x+BORDER+(leftX<<SIZE_ROL),
				this.y+BORDER,
				(rightX-leftX+1)<<SIZE_ROL,
				this.h-(BORDER<<1),
				null
		);
	}

	/**
	 * 绘制正方形块
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
