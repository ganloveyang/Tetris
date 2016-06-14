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

	private JButton btnOk=new JButton("确定");
	
	private JButton btnCancel=new JButton("取消");
	
	private JButton btnUser=new JButton("应用");
	
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
		//获得游戏控制器对象
		this.gameControl=gameControl;
		//设置布局管理器为“边界布局”
		this.setLayout(new BorderLayout());
		this.setTitle("设置");
		//初始化按键输入框
		this.initKeyText();
		//添加主面板
		this.add(createMainPanel(),BorderLayout.CENTER);
		//添加按钮面板
		this.add(createButtonPanel(),BorderLayout.SOUTH);
		//设置不能改变大小
		this.setResizable(false);
		//设置窗口大小
		this.setSize(580,350);
	}

	
	/**
	 * 初始化按键输入框
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
	 * 创建主面板(选项卡面板)
	 * @return
	 */
	private JTabbedPane createMainPanel() {
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("控制设置", this.createControlPanel());
		jtp.addTab("皮肤设置", new JLabel("皮肤"));
		return jtp;
	}
	
	/**
	 * 玩家控制面板
	 * @return
	 */
	private JPanel createControlPanel() {
	JPanel jp=new JPanel(){	
		
		@Override
		public void paintComponent(Graphics g){
			g.drawImage(IMG_PSP, 0, 0, null);
		}
	};
	//设置布局管理器
	jp.setLayout(null);
	for(int i=0;i<keyTexts.length;i++){
		jp.add(keyTexts[i]);
	}
	
		return jp;
	}
	/**
	 * 确定按钮
	 */
	private void okButtonEvent(){
		this.writeConfig();
		this.setVisible(false);
	}
	/**
	 * 取消按钮
	 */
	private void cancelButtonEvent(){
		this.setVisible(false);
	}
	/**
	 * 写入游戏配置
	 */
	private boolean writeConfig(){
		HashMap<Integer,String> keySet=new HashMap<Integer,String>();
		for(int i=0;i<this.keyTexts.length;i++){
			int keyCode=this.keyTexts[i].getKeyCode();
			if(keyCode==0){
				this.errorMsg.setText("错误按键");
				return false;
			}
			keySet.put(keyCode, this.keyTexts[i].getMethodName());
		}
		if(keySet.size()!=8){
			this.errorMsg.setText("重复按键");
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
	 * 创建按钮面板
	 * @return
	 */
	private Component createButtonPanel() {
		//创建按钮面板，流式布局
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//给确定按钮增加事件监听
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
