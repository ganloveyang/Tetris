/**
 * 
 */
package ui.cfg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * @author gandi
 *
 */
public class TextCtrl extends JTextField {

	public TextCtrl(int x, int y,int w,int h){
		this.setBounds(x,y,w,h);
		
		this.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){}
			/**
			 * �����ɿ�
			 */
			public void keyReleased(KeyEvent e){
				String str=KeyEvent.getKeyText(e.getKeyCode());
				setText(str);
			}
			public void keyPressed(KeyEvent e){}
		});
	}
}
