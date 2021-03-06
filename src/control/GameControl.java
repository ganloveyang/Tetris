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
 * 接受玩家键盘事件
 * 控制画面
 * 控制游戏逻辑
 * @author gandi
 *
 */
public class GameControl {
	/**
	 * 数据访问接口A
	 */
	private Data dataA;
	/**
	 * 数据访问接口B
	 */
	private Data dataB;
	
	/**
	 * 游戏界面层
	 */
	private JPanelGame panelGame;
	/**
	 * 游戏逻辑层
	 */
	private GameService gameService;
	/**
	 * 游戏控制窗口
	 */
	private FrameConfig frameConfig;
	
	/**
	 * 游戏行为控制
	 */
	private Map<Integer,Method> actionList;
	
	private GameControl gameControl;
	
	public GameControl(JPanelGame panelGame,GameTetris gameService) throws Exception, SecurityException{
		this.panelGame=panelGame;
		this.gameService=gameService;
		//从数据接口A获得数据库记录
		DataInterfaceConfig cfgDataA=GameConfig.getDataConfig().getDataA();
		//获得类对象
		this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
		//设置数据库记录到游戏
		this.gameService.setDbRecode(dataA.loadData());
		//从数据接口B获得本地磁盘记录
		this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
		//设置本地磁盘记录到游戏
		this.gameService.setDiskRecode(dataB.loadData());
		//读取用户控制设置
		this.setControlConfig();
		//初始化用户配置窗口
		this.frameConfig=new FrameConfig(this);
	}
	/**
	 * 读取用户控制设置
	 */
	private void setControlConfig(){
		//创建键盘码与方法名的映射数组
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
	 * 创建数据对象
	 * @param cfg
	 * @throws Exception 
	 */
	private Data createDataObject(DataInterfaceConfig cfg ) throws  Exception{
		try {
			//获得类对象
			Class<?> cls=Class.forName(cfg.getClassName());
			//获得构造器
			Constructor<?> ctr=cls.getConstructor(HashMap.class);
			//创建对象
			return (Data)ctr.newInstance(cfg.getParam());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 根据玩家控制来决定行为
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
	 * 显示玩家控制窗口
	 */
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
		
	}
	/**
	 * 子窗口关闭事件
	 */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}


}
