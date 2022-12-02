<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.dto.AddressDTO" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<script type="text/javascript">
	function showList(pageNum) {
		document.getElementById("pageNum").value = pageNum;
		document.search.submit();
	}
</script>
<meta charset="UTF-8">
<title>회원 정보 관리</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<div class="jumbotron" style="margin-bottom: 0;">
		<div class="container">
			<h1 class="display-3">회원 정보 목록</h1>
		</div>
	</div>
	
<nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
	<div class="container-fluid">
		<div class="collapse navbar-collapse justify-content-center">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="AddressListAction.do">전체</a></li>
				<li class="nav-item"><a class="nav-link" href="AddressListAction.do?key=admin&value=0">시스템 관리자</a></li>
				<li class="nav-item"><a class="nav-link" href="AddressListAction.do?key=admin&value=1">앙케이트 관리자</a></li>
				<%-- <c:set var="num" value="0" />
				<c:forEach var="group" items="${groups}">
				<li class="nav-item" onclick=""><a href="AddressListAction.do?extend00=${group.groupName}">${group.groupName}</a>
					<c:set var="num" value="${num + 1}" />
					<ul id="ul_${num}" class="list-group" style="display: none;">
						<c:forEach var="subGroup" items="${group.subGroups}">
						<li class="list-group-item"><a href="AddressListAction.do?extend01=${subGroup.groupName}">${subGroup.groupName}</a></li>
						</c:forEach>
					</ul>
				</li>
				</c:forEach> --%>
			</ul>
		</div>
		<div class="collapse navbar-collapse justify-content-center">
			<form class="d-flex" action="AddressListAction.do" method="post" name="search">
				<select class="form-select" name="key">
					<option value="userId" ${(searchKey =='userId')?'selected':''}>아이디</option>
					<option value="name" ${(searchKey =='name')?'selected':''}>이름</option>
					<option value="extend00" ${(searchKey =='extend00')?'selected':''}>그룹1</option>
					<option value="extend01" ${(searchKey =='extend01')?'selected':''}>그룹2</option>
				</select>
				<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="value" value="${searchValue}">
				<button class="btn btn-outline-info" type="submit">검색</button>
				<input type="hidden" name="pageNum" id="pageNum">
			</form>
		</div>
		<div class="collapse navbar-collapse justify-content-end">
			<a href="address/userAdd.jsp" class="btn btn-success">신규 회원</a>
		</div>
	</div>
</nav>

	<div class="container">
		<div class="row">
			<table class="table table-hover">
				<tr>
					<th>#</th>
					<th>아이디</th>
					<th>이름</th>
					<th>구분</th>
				</tr>
				<c:set var="num" value="<%=common.Configure.LISTCOUNT%>" />
				<c:set var="num" value="${(currentPage - 1) * num }" />
				<c:forEach var="row" items="${addressList}">
				<tr>
					<td><c:set var="num" value="${num + 1}" />${num}</td>
					<td><a href="AddressViewAction.do?userId=${row.userid}">${row.userid}</a></td>
					<td>${row.name}</td>
					<td>${row.adminName}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="row justify-content-center">
			<table style="width:100%">
				<tr>
					<td align="right"><h4>
						<a href="javascript:showList(1);" class="badge bg-info text-dark">&laquo;</a>&nbsp;
						<a href="javascript:showList(${beforePage});" class="badge bg-info text-dark">&lt;</a>&nbsp;
					</h4></td>
					<td align="center">
					<c:forEach var="pNum" items="${pages.split(',') }">
					<c:choose>
						<c:when test="${pNum == currentPage}">
							<b>[${pNum}]</b>
						</c:when>
						<c:otherwise>
							<a href="javascript:showList(${pNum});">[${pNum}]</a>
						</c:otherwise>
					</c:choose>
					</c:forEach>
					<td>
					<td align="left"><h4>
						&nbsp;<a href="javascript:showList(${afterPage});" class="badge bg-info text-dark">&gt;</a>
						&nbsp;<a href="javascript:showList(${lastPage});" class="badge bg-info text-dark">&raquo;</a>
					</h4></td>
				</tr>
			</table>
		</div>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>