package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	private static Connection con;
	private ConnectionUtility(){
		
	}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static ThreadLocal<Connection> tlocal=new ThreadLocal<Connection>();
	public static Connection createConnection() {
		con=tlocal.get();
		if(con==null) {
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost/code3", "root", "1234");
				tlocal.set(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void closeConnection(Exception exp) {
		con=tlocal.get();
		if(con!=null) {
			try {
				if(exp==null) con.commit();
				else con.rollback();
				
				con.close();
				tlocal.remove();
			}catch(Exception e) { e.printStackTrace(); }
		}
	}
}