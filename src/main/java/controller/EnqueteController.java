package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.RequestUtil;
import common.ValUtil;
import model.AnswerSummary;
import model.dao.AnswerDAO;
import model.dao.EnqueteDAO;
import model.dao.EnqueteGroupDAO;
import model.dto.EnqueteDTO;
import model.dto.EnqueteGroupDTO;

/**
 * Servlet implementation class EnqueteController
 */
@WebServlet("/EnqueteController")
public class EnqueteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnqueteController() {
        super();
        // TODO Auto-generated constructor stub
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
		// 사이트 관리자, 앙케이트 관라자만 이용 가능
		if (!RequestUtil.isAdmin(request) && !RequestUtil.isCreater(request)) {
			response.sendRedirect("admin/login.jsp");
			return;
		}
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String path = "/";
		System.out.println("DEBUG::requestURI:" + requestURI);
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		if (command.equals("/EnqueteGroupListAction.do")) {
			requestGroupList(request);
			path = "/admin/groupList.jsp";

		} else if (command.equals("/EnqueteGroupCreateAction.do")) {
			requestGroupCreate(request);
			path = "/EnqueteGroupListAction.do";

		} else if (command.equals("/EnqueteGroupViewAction.do")) {
			requestGroupView(request);
			path = "/admin/groupView.jsp";

		} else if (command.equals("/EnqueteGroupUpdateForm.do")) {
			requestGroupView(request);
			path = "/admin/groupEdit.jsp";

		} else if (command.equals("/EnqueteGroupUpdateAction.do")) {
			requestGroupUpdate(request);
			requestGroupView(request);
			path = "/admin/groupView.jsp";

		} else if (command.equals("/EnqueteGroupDeleteAction.do")) {
			requestGroupDelete(request);
			path = "/EnqueteGroupListAction.do";

		} else if (command.equals("/EnqueteListAction.do")) {
			requestEnqueteList(request);
			path = "/admin/enqueteList.jsp";

		} else if (command.equals("/EnqueteCreateAction.do")) {
			requestEnqueteCreate(request);
			path = "/EnqueteListAction.do";

		} else if (command.equals("/EnqueteUpdateForm.do")) {
			requestEnqueteView(request);
			path = "/admin/enqueteEdit.jsp";

		} else if (command.equals("/EnqueteUpdateAction.do")) {
			requestEnqueteUpdate(request);
			path = "/EnqueteListAction.do";

		} else if (command.equals("/EnqueteDeleteAction.do")) {
			requestEnqueteDelete(request);
			path = "/EnqueteListAction.do";

		} else if (command.equals("/EnqueteSummaryAction.do")) {
			requestEnqueteSummary(request);
			path = "/admin/enqueteSummary.jsp";

		}

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	private void requestEnqueteList(HttpServletRequest request) {
		int groupId = ValUtil.convertToInt(request.getParameter("groupId"));
		
		EnqueteGroupDTO group = EnqueteGroupDAO.getInstance().selectById(groupId);
		request.setAttribute("group", group);
		
		EnqueteDAO dao = EnqueteDAO.getInstance();
		EnqueteDTO[] enquetes = dao.selectByGroupId(groupId, null);
		request.setAttribute("enquetes", enquetes);
	}

	private void requestEnqueteCreate(HttpServletRequest request) {
		EnqueteDTO enquete = new EnqueteDTO();
		enquete.setEnqueteName(request.getParameter("enqueteName"));
		enquete.setGroupId(Integer.parseInt(request.getParameter("groupId")));
		enquete.setBeginDate(ValUtil.convertToSqlDate(request.getParameter("beginDate")));
		enquete.setEndDate(ValUtil.convertToSqlDate(request.getParameter("endDate")));
		enquete.setOpenStatus(Integer.parseInt(request.getParameter("openStatus")));
		enquete.setJsonData(request.getParameter("jsonData"));
		
		EnqueteDAO dao = EnqueteDAO.getInstance();
		dao.insert(enquete);
	}

	private void requestEnqueteView(HttpServletRequest request) {
		int enqueteId = Integer.parseInt(request.getParameter("enqueteId"));
		EnqueteDAO dao = EnqueteDAO.getInstance();
		EnqueteDTO enquete = dao.selectById(enqueteId);

		request.setAttribute("enquete", enquete);
	}

	private void requestEnqueteUpdate(HttpServletRequest request) {
		EnqueteDTO enquete = new EnqueteDTO();
		enquete.setEnqueteId(Integer.parseInt(request.getParameter("enqueteId")));
		enquete.setEnqueteName(request.getParameter("enqueteName"));
		enquete.setGroupId(Integer.parseInt(request.getParameter("groupId")));
		enquete.setBeginDate(ValUtil.convertToSqlDate(request.getParameter("beginDate")));
		enquete.setEndDate(ValUtil.convertToSqlDate(request.getParameter("endDate")));
		enquete.setOpenStatus(Integer.parseInt(request.getParameter("openStatus")));
		enquete.setJsonData(request.getParameter("jsonData"));
		
		EnqueteDAO dao = EnqueteDAO.getInstance();
		dao.update(enquete);
	}

