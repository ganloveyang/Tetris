/**
 * 
 */
package control;

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
	public GameControl(JPanelGame panelGame,GameService gameService){
		this.panelGame=panelGame;
		this.gameService=gameService;
		//从数据接口A获得数据库记录
		this.dataA=new DataBase();
		//设置数据库记录到游戏
		this.gameService.setDbRecode(dataA.loadData());
		//从数据接口B获得本地磁盘记录
		this.dataB=new DataDisk();
		//设置本地磁盘记录到游戏
		this.gameService.setDiskRecode(dataB.loadData());
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
