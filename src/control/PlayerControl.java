/**
 * 
 */
package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author gandi
 *
 */
public class PlayerControl extends KeyAdapter{

	private GameControl gameControl;
	
	public PlayerControl(GameControl gameControl){
		this.gameControl=gameControl;
	}
	

	/* (non-Javadoc)
	 * 键盘按下事件
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		this.gameControl.actionByKeyCode(e.getKeyCode());
		
	}

	

	

}