	private void requestEnqueteDelete(HttpServletRequest request) {
		int enqueteId = Integer.parseInt(request.getParameter("enqueteId"));
		EnqueteDAO dao = EnqueteDAO.getInstance();
		dao.deleteById(enqueteId);
	}

	private void requestGroupDelete(HttpServletRequest request) {
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		EnqueteGroupDAO dao = EnqueteGroupDAO.getInstance();
		dao.deleteById(groupId);
	}

	private void requestGroupUpdate(HttpServletRequest request) {
		EnqueteGroupDTO group = new EnqueteGroupDTO();
		group.setGroupId(Integer.parseInt(request.getParameter("groupId")));
		group.setGroupName(request.getParameter("groupName"));
		group.setMaxEnquete(Integer.parseInt(request.getParameter("maxEnquete")));
		group.setExpireDate(ValUtil.convertToSqlDate(request.getParameter("expireDate")));
		group.setCreater(request.getParameter("creater"));
		
		EnqueteGroupDAO dao = EnqueteGroupDAO.getInstance();
		dao.update(group);
	}

	private void requestGroupView(HttpServletRequest request) {
		int groupId = ValUtil.convertToInt(request.getParameter("groupId"));
		EnqueteGroupDAO dao = EnqueteGroupDAO.getInstance();
		EnqueteGroupDTO group = dao.selectById(groupId);

		request.setAttribute("group", group);
	}

	private void requestGroupCreate(HttpServletRequest request) {
		EnqueteGroupDTO group = new EnqueteGroupDTO();
		group.setGroupName(request.getParameter("groupName"));
		group.setMaxEnquete(Integer.parseInt(request.getParameter("maxEnquete")));
		group.setExpireDate(ValUtil.convertToSqlDate(request.getParameter("expireDate")));
		group.setCreater(request.getParameter("creater"));
		
		EnqueteGroupDAO dao = EnqueteGroupDAO.getInstance();
		dao.insert(group);
	}

	private void requestGroupList(HttpServletRequest request) {
		EnqueteGroupDAO dao = EnqueteGroupDAO.getInstance();
		EnqueteGroupDTO[] groups = dao.selectAll(null);
		
		request.setAttribute("groups", groups);
	}

	private void requestEnqueteSummary(HttpServletRequest request) {
		int enqueteId = Integer.parseInt(request.getParameter("enqueteId"));
		
		EnqueteDTO enquete = EnqueteDAO.getInstance().selectById(enqueteId);
		AnswerSummary[] summary = AnswerDAO.getInstance().selectByEnqueteId(enqueteId);
		
		/*
{"enquetes":[
  {"subject":"sbj", "options":[{"option":"opt1"},{"option":"opt2"},{"option":"opt3"},{"option":"opt4"}]},
  {"subject":"sbj", "options":[{"option":"opt1"},{"option":"opt2"},{"option":"opt3"},{"option":"opt4"}]}
]}
		 */
		String jsonData = enquete.getJsonData();
		int index;
		String body = getPharse(jsonData, '[');
		for (int i = 0; i < summary.length; i++) {
			AnswerSummary sum = summary[i];
			String question = getPharse(body, '{');
			index = body.indexOf(",", question.length());
			if (index > -1) {
				body = body.substring(index + 1);
			}
			String subject = question.substring(0, question.indexOf(",")).trim();
			String temp = question.substring(question.indexOf(",") + 1).trim();
			temp = getPharse(temp, '[');
			String[] options = temp.split(",");
			sum.setQuestion(getValue(subject));
			sum.setAnswer1(getValue(getPharse(options[0], '{')));
			sum.setAnswer2(getValue(getPharse(options[1], '{')));
			sum.setAnswer3(getValue(getPharse(options[2], '{')));
			sum.setAnswer4(getValue(getPharse(options[3], '{')));
		}
		
		request.setAttribute("enquete", enquete);
		request.setAttribute("summary", summary);
	}
	
	private String getPharse(String data, char start) {
		char end = '}';
		if (start == '[') {
			end = ']';
		} else if (start == '"') {
			end = '"';
		}
		int startIndex = data.indexOf(start);
		int endIndex = 0;
		int cnt = 0;
		for (int i = startIndex + 1; i < data.length(); i++) {
			if (data.charAt(i) == start) {
				cnt++;
			}
			if (data.charAt(i) == end) {
				if (cnt == 0) {
					endIndex = i;
					break;
				}
				cnt--;
			}
		}
		return 	data.substring(startIndex + 1,  endIndex);
	}

	private String getValue(String data) {
		String value = "";
		
		String[] temp =  data.split(":");
		if (temp.length == 2) {
			value = temp[1].replace("\"", "");
		}
		
		return value;
	}

}
