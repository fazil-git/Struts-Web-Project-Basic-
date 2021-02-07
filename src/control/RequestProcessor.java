package control;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.Actions;

public class RequestProcessor {
	public String process(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletContext ctx=request.getServletContext();
			Properties prop=(Properties) ctx.getAttribute("prop");
			
			String formid=request.getParameter("formid");
			System.out.println("formid string: "+formid);
			
			String actionclass=prop.getProperty(formid);
			System.out.println("action class: "+actionclass);
			
			Actions action= (Actions) Class.forName(actionclass).getDeclaredConstructor().newInstance();
			action.execute(request, response);
			
		}catch(Exception e) { e.printStackTrace(); }
		
		return null;
	}
}
