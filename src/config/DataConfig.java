/**
 * 
 */
package config;

import java.util.List;

import org.dom4j.Element;

/**
 * @author gandi
 *
 */
public class DataConfig {

	private final int maxRow;
	private final DataInterfaceConfig dataA;
	private final DataInterfaceConfig dataB;
	
	public DataConfig(Element data){
		this.maxRow=Integer.parseInt(data.attributeValue("maxRow"));
		dataA=new DataInterfaceConfig(data.element("dataA"));
		dataB=new DataInterfaceConfig(data.element("dataB"));
	}

	public DataInterfaceConfig getDataA() {
		return dataA;
	}

	public DataInterfaceConfig getDataB() {
		return dataB;
	}

	public int getMaxRow() {
		return maxRow;
	}

}
