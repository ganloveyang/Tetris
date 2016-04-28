/**
 * 
 */
package control;

import service.GameService;
import ui.JPanelGame;

/**
 * ������Ҽ����¼�
 * ���ƻ���
 * ������Ϸ�߼�
 * @author gandi
 *
 */
public class GameControl {
	/**
	 * ��Ϸ�����
	 */
	private JPanelGame panelGame;
	/**
	 * ��Ϸ�߼���
	 */
	private GameService gameService;
	public GameControl(JPanelGame panelGame,GameService gameService){
		this.panelGame=panelGame;
		this.gameService=gameService;
	}
	
	/**
	 * ��������������ϣ�
	 */
	public void KeyUp() {
		// TODO Auto-generated method stub
		this.gameService.KeyUp();
		this.panelGame.repaint();
	}
	/**
	 * ��������������£�
	 */
	public void KeyDown() {
		// TODO Auto-generated method stub
		this.gameService.KeyDown();
		this.panelGame.repaint();
	}
	/**
	 * ���������������
	 */
	public void KeyLeft() {
		// TODO Auto-generated method stub
		this.gameService.KeyLeft();
		this.panelGame.repaint();
	}
	/**
	 * ��������������ң�
	 */
	public void KeyRight() {
		// TODO Auto-generated method stub
		this.gameService.KeyRight();
		this.panelGame.repaint();
	}

	//TODO =======================����ר��===================
	public void testLevelUp() {
		// TODO Auto-generated method stub
		this.gameService.testLevelUp();
		this.panelGame.repaint();
	}

}
