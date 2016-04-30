/**
 * 
 */
package control;

import dao.Data;
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
		this.dataA=new DataTest();
		//�������ݿ��¼����Ϸ
		this.gameService.setDbRecode(dataA.loadData());
		//�����ݽӿ�B��ñ��ش��̼�¼
		this.dataB=new DataTest();
		//���ñ��ش��̼�¼����Ϸ
		this.gameService.setDiskRecode(dataB.loadData());
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
