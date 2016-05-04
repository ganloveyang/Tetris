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
import java.util.List;

import dto.Player;

/**
 * @author gandi
 *
 */
public class DataBase implements Data{

	private static String DRIVER ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URI="jdbc:sqlserver://127.0.0.1:1433;database=game_test";
	private static String DB_USER="sa";
	private static String DB_PWD="gandi123";
	private static String LOAD_SQL="select Top 5 user_name,point from user_point where type_id=1 order by point desc";
	private static String SAVE_SQL="INSERT INTO user_point(user_name,point,type_id) VALUES (?,?,?)";
	static{
		try {
			Class.forName(DRIVER);
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
			conn=DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
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
			conn=DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
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

	public static void main(String[] args){
		DataBase db=new DataBase();
		db.saveData(new Player("GM",9999));
	}
	

}
