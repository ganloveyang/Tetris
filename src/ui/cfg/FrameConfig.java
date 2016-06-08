/**
 * 
 */
package ui.cfg;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author gandi
 *
 */
public class FrameConfig extends JFrame {

	private JButton btnOk=new JButton("ȷ��");
	
	private JButton btnCancel=new JButton("ȡ��");
	
	private JButton btnUser=new JButton("Ӧ��");
	
	private TextCtrl test =new TextCtrl(0,50,64,20);
	
	private final static Image IMG_PSP=new ImageIcon("data/psp.jpg").getImage();
	
	public FrameConfig(){
		//���ò��ֹ�����Ϊ���߽粼�֡�
		this.setLayout(new BorderLayout());
		//��������
		this.add(createMainPanel(),BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(createButtonPanel(),BorderLayout.SOUTH);
		//���ò��ܸı��С
		this.setResizable(false);
		//���ô��ڴ�С
		this.setSize(580,350);
		//TODO p��������
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	
	/**
	 * ���������(ѡ����)
	 * @return
	 */
	private JTabbedPane createMainPanel() {
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("��������", this.createControlPanel());
		jtp.addTab("Ƥ������", new JLabel("Ƥ��"));
		return jtp;
	}
	
	/**
	 * ��ҿ������
	 * @return
	 */
	private JPanel createControlPanel() {
	JPanel jp=new JPanel(){	
		
		@Override
		public void paintComponent(Graphics g){
			g.drawImage(IMG_PSP, 0, 0, null);
		}
	};
	//���ò��ֹ�����
	jp.setLayout(null);
	jp.add(test);
		return jp;
	}


	/**
	 * ������ť���
	 * @return
	 */
	private Component createButtonPanel() {
		//������ť��壬��ʽ����
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp.add(this.btnOk);
		jp.add(this.btnCancel);
		jp.add(this.btnUser);
		return jp;
	}
public static void main(String[] args){
	new FrameConfig();
}

}
