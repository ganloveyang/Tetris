package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerLevel extends Layer {
	/**
	 * ����ͼƬ
	 */
	private static final Image IMG_LV=new ImageIcon("graphics/string/level.png").getImage();
	/**
	 * ����ͼƬ260 36
	 */
	private static final Image IMG_NUMBER=new ImageIcon("graphics/string/num.png").getImage();
	
	private static final int IMG_NUMBER_W=26;
	private static final int IMG_NUMBER_H=36;
	
	
	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//���ڱ���
		g.drawImage(IMG_LV, this.x+PADDING, this.y+PADDING, null);
		//��ʾ�ȼ�
		this.drawNumber(32, 32, 2, g);
	}

	/**
	 * ��ʾ����
	 */
	private void drawNumber(int x,int y,int num,Graphics g){
		//������number�е�ÿһλȡ��
	
		g.drawImage(IMG_NUMBER, 
				this.x+x, this.y+y, this.x+x+IMG_NUMBER_W, this.y+y+IMG_NUMBER_H, 
				num*IMG_NUMBER_W, 0,
				(num+1)*IMG_NUMBER_W, IMG_NUMBER_H, null);
	}
}
