<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
if (common.RequestUtil.isAdmin(request) == false && common.RequestUtil.isCreater(request) == false) {
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>앙케이트 관리</title>
<script type="text/javascript">
	var xhrObject; // XMLhttpRequest객체를 저장할 변수, 전역변수선언
	function setIdCheck() {
		if(xhrObject.readyState == 4){
			if(xhrObject.status == 200){
				var result = xhrObject.responseText;
				var objRes = eval("("+result+")");
				resetIdCheck(objRes.result);
			}
		}
	}
	function resetIdCheck(result) {
		if (result == true) {
			document.getElementById("idChecked").value = true;
			btn = document.getElementById("btnIdCheck").className = "btn btn-secondary";
		} else {
			document.getElementById("idChecked").value = false;
			btn = document.getElementById("btnIdCheck").className = "btn btn-success";
			if (result == false) {
				alert("존재하지 않는 아이디 입니다.");
			}
		}
	}
	function checkId() {
		let creater = document.getElementById("creater").value;
		if (creater.length == 0) {
			return;
		}
		
		xhrObject = new XMLHttpRequest();
		
		var url = '<c:url value="/address/chekUserId.jsp"/>'; //요청 url 설정
		
		var reqparam = "userId=" + creater;
		xhrObject.onreadystatechange = setIdCheck; // 응답결과를 처리메소드인 resultProcess() 메소드 설정
		xhrObject.open("Post", url, "true"); //서버의 요청설정 -url변수에 설정된 리소스를 요청할 준비
		xhrObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xhrObject.send(reqparam);
	}
	function regist() {
		let idChk = document.getElementById("idChecked").value;
		if (idChk == "false") {
			alert("존재하지 않는 아이디이거나 앙케이트 관리자가 아닙니다.");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">앙케이트 그룹 등록</h1>
		</div>
	</div>
	
	<div class="container">
		<form name="createGroup" class="form-horizontal off-sm-2" action="<c:url value="/EnqueteGroupCreateAction.do"/>" method="post">
			<div class="form-group row">
				<label class="col-sm-2">그룹명</label>
				<div class="col-sm-3">
					<input type="text" name="groupName" class="form-control" required="required" value="${group.groupName}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">앙케이트수</label>
				<div class="col-sm-3">
					<input type="text" name="maxEnquete" class="form-control" required="required" value="${group.maxEnquete}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">유효기간</label>
				<div class="col-sm-3">
					<input type="date" id="expireDate" name="expireDate" class="form-control" value="${group.expireDate}">
				</div>
				<span class="col-sm-2">생략시 무기한</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">앙케이트 관리자</label>
				<div class="col-sm-3">
					<input type="text" id="creater" name="creater" class="form-control" required="required" onkeyup="resetIdCheck()" value="${group.creater}">
					<input type="hidden" id="idChecked" value="false">
				</div>
				<div class="col-sm-2">
					<input id="btnIdCheck" type="button" value="아이디 확인" onclick="checkId()" class="btn btn-success">
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록" onclick="return regist();">
					<a href="<c:url value="/EnqueteGroupListAction.do"/>" class="btn btn-danger">취소</a>
				</div>
			</div>
		</form>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>