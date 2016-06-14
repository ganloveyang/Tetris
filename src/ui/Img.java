/**
 * 
 */
package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

/**
 * ͼƬ�ࣨ��Ϸ�и���ͼƬ�ļ��ϣ�
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
	 * ��Ӱ
	 */
	public static final Image SHODOW=new ImageIcon("graphics/game/shodow.png").getImage();
	
	/**
	 * ��ʼ��ť
	 */
	public static final ImageIcon BTN_START=new ImageIcon("graphics/string/start.png");
	
	/**
	 * ���ð�ť
	 */
	public static final ImageIcon BTN_CONFIG=new ImageIcon("graphics/string/config.png");
	/**
	 * ��һ��ͼƬ����
	 */
	public static Image[] NEXT_ACT;
	
	public static List<Image> BG_LIST;
	
	static {
		//��һ������ͼƬ
		NEXT_ACT =new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for(int i=0;i<NEXT_ACT.length;i++){
			 NEXT_ACT[i]=new ImageIcon("graphics/game/"+i+".png").getImage();
		}
		//����ͼƬ����
		File dir=new File("graphics/background");
		File[] files=dir.listFiles();
		BG_LIST =new ArrayList<Image>();
		for(File file:files){
			if(file.isDirectory()){
				continue;
			}
			BG_LIST.add(new ImageIcon(file.getPath()).getImage());
		
		}
	}
}
