<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>앙케이트</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">${message}</h1>
			<p id="chkhtml">
		</div>
	</div>
	
	<div class="container">
		<a class="btn btn-primary" href="welcome.jsp">나가기</a>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>