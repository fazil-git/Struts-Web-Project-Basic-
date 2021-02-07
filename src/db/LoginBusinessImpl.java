package db;

import connect.UserDAO;
import connect.UserDTO;

public class LoginBusinessImpl implements LoginBusiness {
	UserDAO userDAO;
	UserDTO userDTO;
	
	public LoginBusinessImpl(UserDAO userDAO) {
		this.userDAO=userDAO;
	}
	@Override
	public boolean checkUser(String uname, String upass) {
		userDTO=userDAO.getUser(uname, upass);
		if(userDTO!=null) {
			if(userDTO.getUname().equals(uname) && userDTO.getUpass().equals(upass)) {
				userDAO.setFlag(1, uname);
				return true;
			}
			return false;
		}
		return false;
	}
	@Override
	public int getUid() {
		return userDTO.getUid();
	}
}
