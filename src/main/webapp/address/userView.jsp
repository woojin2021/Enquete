<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>회원 정보 관리</title>
<script type="text/javascript">
	function deleteConfirm(userid) {
		if(confirm("회원 정보를 삭제하겠습니까?")) {
			location.href="AddressDeleteAction.do?userId=" + userid;
		}
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	
		<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">회원 정보</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<table class="table table-border table-sm">
				<tr>
					<th>아이디</th><td>${address.userid}<td>
				</tr>
				<tr>
					<th>이름</th><td>${address.name}<td>
				</tr>
				<tr>
					<th>e메일</th><td>${address.mailaddress}<td>
				</tr>
				<tr>
					<th>그룹1</th><td>${address.extend00}<td>
				</tr>
				<tr>
					<th>그룹2</th><td>${address.extend01}<td>
				</tr>
				<tr>
					<th>권한</th><td>${address.adminName}<td>
				</tr>
				<tr>
					<th>등록일</th><td>${address.regdate}<td>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<td>
						<a href="AddressUpdateForm.do?userId=${address.userid}" class="btn btn-warning">수정</a>&nbsp;
						<a href="javascript:deleteConfirm('${address.userid}');" class="btn btn-danger">삭제</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>