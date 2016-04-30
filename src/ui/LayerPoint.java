package ui;


import java.awt.Graphics;

public class LayerPoint extends Layer {
	/**
	 * ���������λ��
	 */
	private static final int POINT_BIT=5;
	//TODO �����ļ�
	private static final int LEVEL_UP=20;
	/**
	 * ����ֵy����
	 */
	private  final int expY;
	
	/**
	 * ����y����
	 */
	private  final int rmLineY;
	/**
	 * ����y����
	 */
	private  final int pointY;
	
	/**
	 * ����x����
	 */
	private  final int comX;
	
	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		//��ʼ����ͨX����
		comX=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
		//��ʼ��������ʾ��Y����
		pointY=PADDING;
		//��ʼ��������ʾ��Y����
		rmLineY=this.pointY+Img.POINT.getHeight(null)+PADDING;
		//��ʼ������ֵ��ʾ��Y����
		this.expY=this.rmLineY+Img.POINT.getHeight(null)+PADDING;
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//���Ʊ���(����)
		g.drawImage(Img.POINT, x+PADDING, this.y+pointY, null);
		//��ʾ����
		this.drawNumber(comX, pointY,this.dto.getNowPoint(),POINT_BIT, g);
		//���Ʊ���(����)
		g.drawImage(Img.RMLINE, x+PADDING, this.y+rmLineY, null);
		//��ʾ����
		this.drawNumber(comX, rmLineY,this.dto.getNowRemoveLine(),POINT_BIT, g);
		//����ֵ�ۣ�����ֵ��
		int rmLine=this.dto.getNowRemoveLine();
		this.drawRect(expY,"��һ��",null,(double)(rmLine%LEVEL_UP)/(double)LEVEL_UP, g);
		
	}
	

}
