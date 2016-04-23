package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * ��Ϸ������
 * @author gandi
 *
 */

public class GameConfig {
    /**
     * ���ڿ��
     */
	private int width;
	/**
     * ���ڸ߶�
     */
	private int height;
	/**
	 * ����
	 */
	private String title;
	/**
	 * ���ڰθ�
	 */
	private int windowUp;
	
	/**
     * �߿�ߴ�
     */
	private int windowSize;
	/**
     * �߿��ڱ߾�
     */
	private int padding;
	/**
     * ͼ������
     */
	private List<LayerConfig> layersConfig;
	/**
	 * ���캯��
	 * ��ȡXML�ļ�����ȡȫ����Ϸ����
	 * @throws Exception
	 */
	public GameConfig() throws Exception{
		//����XML��ȡ��
		SAXReader reader=new SAXReader();
		//��ȡXML�ļ�
		Document   doc = reader.read("config/cfg.xml"); 
		//���XML�ļ��ĸ��ڵ�
		Element game=doc.getRootElement();
		//���ô��ڲ���
		this.setUiConfig(game.element("frame"));
		//����ϵͳ����
		this.setSystemConfig(game.element("system"));
		//�������ݷ��ʲ���
		this.setSystemData(game.element("data"));
		
	}
	/**
	 * ���ô���
	 * @param frame �����ļ��Ĵ�������Ԫ��
	 */
	private void setUiConfig(Element frame){
		//��ȡ���ڿ��
		this.width=Integer.parseInt(frame.attributeValue("width"));
		//��ȡ���ڸ߶�
		this.height=Integer.parseInt(frame.attributeValue("height"));
		//��ȡ�߿��ϸ
		this.windowSize=Integer.parseInt(frame.attributeValue("windowSize"));
		//��ȡ�߿��ڱ߾�
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		//��ȡ����
		this.title=frame.attributeValue("title");
		//��ȡ���ڰθ�
		this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
		//��ȡ��������
		List<Element> Layers=frame.elements("layer");
		layersConfig=new ArrayList<LayerConfig>();
		//��ȡ���д�������
		for(Element layer:Layers){
			LayerConfig lc=new LayerConfig(
					layer.attributeValue("className"),
					Integer.parseInt(layer.attributeValue("x")),
					Integer.parseInt(layer.attributeValue("y")),
					Integer.parseInt(layer.attributeValue("w")),
					Integer.parseInt(layer.attributeValue("h"))
					);
			layersConfig.add(lc);
			
		}
	}
	/**
	 * ����ϵͳ����
	 */
	private void setSystemConfig(Element system){
		//TODO ����ϵͳ����
	}
	/**
	 * �������ݲ���
	 * @param data
	 */
    private void setSystemData(Element data){
		//�������ݷ��ʲ���
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getWindowSize() {
		return windowSize;
	}
	public int getPadding() {
		return padding;
	}
	public List<LayerConfig> getLayersConfig() {
		return layersConfig;
	}
	public String getTitle() {
		return title;
	}
	public int getWindowUp() {
		return windowUp;
	}
    
    
    
}
