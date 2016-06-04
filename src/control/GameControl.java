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
	}
	/**
	 * 创建数据对象
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
	 * 控制器方向键（上）
	 */
	public void KeyUp() {
		
		this.gameService.KeyUp();
		this.panelGame.repaint();
	}
	/**
	 * 控制器方向键（下）
	 */
	public void KeyDown() {
		this.gameService.KeyDown();
		this.panelGame.repaint();
	}
	/**
	 * 控制器方向键（左）
	 */
	public void KeyLeft() {
		this.gameService.KeyLeft();
		this.panelGame.repaint();
	}
	/**
	 * 控制器方向键（右）
	 */
	public void KeyRight() {
	
		this.gameService.KeyRight();
		this.panelGame.repaint();
	}

	//TODO =======================测试专用===================
	public void testLevelUp() {
		
		this.gameService.testLevelUp();
		this.panelGame.repaint();
	}

}
