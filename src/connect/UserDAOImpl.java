package connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl extends UserDAO{
	private static UserDAOImpl obj;
	private static final String GET_USER="select uid,uname,upass,flag from users where uname=? && upass=?;";
	private static final String SET_FLAG="update users set flag=? where uname=?;";
	UserDTO userDTO=null;
	
	private UserDAOImpl() {
	}
	
	public static UserDAOImpl createUserDAO() {
		if(obj==null) obj=new UserDAOImpl();
		return obj;
	}
	
	@Override
	public UserDTO getUser(String uname, String upass) {
		try {
			Connection con=ConnectionUtility.createConnection();
			PreparedStatement pst=con.prepareStatement(GET_USER);
			pst.setString(1, uname);
			pst.setString(2, upass);
			ResultSet rs=pst.executeQuery();		
			
			while(rs.next()) {
				int uid=rs.getInt(1);
				String name=rs.getString(2);
				String pass=rs.getString(3);
				int flag=rs.getInt(4);
				userDTO=new UserDTO(uid, name, pass, flag);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userDTO;
	}
	public void setFlag(int flag, String uname) {
		try {
			Connection con=ConnectionUtility.createConnection();
			PreparedStatement pst=con.prepareStatement(SET_FLAG);
			pst.setInt(1, flag);
			pst.setString(2, uname);
			pst.executeUpdate();
			
			ConnectionUtility.closeConnection(null);
			
		}catch(Exception e) {
			e.printStackTrace();
			ConnectionUtility.closeConnection(e);
		}
	}
	
	public int getUid() {
		return userDTO.getUid();
	}
}