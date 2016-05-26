/**
 * 
 */
package service;

import java.awt.Point;
import java.util.List;
import java.util.Random;
import dto.GameDto;
import dto.Player;
import entity.GameAct;

/**
 * 游戏业务逻辑
 * @author gandi
 *
 */
public class GameService {

	private GameDto dto;
	/**
	 * 随机数生成器
	 */
	private Random random=new Random();
	/**
	 * 方块种类个数
	 */
	private static final int MAX_TYPE=6;
	
	public GameService(GameDto dto){
		this.dto=dto;
		GameAct act=new GameAct(random.nextInt(MAX_TYPE));
		dto.setGameAct(act);
	}
	/**
	 * 方块操作（上）
	 */
	public void KeyUp() {
//		if(this.canMove(0, -1)){
//			this.dto.getGameAct().move(0, -1);
//		}
		
	this.dto.getGameAct().round(this.dto.getGameMap());
	}
	/**
	 * 方块操作（下）
	 */
	public void KeyDown() {
		//方向向下移动，并判断是否移动成功
		if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())){
			return;
		}
		//获得游戏地图对象
		boolean[][] map=this.dto.getGameMap();
		//获得方块对象
		Point[] act=this.dto.getGameAct().getActPoints();
		//将方块堆积到地图数组
		for(int i=0;i<act.length;i++){
			map[act[i].x][act[i].y]=true;
		}
		//TODO 判断是否可以消行
		
		//TODO 消行操作
		
		//TODO 算分操作
		//TODO 判断是否升级
		//TODO 升级
		//创建下一个方块
		this.dto.getGameAct().init(this.dto.getNext());
		//随机生成下一个方块
		this.dto.setNext(random.nextInt(MAX_TYPE));
		
		 
		
	}
	/**
	 * 方块操作（左）
	 */
	public void KeyLeft() {
		
			this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());

		// TODO Auto-generated method stub
		
	}
	/**
	 * 方块操作（右）
	 */
	public void KeyRight() {
	
			this.dto.getGameAct().move(1, 0,this.dto.getGameMap());
	}
	/**
	 * 判断是否移动
	 * @param moveX
	 * @param moveY
	 * @return
	 */
	private boolean canMove(int moveX,int moveY){
		
		Point[] nowPoints=this.dto.getGameAct().getActPoints();
		for(int i=0;i<nowPoints.length;i++){
			int newX=nowPoints[i].x+moveX;
			int newY=nowPoints[i].y+moveY;
			//TODO 配置
			if(newX<0||newX>9||newY<0||newY>17){
				return false;
			}
		}
		return true;
	}
	//TODO =======================测试专用===================
	public void testLevelUp() {
		int point=this.dto.getNowPoint();
		int rmLine=this.dto.getNowRemoveLine();
		int lv=this.dto.getNowLevel();
		point+=10;
		rmLine+=1;
		if(rmLine%20==0){
			lv+=1;
		}
		this.dto.setNowPoint(point);
		this.dto.setNowLevel(lv);
		this.dto.setNowRemoveLine(rmLine);
	}
	
	public void setDbRecode(List<Player> players){
		this.dto.setDbRecode(players);
	}
	public void setDiskRecode(List<Player> players){
		this.dto.setDiskRecode(players);
	}
}
