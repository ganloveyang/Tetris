package main;

import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import service.GameService;
import ui.JFrameGame;
import ui.JPanelGame;

public class Main {
	
	public static void main(String args[]){
		//������Ϸ����Դ
		GameDto dto=new GameDto();
		//������Ϸ���
		JPanelGame panel=new JPanelGame(dto);	
		//������Ϸ�߼��飨��װ��Ϸ����Դ��
		GameService service=new GameService(dto);
		//������Ϸ��������������Ϸ�������Ϸ�߼��飩
		GameControl gameControl = null;
		try {
			gameControl = new GameControl(panel,service);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//������ҿ�������������Ϸ��������
		PlayerControl playerControl=new PlayerControl(gameControl);
		//��װ��ҿ�����
		panel.setGameControl(playerControl);
		//������Ϸ���ڣ���װ��Ϸ���
		new JFrameGame(panel);
		
	}
	
}
