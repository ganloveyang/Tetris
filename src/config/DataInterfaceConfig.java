/**
 * 
 */
package config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * @author gandi
 *
 */
public class DataInterfaceConfig {
	private  final String className;
	private  final Map<String,String> param;
	public DataInterfaceConfig(Element dataInterfaceConfig){
		className=dataInterfaceConfig.attributeValue("className");
		param=new HashMap<String,String>();
		List<Element> params=dataInterfaceConfig.elements("param");
		for(Element e:params){
			param.put(e.attributeValue("key"), e.attributeValue("value"));
		}
	}
	public String getClassName() {
		return className;
	}
	public Map<String, String> getParam() {
		return param;
	}
	

}
