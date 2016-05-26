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
 * ������Ҽ����¼�
 * ���ƻ���
 * ������Ϸ�߼�
 * @author gandi
 *
 */
public class GameControl {
	/**
	 * ���ݷ��ʽӿ�A
	 */
	private Data dataA;
	/**
	 * ���ݷ��ʽӿ�B
	 */
	private Data dataB;
	
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
		//�����ݽӿ�A������ݿ��¼
		this.dataA=new DataBase();
		//�������ݿ��¼����Ϸ
		this.gameService.setDbRecode(dataA.loadData());
		//�����ݽӿ�B��ñ��ش��̼�¼
		this.dataB=new DataDisk();
		//���ñ��ش��̼�¼����Ϸ
		this.gameService.setDiskRecode(dataB.loadData());
	}
	
	/**
	 * ��������������ϣ�
	 */
	public void KeyUp() {
		
		this.gameService.KeyUp();
		this.panelGame.repaint();
	}
	/**
	 * ��������������£�
	 */
	public void KeyDown() {
		this.gameService.KeyDown();
		this.panelGame.repaint();
	}
	/**
	 * ���������������
	 */
	public void KeyLeft() {
		this.gameService.KeyLeft();
		this.panelGame.repaint();
	}
	/**
	 * ��������������ң�
	 */
	public void KeyRight() {
	
		this.gameService.KeyRight();
		this.panelGame.repaint();
	}

	//TODO =======================����ר��===================
	public void testLevelUp() {
		
		this.gameService.testLevelUp();
		this.panelGame.repaint();
	}

}
