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
	 * 游戏行为控制
	 */
	private Map<Integer,Method> actionList;
	
	public GameControl(JPanelGame panelGame,GameService gameService) throws Exception, SecurityException{
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
		//初始化游戏行为
		actionList=new HashMap<Integer,Method>();
		//TODO 配置文件
		actionList.put(69, this.gameService.getClass().getMethod("KeyUp"));
		actionList.put(68, this.gameService.getClass().getMethod("KeyDown"));
		actionList.put(83, this.gameService.getClass().getMethod("KeyLeft"));
		actionList.put(70, this.gameService.getClass().getMethod("KeyRight"));
		actionList.put(38, this.gameService.getClass().getMethod("testLevelUp"));
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


}
