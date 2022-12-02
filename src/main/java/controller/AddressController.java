package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Configure;
import common.RequestUtil;
import model.UserGroup;
import model.dao.AddressDAO;
import model.dto.AddressDTO;

/**
 * Servlet implementation class AddressController
 */
@WebServlet("/AddressController")
public class AddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressController() {
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
		// 사이트 관리자만 이용 가능
		if (Configure.AUTHOR_ADMIN != RequestUtil.getInt(request, Configure.SSM_AUTHOR)) {
			response.sendRedirect("address/login.jsp");
			return;
		}
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String path = "/";
		System.out.println("DEBUG::requestURI:" + requestURI);
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		if (command.equals("/AddressViewAction.do")) {
			requestAddressItem(request);
			path = "/address/userView.jsp";

		} else if (command.equals("/AddressUpdateForm.do")) {
			requestAddressItem(request);
			path = "/address/userEdit.jsp";

		} else if (command.equals("/AddressUpdateAction.do")) {
			requestAddressUpdate(request);
			requestAddressItem(request);
			path = "/address/userView.jsp";

		} else if (command.equals("/AddressDeleteAction.do")) {
			requestAddressDelete(request);
			path = "/AddressViewAction.do";

		} else if (command.equals("/AddressListAction.do")) {
			requestAddressList(request);
			path = "/address/userList.jsp";

		} else if (command.equals("/AddressCreateAction.do")) {
			requestAddressCreate(request);
			requestAddressItem(request);
			path = "/address/userList.jsp";

		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	private void requestAddressUpdate(HttpServletRequest request) {
		AddressDTO user = new AddressDTO();
		user.setUserid(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setMailaddress(request.getParameter("mailaddress"));
		user.setExtend00(request.getParameter("extend00"));
		user.setExtend01(request.getParameter("extend01"));
		user.setAdmin(Integer.parseInt(request.getParameter("admin")));

		AddressDAO dao = AddressDAO.getInstance();
		dao.updateDetail(user);
	}

	private void requestAddressDelete(HttpServletRequest request) {
		AddressDTO user = new AddressDTO();
		user.setUserid(request.getParameter("userId"));

		AddressDAO dao = AddressDAO.getInstance();
		dao.delete(user);
	}

	private void requestAddressCreate(HttpServletRequest request) {
		AddressDTO user = new AddressDTO();
		user.setUserid(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setMailaddress(request.getParameter("mailaddress"));
		user.setExtend00(request.getParameter("extend00"));
		user.setExtend01(request.getParameter("extend01"));
		user.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		AddressDAO dao = AddressDAO.getInstance();
		dao.insert(user);
	}

	private void requestAddressItem(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		
		AddressDAO dao = AddressDAO.getInstance();
		AddressDTO user = dao.selectByUserId(userId);
		request.setAttribute("address", user);
	}

	private void requestAddressList(HttpServletRequest request) {
		String sPageNum = request.getParameter("pageNum");
		int pageNum;
		try {
			pageNum = Integer.parseInt(sPageNum);
		} catch (NumberFormatException e) {
			pageNum = 1;
		}
		int start = Configure.LISTCOUNT * (pageNum - 1) + 1;
		int end = Configure.LISTCOUNT * pageNum;
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		String whereSql = "";
		if (key != null && value != null) {
			if (key.equals("admin")) {
				whereSql = " WHERE "  + key +  " = " + value;
				key = null;
				value = null;
			} else {
				whereSql = " WHERE "  + key +  " LIKE '%" + value + "%'";
			}
		}

//		String admin = request.getParameter("admin");
//		if (admin != null) {
//			whereSql += whereSql.isEmpty() ? " WHERE " : " AND ";
//			whereSql += " admin = " + admin;
//		}
//		String extend00 = request.getParameter("extend00");
//		if (extend00 != null) {
//			whereSql += whereSql.isEmpty() ? " WHERE " : " AND ";
//			whereSql += " extend00 = '" + extend00 + "'";
//		}
//		String extend01 = request.getParameter("extend01");
//		if (extend01 != null) {
//			whereSql += whereSql.isEmpty() ? " WHERE " : " AND ";
//			whereSql += " extend01 = '" + extend01 + "'";
//		}
		
		AddressDAO dao = AddressDAO.getInstance();
		AddressDTO[] addressdList = dao.selectList(start, end, whereSql);
		
		int addressCount = dao.selectCount(whereSql);
		int lastPage = (addressCount / Configure.LISTCOUNT) + 1;
		
		int tempPage = 0;
		String pages = "";
		for (int i = 0; i < addressCount; i++) {
			if (tempPage == (i / Configure.LISTCOUNT)) {
				tempPage++;
				if ((tempPage - 1) / Configure.PAGECOUNT == (pageNum -1) / Configure.PAGECOUNT) {
					pages += "," + tempPage; 
				}
			}
		}
		if (pages.length() > 0) {
			pages = pages.substring(1);
		}
		
		int beforePage = ((pageNum - 1) / Configure.LISTCOUNT - 1) * Configure.LISTCOUNT + 1;
		beforePage = (beforePage < 1) ? 1 : beforePage;
		int afterPage = ((pageNum - 1) / Configure.LISTCOUNT + 1) * Configure.LISTCOUNT + 1;
		afterPage = (afterPage > lastPage) ? lastPage : afterPage;
		
		request.setAttribute("addressList", addressdList);
		request.setAttribute("addressCount", addressCount);
		request.setAttribute("currentPage", pageNum);
		request.setAttribute("beforePage", beforePage);
		request.setAttribute("afterPage", afterPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("pages", pages);
		request.setAttribute("searchKey", key);
		request.setAttribute("searchValue", value);
		
		UserGroup[] groups = dao.getUserGroupList();
		request.setAttribute("groups", groups);
	}

}
