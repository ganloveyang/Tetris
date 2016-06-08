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

	private JButton btnOk=new JButton("确定");
	
	private JButton btnCancel=new JButton("取消");
	
	private JButton btnUser=new JButton("应用");
	
	private TextCtrl test =new TextCtrl(0,50,64,20);
	
	private final static Image IMG_PSP=new ImageIcon("data/psp.jpg").getImage();
	
	public FrameConfig(){
		//设置布局管理器为“边界布局”
		this.setLayout(new BorderLayout());
		//添加主面板
		this.add(createMainPanel(),BorderLayout.CENTER);
		//添加按钮面板
		this.add(createButtonPanel(),BorderLayout.SOUTH);
		//设置不能改变大小
		this.setResizable(false);
		//设置窗口大小
		this.setSize(580,350);
		//TODO p）测试用
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
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
	jp.add(test);
		return jp;
	}


	/**
	 * 创建按钮面板
	 * @return
	 */
	private Component createButtonPanel() {
		//创建按钮面板，流式布局
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
