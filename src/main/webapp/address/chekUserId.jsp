<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String userId = request.getParameter("userId");
if (userId == null) {
	userId = "";
}
model.dao.AddressDAO dao = model.dao.AddressDAO.getInstance();
model.dto.AddressDTO user = dao.selectByUserId(userId);
%>
{result:<%=(user != null && user.getAdmin() == common.Configure.AUTHOR_CREATER) %>}