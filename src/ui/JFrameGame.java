package ui;


import javax.swing.JFrame;

import config.ConfigFactory;
import config.GameConfig;

public class JFrameGame extends JFrame{
	

	public JFrameGame(JPanelGame panelGame){
		//�����Ϸ����
		GameConfig cfg=ConfigFactory.getGameConfig();
		//���ñ���
		this.setTitle(cfg.getTitle());
		//����Ĭ�Ϲر����ԣ����������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //���ô��ڴ�С
		this.setSize(cfg.getWidth(), cfg.getHeight());
		//�������û��ı��С
		this.setResizable(false);
		//���ھ�������
		this.setLocationRelativeTo(null);
		//������һ�ַ���
//		Toolkit toolkit=Toolkit.getDefaultToolkit();
//		Dimension screen=toolkit.getScreenSize();
//		int x=(screen.width-this.getWidth())>>1;
//		int y=(screen.height-this.getHeight())>>1-cfg.getWindowUp;
//		this.setLocation(x, y);
		//����Ĭ��panel
		this.setContentPane(panelGame);
		//Ĭ�ϸô���Ϊ��ʾ
		this.setVisible(true);
	}

}
