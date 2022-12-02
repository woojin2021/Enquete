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
	function deleteConfirm(userid) {
		if(confirm("앙케이트 그룹를 삭제하겠습니까?\n앙케이트 그룹를 삭제할 경우 작성한 앙케이트도 함께 삭제됩니다.")) {
			location.href="EnqueteGroupDeleteAction.do?groupId=" + userid;
		}
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
		<form name="createGroup" class="form-horizontal off-sm-2">
			<div class="form-group row">
				<label class="col-sm-2">그룹명</label>
				<div class="col-sm-3">
					<input type="text" name="groupName" class="form-control" disabled="disabled" value="${group.groupName}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">앙케이트수</label>
				<div class="col-sm-3">
					<input type="text" name="maxEnquete" class="form-control" disabled="disabled" value="${group.maxEnquete}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">유효기간</label>
				<div class="col-sm-3">
					<input type="date" id="expireDate" name="expireDate" class="form-control" disabled="disabled" value="${group.expireDate}">
				</div>
				<span class="col-sm-2">생략시 무기한</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">앙케이트 관리자</label>
				<div class="col-sm-3">
					<input type="text" id="creater" name="creater" class="form-control" disabled="disabled" onkeyup="resetIdCheck()" value="${group.creater}">
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
					<a href="EnqueteGroupUpdateForm.do?groupId=${group.groupId}" class="btn btn-warning">수정</a>&nbsp;
					<a href="javascript:deleteConfirm('${group.groupId}');" class="btn btn-danger">삭제</a>
				</div>
			</div>
		</form>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>