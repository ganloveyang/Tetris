/**
 * 
 */
package service;

import java.awt.Point;

import dto.GameDto;
import entity.GameAct;

/**
 * ��Ϸҵ���߼�
 * @author gandi
 *
 */
public class GameService {

	private GameDto dto;
	
	public GameService(GameDto dto){
		this.dto=dto;
		GameAct act=new GameAct();
		dto.setGameAct(act);
	}
	/**
	 * ����������ϣ�
	 */
	public void KeyUp() {
//		if(this.canMove(0, -1)){
//			this.dto.getGameAct().move(0, -1);
//		}
		
	this.dto.getGameAct().round(this.dto.getGameMap());
	}
	/**
	 * ����������£�
	 */
	public void KeyDown() {
		//���������ƶ������ж��Ƿ��ƶ��ɹ�
		if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())){
			return;
		}
		//�����Ϸ��ͼ����
		boolean[][] map=this.dto.getGameMap();
		//��÷������
		Point[] act=this.dto.getGameAct().getActPoints();
		//������ѻ�����ͼ����
		for(int i=0;i<act.length;i++){
			map[act[i].x][act[i].y]=true;
		}
		//TODO �ж��Ƿ��������
		//TODO ���в���
		//TODO ��ֲ���
		//TODO �ж��Ƿ�����
		//TODO ����
		//ˢ��һ���µķ���
		this.dto.getGameAct().init(1);
		
	}
	/**
	 * �����������
	 */
	public void KeyLeft() {
		
			this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());

		// TODO Auto-generated method stub
		
	}
	/**
	 * ����������ң�
	 */
	public void KeyRight() {
	
			this.dto.getGameAct().move(1, 0,this.dto.getGameMap());
	
		// TODO Auto-generated method stub
		
	}
	/**
	 * �ж��Ƿ��ƶ�
	 * @param moveX
	 * @param moveY
	 * @return
	 */
	private boolean canMove(int moveX,int moveY){
		
		Point[] nowPoints=this.dto.getGameAct().getActPoints();
		for(int i=0;i<nowPoints.length;i++){
			int newX=nowPoints[i].x+moveX;
			int newY=nowPoints[i].y+moveY;
			//TODO ����
			if(newX<0||newX>9||newY<0||newY>17){
				return false;
			}
		}
		return true;
	}
}
