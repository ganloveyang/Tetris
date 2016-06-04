/**
 * 
 */
package control;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dao.DataBase;
import dao.DataDisk;
import dao.DataTest;
import service.GameService;
import ui.JPanelGame;

/**
 * ������Ҽ����¼�
 * ���ƻ���
 * ������Ϸ�߼�
 * @author gandi
 *
 */
public class GameControl {
	/**
	 * ���ݷ��ʽӿ�A
	 */
	private Data dataA;
	/**
	 * ���ݷ��ʽӿ�B
	 */
	private Data dataB;
	
	/**
	 * ��Ϸ�����
	 */
	private JPanelGame panelGame;
	/**
	 * ��Ϸ�߼���
	 */
	private GameService gameService;
	public GameControl(JPanelGame panelGame,GameService gameService) throws Exception, SecurityException{
		this.panelGame=panelGame;
		this.gameService=gameService;
		//�����ݽӿ�A������ݿ��¼
		DataInterfaceConfig cfgDataA=GameConfig.getDataConfig().getDataA();
		//��������
		this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
		//�������ݿ��¼����Ϸ
		this.gameService.setDbRecode(dataA.loadData());
		//�����ݽӿ�B��ñ��ش��̼�¼
		this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
		//���ñ��ش��̼�¼����Ϸ
		this.gameService.setDiskRecode(dataB.loadData());
	}
	/**
	 * �������ݶ���
	 * @param cfg
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private Data createDataObject(DataInterfaceConfig cfg ) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, Exception{
		try {
			//��������
			Class<?> cls=Class.forName(cfg.getClassName());
			//��ù�����
			Constructor<?> ctr=cls.getConstructor(HashMap.class);
			//��������
			return (Data)ctr.newInstance(cfg.getParam());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * ��������������ϣ�
	 */
	public void KeyUp() {
		
		this.gameService.KeyUp();
		this.panelGame.repaint();
	}
	/**
	 * ��������������£�
	 */
	public void KeyDown() {
		this.gameService.KeyDown();
		this.panelGame.repaint();
	}
	/**
	 * ���������������
	 */
	public void KeyLeft() {
		this.gameService.KeyLeft();
		this.panelGame.repaint();
	}
	/**
	 * ��������������ң�
	 */
	public void KeyRight() {
	
		this.gameService.KeyRight();
		this.panelGame.repaint();
	}

	//TODO =======================����ר��===================
	public void testLevelUp() {
		
		this.gameService.testLevelUp();
		this.panelGame.repaint();
	}

}
