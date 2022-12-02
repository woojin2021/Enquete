<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<title>로그인</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">로그인</h1>
		</div>
	</div>
	
	<div class="container">
		<form name="login" class="form-horizontal" action="<c:url value="/AddressLoginAction.do"/>" method="post">
			<div class="form-group row">
				<label class="col-sm-2">회원 아이디</label>
				<div class="col-sm-3">
					<input type="text" id="memberId" name="userId" class="form-control" required="required" placeholder="회원 아이디">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">비밀번호</label>
				<div class="col-sm-3">
					<input type="password" id="password" name="password" class="form-control" required="required" placeholder="비밀번호">
				</div>
			</div>
			<c:if test="${result != null }">
			<div id="loginError" class="form-group row">
				<div class="col-sm-5">
					<p class="alert alert-danger">로그인에 실패하였습니다.<br>회원 아이디와 비밀번호를 확인해주세요.</p>
				</div>
			</div>
			</c:if>
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="로그인">
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>