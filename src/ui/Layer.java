package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.ConfigFactory;
import config.GameConfig;
import dto.GameDto;
/**
 * 绘制窗口
 * @author Gand
 *
 */
public abstract class Layer { 
	protected static final int PADDING;
	private static final int SIZE;
	/**
	 * 数字图片260 36
	 */
	private static final Image IMG_NUMBER=new ImageIcon("graphics/string/num.png").getImage();
	/**
	 * 数字切片的宽度
	 */
	protected static final int IMG_NUMBER_W=IMG_NUMBER.getWidth(null)/10;
	/**
	 * 数字切片的高度
	 */
	private static final int IMG_NUMBER_H=IMG_NUMBER.getHeight(null);
	
	
	static{
		//获得游戏配置
		GameConfig cfg=ConfigFactory.getGameConfig();
		PADDING=cfg.getPadding();
		SIZE=cfg.getWindowSize();
	}
	private static Image WINDOW_IMG=new ImageIcon("graphics/window/Window.png").getImage();
	private static int WINDOW_W=WINDOW_IMG.getWidth(null);
	private static int WINDOW_H=WINDOW_IMG.getHeight(null);
	/**
	 * 窗口左上角x坐标
	 */
	protected int x;
	/**
	 *  窗口左上角y坐标
	 */
	protected int y;
	/**
	 * 窗口宽度
	 */
	protected int w;
	/**
	 *  窗口高度
	 */
	protected int h;
	/**
	 * 游戏数据
	 */
	protected GameDto dto=null;
	protected Layer(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	/**
	 * 绘制窗口
	 */
    protected void  createWindow(Graphics g){
    			//左上
    			g.drawImage(WINDOW_IMG, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
    			//中上
    			g.drawImage(WINDOW_IMG, x+SIZE, y, w+x-SIZE, y+SIZE, SIZE, 0, WINDOW_W-SIZE, SIZE, null);
    			//右上
    			g.drawImage(WINDOW_IMG, x+w-SIZE, y, x+w, y+SIZE, WINDOW_W-SIZE, 0, WINDOW_W, SIZE, null);
    			//左中
    			g.drawImage(WINDOW_IMG, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE,WINDOW_H-SIZE, null);
    			//正中
    			g.drawImage(WINDOW_IMG, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, WINDOW_W-SIZE,WINDOW_H-SIZE, null);
    			//右中
    			g.drawImage(WINDOW_IMG, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, WINDOW_W-SIZE, SIZE, WINDOW_W, WINDOW_H-SIZE, null);
    			//左下
    			g.drawImage(WINDOW_IMG, x, y+h-SIZE, x+SIZE, y+h, 0, WINDOW_H-SIZE, SIZE, WINDOW_H, null);
    			//中下
    			g.drawImage(WINDOW_IMG, x+SIZE, y+h-SIZE, w+x-SIZE, y+h, SIZE, WINDOW_H-SIZE, WINDOW_W-SIZE, WINDOW_H, null);
    			//右下
    			g.drawImage(WINDOW_IMG, x+w-SIZE, y+h-SIZE, x+w, y+h, WINDOW_W-SIZE, WINDOW_H-SIZE, WINDOW_W, WINDOW_H, null);
    }
    
   
	/**
     * 刷新游戏具体内容
     * @author gand
     * @param g 画笔
     */
	abstract public void paint(Graphics g);
	
	 public void setDto(GameDto dto) {
			this.dto = dto;
		}
	 /**
		 * 显示数字
		 * @param x 左上角x坐标
		 * @param y 左上角y坐标
		 * @param num 要显示的数字
		 * @param g 画笔对象
		 * @param maxBit 数字位数
		 */
		protected void drawNumber(int x,int y,int num,int maxBit,Graphics g){
			//把要打印的数字转换成字符串
		    String strNum=Integer.toString(num);
		    //循环绘制数字右对齐
		    for(int i=0;i<maxBit;i++){
		    	//判断是否满足绘制条件
		    	if(maxBit-i<=strNum.length()){
		    		//获得数字在字符串中的下标
		    		int idx=i-maxBit+strNum.length();
		    		//把数字number中的每一位取出
		    		int bit=strNum.charAt(idx)-'0';
		    		//绘制数字
			    	g.drawImage(IMG_NUMBER, 
							this.x+x+IMG_NUMBER_W*i, this.y+y, 
							this.x+x+IMG_NUMBER_W*(i+1), this.y+y+IMG_NUMBER_H, 
							bit*IMG_NUMBER_W, 0,
							(bit+1)*IMG_NUMBER_W, IMG_NUMBER_H, null);
		    	}
		    	
		    }
			
		}
}
