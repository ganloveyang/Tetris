package ui;


import java.awt.Graphics;

public class LayerPoint extends Layer {
	/**
	 * 分数的最大位数
	 */
	private static final int POINT_BIT=5;
	//TODO 配置文件
	private static final int LEVEL_UP=20;
	/**
	 * 经验值y坐标
	 */
	private  final int expY;
	
	/**
	 * 消行y坐标
	 */
	private  final int rmLineY;
	/**
	 * 分数y坐标
	 */
	private  final int pointY;
	
	/**
	 * 消行x坐标
	 */
	private  final int comX;
	
	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		//初始化共通X坐标
		comX=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
		//初始化分数显示的Y坐标
		pointY=PADDING;
		//初始化消行显示的Y坐标
		rmLineY=this.pointY+Img.POINT.getHeight(null)+PADDING;
		//初始化经验值显示的Y坐标
		this.expY=this.rmLineY+Img.POINT.getHeight(null)+PADDING;
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//绘制标题(分数)
		g.drawImage(Img.POINT, x+PADDING, this.y+pointY, null);
		//显示分数
		this.drawNumber(comX, pointY,this.dto.getNowPoint(),POINT_BIT, g);
		//绘制标题(消行)
		g.drawImage(Img.RMLINE, x+PADDING, this.y+rmLineY, null);
		//显示消行
		this.drawNumber(comX, rmLineY,this.dto.getNowRemoveLine(),POINT_BIT, g);
		//绘制值槽（经验值）
		int rmLine=this.dto.getNowRemoveLine();
		this.drawRect(expY,"下一级",null,(double)(rmLine%LEVEL_UP)/(double)LEVEL_UP, g);
		
	}
	

}
