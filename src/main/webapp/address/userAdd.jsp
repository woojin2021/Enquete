<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>회원 정보 관리</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">회원 정보 등록</h1>
		</div>
	</div>
	
	<div class="container">
		<form name="login" class="form-horizontal off-sm-2" action="<c:url value="/AddressUpdateAction.do"/>" method="post">
			<div class="form-group row">
				<label class="col-sm-2">회원 아이디</label>
				<div class="col-sm-3">
					<input type="text" name="userId" class="form-control" required="required" value="${address.userid}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">비밀번호</label>
				<div class="col-sm-3">
					<input type="password" name="password" class="form-control" required="required" value="${address.password}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">이름</label>
				<div class="col-sm-3">
					<input type="text" name="name" class="form-control" required="required" value="${address.name}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">이메일</label>
				<div class="col-sm-3">
					<input type="email" name="mailaddress" class="form-control" required="required" value="${address.mailaddress}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">그룹1</label>
				<div class="col-sm-3">
					<input type="text" name="extend00" class="form-control" required="required" value="${address.extend00}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">그룹2</label>
				<div class="col-sm-3">
					<input type="text" name="extend01" class="form-control" required="required" value="${address.extend01}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">권한</label>
				<div class="col-sm-5">
					<input type="radio" name="admin" value="0" ${(address.admin == 0)?'checked':''}>시스템 관리자
					<input type="radio" name="admin" value="1" ${(address.admin == 1)?'checked':''}>앙케이트 관리자
					<input type="radio" name="admin" value="2" ${(address.admin == 2)?'checked':''}>일반 회원
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록">
					<a href="<c:url value="/AddressListAction.do" />" class="btn btn-danger">취소</a>
				</div>
			</div>
		</form>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>