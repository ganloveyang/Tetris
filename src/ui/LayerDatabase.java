package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

public class LayerDatabase extends Layer{

	//TODO �����ļ�
	private static final int MAX_ROW=5;
	/**
	 * ��ʼY����
	 */
	private static  int STATR_Y=0;
	/**
	 * ֵ���⾶
	 */
	private static final int RECT_H=IMG_RECT_H+4;
	/**
	 * ���
	 * 
	 */
	private static  int SPA=0;
	
	public LayerDatabase(int x, int y, int w, int h) {
		super(x, y, w, h);
		//�����¼���Ƽ��
		SPA=(this.h-RECT_H*5-(PADDING<<1)-Img.DB.getHeight(null))/MAX_ROW;
		//������ʼY����
		STATR_Y=PADDING+Img.DB.getHeight(null)+SPA;
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//���Ʊ���
		g.drawImage(Img.DB, this.x+PADDING, this.y+PADDING, null);
		//������ݶ���
		List<Player> players=this.dto.getDbRecode();
		//������ڷ���
		int nowPoint=this.dto.getNowPoint();
		//ѭ�����Ƽ�¼
		for(int i=0;i<MAX_ROW;i++){
			//���һ����Ҽ�¼
			Player pla=players.get(i);
			//��ø÷���
			int recodePoint=pla.getPoint();
			//�������ڷ������¼������ֵ
			double percent=(double)nowPoint/recodePoint;
			//������Ƽ�¼����ֵ��Ϊ100%
			percent=percent>1?1:percent;
			//���Ƶ�����¼
			this.drawRect(STATR_Y+i*(RECT_H+SPA), 
					"NO DATA", Integer.toString(recodePoint), 
					percent,g);
		}
	}

}
