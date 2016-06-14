/**
 * 
 */
package service;

import java.util.List;

import dto.Player;

/**
 * @author gandi
 *
 */
public interface GameService {

	/**
	 * 方向键上
	 */
	public void KeyUp();
	/**
	 * 方向键下
	 */
	public void KeyDown();
	/**
	 * 方向键上左
	 */
	public void KeyLeft();
	/**
	 * 方向键右
	 */
	public void KeyRight();
	/**
	 * 三角
	 */
	public void keyFunUp();
	/**
	 * 大叉
	 */
	public void keyFunDown();
	/**
	 * 方块
	 */
	public void keyFunLeft();
	/**
	 * 圆圈
	 */
	public void keyFunRight();
	/**
	 * 设置数据对象
	 * @param players
	 */
	public void setDbRecode(List<Player> players);
	public void setDiskRecode(List<Player> players);
	
}
