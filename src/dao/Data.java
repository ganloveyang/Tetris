package dao;

import java.util.List;

import dto.Player;
/**
 * 数据持久层接口
 * @author gandi
 *
 */
public interface Data {
	/**
	 * 读取数据
	 * @return
	 */
	public List<Player> loadData();
	/**
	 * 存储数据
	 * @param players
	 */
	public void saveData(Player players);
	
}
