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
	 * �������
	 */
	public void KeyUp();
	/**
	 * �������
	 */
	public void KeyDown();
	/**
	 * ���������
	 */
	public void KeyLeft();
	/**
	 * �������
	 */
	public void KeyRight();
	/**
	 * ����
	 */
	public void keyFunUp();
	/**
	 * ���
	 */
	public void keyFunDown();
	/**
	 * ����
	 */
	public void keyFunLeft();
	/**
	 * ԲȦ
	 */
	public void keyFunRight();
	/**
	 * �������ݶ���
	 * @param players
	 */
	public void setDbRecode(List<Player> players);
	public void setDiskRecode(List<Player> players);
	
}
