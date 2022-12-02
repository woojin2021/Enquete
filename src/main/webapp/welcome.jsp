<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enquete Sys co.</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<%
model.dto.EnqueteDTO[] enquetes = model.dao.EnqueteDAO.getInstance().selectAllOpened();
request.setAttribute("enquetes", enquetes);
%>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container-fluid">
			<div class="collapse navbar-collapse justify-content-end">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="address/login.jsp">회원관리</a></li>
					<li class="nav-item"><a class="nav-link" href="admin/login.jsp">앙케이트관리</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">앙케이트 사이트에 오신것을 환영합니다.</h1>
		</div>
	</div>
	
	<div class="container">
		<ul class="list-group">
			<c:forEach var="row" items="${enquetes}">
				<li class="list-group-item" onclick="location.href='EnqueteAnswerForm.do?enqueteId=${row.enqueteId}'">${row.enqueteName}</li>
			</c:forEach>
		</ul>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>