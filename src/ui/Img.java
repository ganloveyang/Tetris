/**
 * 
 */
package ui;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author gandi
 *
 */
public class Img {

	private Img(){};
	/**
	 * ����ǩ��
	 */
	public static Image SIGN=new ImageIcon("graphics/string/sign.png").getImage();
	/**
	 * ����ͼƬ
	 */
	public static Image WINDOW=new ImageIcon("graphics/window/Window.png").getImage();
	/**
	 * ����ͼƬ260 36
	 */
	public static final Image NUMBER=new ImageIcon("graphics/string/num.png").getImage();
	/**
	 * ����ֵ��
	 */
	public static final Image RECT=new ImageIcon("graphics/window/rect.png").getImage();
	/**
	 * ���ݿⴰ�ڱ���
	 */
	public static Image DB=new ImageIcon("graphics/string/db.png").getImage();
	/**
	 * ���ؼ�¼���ڱ���
	 */
	public static Image DISK=new ImageIcon("graphics/string/disk.png").getImage();
	/**
	 * ����ͼƬ
	 */
	public static Image ACT=new ImageIcon("graphics/game/rect.png").getImage();
	/**
	 * �ȼ�����
	 */
	public static final Image LV=new ImageIcon("graphics/string/level.png").getImage();
	/**
	 * ���ڱ���ͼƬ��������
	 */
	public static final Image POINT=new ImageIcon("graphics/string/point.png").getImage();
	
	/**
	 * ���ڱ��⣨���У�
	 */
	public static final Image RMLINE=new ImageIcon("graphics/string/rmline.png").getImage();
	/**
	 * ��һ��ͼƬ����
	 */
	public static Image[] NEXT_ACT;
	
	static {
		NEXT_ACT =new Image[7];
		for(int i=0;i<NEXT_ACT.length;i++){
			 NEXT_ACT[i]=new ImageIcon("graphics/game/"+i+".png").getImage();
		}
	}
}
