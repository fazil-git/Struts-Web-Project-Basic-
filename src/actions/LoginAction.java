package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connect.UserDAO;
import connect.UserDAOImpl;
import db.LoginBusiness;
import db.LoginBusinessImpl;

public class LoginAction extends Actions {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		
		UserDAO userDAO=UserDAOImpl.createUserDAO();
		LoginBusiness loginBusiness=new LoginBusinessImpl(userDAO);
		
		if(loginBusiness.checkUser(uname, upass)) {
			session.setAttribute("uname", uname);
			return "login.success";
		}
		return "login.fail";
	}
}
