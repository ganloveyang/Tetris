package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

public class LayerDatabase extends Layer{

	//TODO 配置文件
	private static final int MAX_ROW=5;
	/**
	 * 起始Y坐标
	 */
	private static  int STATR_Y=0;
	/**
	 * 值槽外径
	 */
	private static final int RECT_H=IMG_RECT_H+4;
	/**
	 * 间距
	 * 
	 */
	private static  int SPA=0;
	
	public LayerDatabase(int x, int y, int w, int h) {
		super(x, y, w, h);
		//计算记录绘制间距
		SPA=(this.h-RECT_H*5-(PADDING<<1)-Img.DB.getHeight(null))/MAX_ROW;
		//计算起始Y坐标
		STATR_Y=PADDING+Img.DB.getHeight(null)+SPA;
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//绘制标题
		g.drawImage(Img.DB, this.x+PADDING, this.y+PADDING, null);
		//获得数据对象
		List<Player> players=this.dto.getDbRecode();
		//获得现在分数
		int nowPoint=this.dto.getNowPoint();
		//循环绘制记录
		for(int i=0;i<MAX_ROW;i++){
			//获得一条玩家记录
			Player pla=players.get(i);
			//获得该分数
			int recodePoint=pla.getPoint();
			//计算现在分数与记录分数比值
			double percent=(double)nowPoint/recodePoint;
			//如果已破记录，比值设为100%
			percent=percent>1?1:percent;
			//绘制单条记录
			this.drawRect(STATR_Y+i*(RECT_H+SPA), 
					"NO DATA", Integer.toString(recodePoint), 
					percent,g);
		}
	}

}
