package ui;

import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import service.GameService;

import config.ConfigFactory;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

public class JPanelGame extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private List<Layer> layers=null;
	private GameDto dto=null;
	public JPanelGame(GameDto dto){
		//���dto����
		this.dto=dto;
		//��ʼ�����
		 initComponent();
		//��ʼ����
         initLayer();
		//		layers=new Layer[]{
//				//TODO Ӳ����
//				new LayerBackground(0,0,1200,700),
//				new LayerDatabase(40,32,334,279),
//				new LayerDisk(40,343,334,279),
//				new LayerGame(414,32,334,590),
//				new LayerButton(788,32,334,124),
//				new LayerNext(788,188,176,148),
//				new LayerLevel(964,188,158,148),
//				new LayerPoint(788,368,334,200),
//		};
	}
	/**
	 * ��װ��ҿ�����
	 * @param control
	 */
	public void setGameControl(PlayerControl control){
		this.addKeyListener(control);
	}
	/**
	 * ��ʼ�����
	 */
    private void initComponent(){
    	
		
	}
	/**
	 * ��ʼ����
	 */ 
	private void initLayer(){
		try {
	    	 //�����Ϸ����
			GameConfig cfg=ConfigFactory.getGameConfig();
			//��ò�����
			List<LayerConfig> layersCfg=cfg.getLayersConfig();
			//������Ϸ������
			layers=new ArrayList<Layer>(layersCfg.size());
			//�������в����
			for(LayerConfig layerCfg:layersCfg){
				//������JAVA����֪ʶ
				//��������
				Class<?> cls=Class.forName(layerCfg.getClassName());
				
				//��ù��캯��
				Constructor<?> ctr =cls.getConstructor(int.class,int.class,int.class,int.class);
				//���ù��캯����������
				Layer layer=(Layer)ctr.newInstance(
						layerCfg.getX(),layerCfg.getY(),layerCfg.getW(),layerCfg.getH()
				);
				//������Ϸ���ݶ���
				layer.setDto(this.dto);
				//��������Layer������뼯��
				layers.add(layer);
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Override
	public void paintComponent(Graphics g){
		//���û��෽��
		super.paintComponent(g);
		//������Ϸ����
		for(int i=0;i<layers.size();i++){	
			//ˢ�²㴰��
			layers.get(i).paint(g);
			}
		//���ؽ���
	    this.requestFocus();
		
	}
}
