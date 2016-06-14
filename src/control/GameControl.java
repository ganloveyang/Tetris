/**
 * 
 */
package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import service.GameService;
import service.GameTetris;
import ui.JPanelGame;
import ui.cfg.FrameConfig;

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
	 * ��Ϸ���ƴ���
	 */
	private FrameConfig frameConfig;
	
	/**
	 * ��Ϸ��Ϊ����
	 */
	private Map<Integer,Method> actionList;
	
	private GameControl gameControl;
	
	public GameControl(JPanelGame panelGame,GameTetris gameService) throws Exception, SecurityException{
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
		//��ȡ�û���������
		this.setControlConfig();
		//��ʼ���û����ô���
		this.frameConfig=new FrameConfig(this);
	}
	/**
	 * ��ȡ�û���������
	 */
	private void setControlConfig(){
		//�����������뷽������ӳ������
		this.actionList=new HashMap<Integer,Method>();
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("data/control.dat"));
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> cfgSet=(HashMap<Integer,String>)ois.readObject();
			Set<java.util.Map.Entry<Integer,String>> entryset=cfgSet.entrySet();
			for(java.util.Map.Entry<Integer,String> e:entryset){
				actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
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
	/**
	 * ��ʾ��ҿ��ƴ���
	 */
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
		
	}
	/**
	 * �Ӵ��ڹر��¼�
	 */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}


}
