/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Player;

/**
 * @author gandi
 *
 */
public class DataBase implements Data{

	private final String dburl;
	private final String dbuser;
	private final String dbpwd;
	private static String LOAD_SQL="select Top 5 user_name,point from user_point where type_id=1 order by point desc";
	private static String SAVE_SQL="INSERT INTO user_point(user_name,point,type_id) VALUES (?,?,?)";
	
	
	public DataBase(HashMap<String,String>param){
		
		this.dburl=param.get("dburl");
		this.dbuser=param.get("dbuser");
		this.dbpwd=param.get("dbpwd");
		try {
			Class.forName(param.get("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Player> loadData() {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Player> players=new ArrayList<Player>();
		try {
			conn=DriverManager.getConnection(dburl,dbuser,dbpwd);
			stmt=conn.prepareStatement(LOAD_SQL);
			rs=stmt.executeQuery();
			while(rs.next()){
				players.add(new Player(rs.getString(1),rs.getInt(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
				try {
					if(stmt!=null) stmt.close();
					if(rs!=null) rs.close();
					if(conn!=null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		return players;
	}

	
	@Override
	public void saveData(Player players) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DriverManager.getConnection(dburl,dbuser,dbpwd);
			stmt=conn.prepareStatement(SAVE_SQL);
			stmt.setObject(1,players.getName());
			stmt.setObject(2,players.getPoint());
			stmt.setObject(3,1);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	

}
