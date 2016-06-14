package main;

import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import service.GameTetris;
import ui.JFrameGame;
import ui.JPanelGame;

public class Main {
	
	public static void main(String args[]) throws Exception{
		//������Ϸ����Դ
		GameDto dto=new GameDto();
		//������Ϸ���
		JPanelGame panel=new JPanelGame(dto);	
		//������Ϸ�߼��飨��װ��Ϸ����Դ��
		GameTetris service=new GameTetris(dto);
		//������Ϸ��������������Ϸ�������Ϸ�߼��飩
		GameControl gameControl = new GameControl(panel,service);	
		//����Ϸ���������󽻸�Panel
		panel.setGameControl(gameControl);
		//������ҿ�������������Ϸ��������
		PlayerControl playerControl=new PlayerControl(gameControl);
		//��װ��ҿ�����
		panel.setGameControl(playerControl);
		//������Ϸ���ڣ���װ��Ϸ���
		new JFrameGame(panel);
		
	}
	
}
