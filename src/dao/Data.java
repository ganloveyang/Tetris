package dao;

import java.util.List;

import dto.Player;
/**
 * ���ݳ־ò�ӿ�
 * @author gandi
 *
 */
public interface Data {
	/**
	 * ��ȡ����
	 * @return
	 */
	public List<Player> loadData();
	/**
	 * �洢����
	 * @param players
	 */
	public void saveData(Player players);
	
}
