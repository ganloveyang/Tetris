/**
 * 
 */
package control;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
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
	/**
	 * ��Ϸ��Ϊ����
	 */
	private Map<Integer,Method> actionList;
	
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
		//��ʼ����Ϸ��Ϊ
		actionList=new HashMap<Integer,Method>();
		//TODO �����ļ�
		actionList.put(69, this.gameService.getClass().getMethod("KeyUp"));
		actionList.put(68, this.gameService.getClass().getMethod("KeyDown"));
		actionList.put(83, this.gameService.getClass().getMethod("KeyLeft"));
		actionList.put(70, this.gameService.getClass().getMethod("KeyRight"));
		actionList.put(38, this.gameService.getClass().getMethod("testLevelUp"));
	}
	/**
	 * �������ݶ���
	 * @param cfg
	 * @throws Exception 
	 */
	private Data createDataObject(DataInterfaceConfig cfg ) throws  Exception{
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
	 * ������ҿ�����������Ϊ
	 * @param keyCode
	 */
	public void actionByKeyCode(int keyCode) {
	
		try {
			if(this.actionList.containsKey(keyCode)){
				this.actionList.get(keyCode).invoke(this.gameService);
				} 
			}catch (Exception e) {
				e.printStackTrace();
		}
		this.panelGame.repaint();
	}


}
