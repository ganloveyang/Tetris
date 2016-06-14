/**
 * 
 */
package ui.cfg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import control.GameControl;

/**
 * @author gandi
 *
 */
public class FrameConfig extends JFrame {

	private JButton btnOk=new JButton("ȷ��");
	
	private JButton btnCancel=new JButton("ȡ��");
	
	private JButton btnUser=new JButton("Ӧ��");
	
	private TextCtrl[] keyTexts =new TextCtrl[8];
	
	private JLabel errorMsg=new JLabel();
	
	private GameControl gameControl;
	
	private final static Image IMG_PSP=new ImageIcon("data/psp.jpg").getImage();
	
	private final static String[] METHOD_NAMES={
		"KeyRight","KeyUp","KeyLeft","KeyDown",
		"keyFunLeft","keyFunUp","keyFunRight","keyFunDown"
	};
	private final static String PATH="data/control.dat";
	
	public FrameConfig(GameControl gameControl){
		//�����Ϸ����������
		this.gameControl=gameControl;
		//���ò��ֹ�����Ϊ���߽粼�֡�
		this.setLayout(new BorderLayout());
		this.setTitle("����");
		//��ʼ�����������
		this.initKeyText();
		//��������
		this.add(createMainPanel(),BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(createButtonPanel(),BorderLayout.SOUTH);
		//���ò��ܸı��С
		this.setResizable(false);
		//���ô��ڴ�С
		this.setSize(580,350);
	}

	
	/**
	 * ��ʼ�����������
	 */
	private void initKeyText() {
		int x=20;
		int y=50;
		int w=64;
		int h=20;
		for(int i=0;i<4;i++){
			keyTexts[i]=new TextCtrl(x,y,w,h,METHOD_NAMES[i]);
			y+=28;
		}
		x=490;
		y=56;
		for(int i=4;i<8;i++){
			keyTexts[i]=new TextCtrl(x,y,w,h,METHOD_NAMES[i]);
			y+=28;
		}
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(PATH));
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> cfgSet=(HashMap<Integer,String>)ois.readObject();
			ois.close();
			Set<java.util.Map.Entry<Integer,String>> entryset=cfgSet.entrySet();
			for(java.util.Map.Entry<Integer,String> e:entryset){
				System.out.print(e.getKey()+"++"+e.getValue());
				for(TextCtrl tc:keyTexts){
					if(tc.getMethodName().equals(e.getValue())){
						tc.setKeyCode(e.getKey());
					}
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
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
	for(int i=0;i<keyTexts.length;i++){
		jp.add(keyTexts[i]);
	}
	
		return jp;
	}
	/**
	 * ȷ����ť
	 */
	private void okButtonEvent(){
		this.writeConfig();
		this.setVisible(false);
	}
	/**
	 * ȡ����ť
	 */
	private void cancelButtonEvent(){
		this.setVisible(false);
	}
	/**
	 * д����Ϸ����
	 */
	private boolean writeConfig(){
		HashMap<Integer,String> keySet=new HashMap<Integer,String>();
		for(int i=0;i<this.keyTexts.length;i++){
			int keyCode=this.keyTexts[i].getKeyCode();
			if(keyCode==0){
				this.errorMsg.setText("���󰴼�");
				return false;
			}
			keySet.put(keyCode, this.keyTexts[i].getMethodName());
		}
		if(keySet.size()!=8){
			this.errorMsg.setText("�ظ�����");
			return false;
		}
		try{
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
		}catch(Exception e){
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		this.errorMsg.setText(null);
		return true;
	}

	/**
	 * ������ť���
	 * @return
	 */
	private Component createButtonPanel() {
		//������ť��壬��ʽ����
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//��ȷ����ť�����¼�����
		this.btnOk.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(writeConfig()){
					setVisible(false);
					gameControl.setOver();
				}
			}
		});
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.errorMsg);
		jp.add(this.btnOk);
		this.btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				gameControl.setOver();
			}
		});
		jp.add(this.btnCancel);
		this.btnUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				writeConfig();;
			}
		});
		jp.add(this.btnUser);
		return jp;
	}


}
