package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Configure;
import model.dao.AnswerDAO;
import model.dao.EnqueteDAO;
import model.dto.AnswerDTO;
import model.dto.EnqueteDTO;

/**
 * Servlet implementation class AnswerController
 */
@WebServlet("/AnswerController")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerController() {
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
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String path = "/";
		System.out.println("DEBUG::requestURI:" + requestURI);
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		if (command.equals("/EnqueteAnswerForm.do")) {
			path = requestEnqueteAnswerView(request);
			
		} else if (command.equals("/EnqueteAnswerAction.do")) {
			path = requestEnqueteAnsweCreate(request);
			
		}

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	private String requestEnqueteAnsweCreate(HttpServletRequest request) {
		String sEnqueteID = request.getParameter("enqueteId");
		if (sEnqueteID == null || sEnqueteID.isBlank()) {
			return "/welcom.jsp";
		}
		int enqueteId = Integer.parseInt(sEnqueteID);

		String regex = "^ANS";
		Pattern p = Pattern.compile(regex);
		String name;
		
		AnswerDAO dao = AnswerDAO.getInstance();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			name = names.nextElement();
			Matcher m = p.matcher(name);
			if (!m.find()) {
				continue;
			}
			int questionId = Integer.parseInt(name.substring(4));
			int answerValue = Integer.parseInt(request.getParameter(name));
			
			AnswerDTO answer = new AnswerDTO();
			answer.setEnqueteId(enqueteId);
			answer.setQuestionId(questionId);
			answer.setAnswerValue(answerValue);
			dao.insert(answer);
		}

		request.setAttribute("message", "수고하셨습니다.");
		return "thankyou.jsp";
	}

	private String requestEnqueteAnswerView(HttpServletRequest request) {
		String sEnqueteID = request.getParameter("enqueteId");
		if (sEnqueteID == null || sEnqueteID.isBlank()) {
			return "/welcom.jsp";
		}
		int enqueteId = Integer.parseInt(request.getParameter("enqueteId"));
		EnqueteDAO dao = EnqueteDAO.getInstance();
		EnqueteDTO enquete = dao.selectById(enqueteId);
		
		// TODO 투표 대상자 확인
		
		boolean isOpen = true;
		if (enquete.getOpenStatus() != Configure.ENQUETE_STATUS_OPEN) {
			isOpen = false;
		}
		if (enquete.getBeginDate() != null) {
			java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
			if (currentDate.compareTo(enquete.getBeginDate()) < 0 || currentDate.compareTo(enquete.getEndDate()) > 0) {
				isOpen = false;
			}
		}
		if (isOpen == false) {
			request.setAttribute("message", "아직 시작하지 않았거나 종료된 앙케이트 입니다.");
			return "/thankyou.jsp";
		}

		request.setAttribute("enquete", enquete);
		return "/enquete.jsp";
	}

}
