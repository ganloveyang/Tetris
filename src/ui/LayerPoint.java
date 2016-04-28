package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerPoint extends Layer {
	/**
	 * ���ڱ���ͼƬ��������
	 */
	private static final Image IMG_POINT=new ImageIcon("graphics/string/point.png").getImage();
	private static final int POINT_Y=PADDING;
	/**
	 * ���ڱ���ͼƬ�����У�
	 */
	private static final Image IMG_RMLINE=new ImageIcon("graphics/string/rmline.png").getImage();
	
	private static final int RMLIN_Y=IMG_RMLINE.getHeight(null)+(PADDING<<1);
	/**
	 * ���������λ��
	 */
	private static final int POINT_BIT=5;
	
	private static  int POINT_X;
	
	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		//��ʼ��������ʾ��X����
		POINT_X=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//���ڱ���(����)
		g.drawImage(IMG_POINT, x+PADDING, this.y+POINT_Y, null);
		this.drawNumber(128, POINT_Y,this.dto.getNowLevel(),5, g);
		//���ڱ���(����)
		g.drawImage(IMG_RMLINE, x+PADDING, this.y+RMLIN_Y, null);
		this.drawNumber(128, RMLIN_Y,this.dto.getNowLevel(),5, g);

	}

}
