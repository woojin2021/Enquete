package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Configure;
import model.dao.AddressDAO;
import model.dto.AddressDTO;

/**
 * Servlet implementation class LogonController
 */
@WebServlet("/LogonController")
public class LogonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogonController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String path = "/";
		System.out.println("DEBUG::requestURI:" + requestURI);
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		
		if (command.equals("/AddressLoginAction.do")) {
			if (requestAddressLogin(request)) {
				path = "/AddressListAction.do";
			} else {
				path = "/address/login.jsp?failed";
			}

		} else if (command.equals("/EnqueteLoginAction.do")) {

			if (requestAddressLogin(request)) {
				path = "/EnqueteGroupListAction.do";
			} else {
				path = "/admin/login.jsp?failed";
			}

		} else if (command.equals("/UserLoginAction.do")) {
			requestEnqueteLogin(request);
			path = (String)request.getSession().getAttribute(Configure.SSM_ENQUETE_PATH);

		} else if (command.equals("/LogoutAction.do")) {
			request.getSession().invalidate();
			response.sendRedirect(contextPath + "/welcome.jsp");
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	private boolean requestAddressLogin(HttpServletRequest request) {
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		
		AddressDAO dao = AddressDAO.getInstance();
		AddressDTO user = dao.selectByUserId(id);
		if (user != null && user.getPassword().equals(pw) && user.getAdmin() != Configure.AUTHOR_USER) {
			user.setLastlogindate(new java.sql.Date(new java.util.Date().getTime()));
			dao.updateLastLogin(user);
			HttpSession session = request.getSession();
			session.setAttribute(Configure.SSM_USERID, user.getUserid());
			session.setAttribute(Configure.SSM_USERNAME, user.getName());
			session.setAttribute(Configure.SSM_AUTHOR, user.getAdmin());
			return true;
		} else {
			request.setAttribute("result", "loginFiled...");
			return false;
		}
	}

	private boolean requestEnqueteLogin(HttpServletRequest request) {
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		
		AddressDAO dao = AddressDAO.getInstance();
		AddressDTO user = dao.selectByUserId(id);
		if (user != null && user.getPassword().equals(pw) && 
				(user.getAdmin() == Configure.AUTHOR_ADMIN || user.getAdmin() == Configure.AUTHOR_CREATER)) {
			user.setLastlogindate(new java.sql.Date(new java.util.Date().getTime()));
			dao.updateLastLogin(user);
			HttpSession session = request.getSession();
			session.setAttribute(Configure.SSM_USERID, user.getUserid());
			session.setAttribute(Configure.SSM_USERNAME, user.getName());
			session.setAttribute(Configure.SSM_AUTHOR, user.getAdmin());
			return true;
		} else {
			request.setAttribute("result", "loginFiled...");
			return false;
		}
	}
	

}
