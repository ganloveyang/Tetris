/**
 * 
 */
package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Player;

/**
 * @author gandi
 *
 */
public class DataDisk implements Data{

	private  final String filepath;
	
	public DataDisk(HashMap<String,String> param){
		this.filepath=param.get("path");
	}
	
	@Override
	public List<Player> loadData() {
		ObjectInputStream ois=null;
		List<Player> players=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(filepath));
			players= (List<Player>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return players;
	}

	
	@Override
	public void saveData(Player pla) {
		//先取出数据
		List<Player> players=this.loadData();
		//TODO 判断记录是否超过5条，如果超过5条，去掉分数低的
		//追加新纪录
		players.add(pla);
		//重新写入
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("data/recode.dat"));
			oos.writeObject(players);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
//	public static void main(String[] args) {
//		DataDisk dd=new DataDisk();
////		List<Player> players=new ArrayList<Player>();
////		players.add(new Player("小明",1024));
////		players.add(new Player("小红",1000));
////		players.add(new Player("小姨",2000));
////		players.add(new Player("本拉登",3000));
////		players.add(new Player("刘明",4000));
////		dd.saveData(players);
////		System.out.print("保存完毕");
//		
//		List<Player> dataFromDisk=dd.loadData();
//		
//		for(Player p:dataFromDisk){
//			System.out.print(p.getName()+":"+p.getPoint());
//		}
//	}

}
