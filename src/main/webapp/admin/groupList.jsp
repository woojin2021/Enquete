<%@page import="common.Configure"%>
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
<title>앙케이트 관리</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">앙케이트 그룹</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<table style="width:100%">
				<tr>
					<td align="right">
					<c:choose>
						<c:when test="<%=common.RequestUtil.isAdmin(request)%>"><a href="admin/groupAdd.jsp" class="btn btn-success">신규</a></c:when>
						<c:otherwise><a href="#" class="btn btn-secondary">신규</a></c:otherwise>
					</c:choose>
					</td>
				</tr>
			</table>
		</div>
		<div style="padding-top: 50px;">
			<table class="table table-hover">
				<tr>
					<th>#</th>
					<th>그룹명</th>
					<th>앙케이트</th>
					<th>관리자</th>
					<th>유효기간</th>
				</tr>
				<c:set var="userId" value="<%=session.getAttribute(Configure.SSM_USERID)%>"/>
				<c:forEach var="row" items="${groups}">
				<tr>
					<td>
					<c:choose>
						<c:when test="<%=common.RequestUtil.isAdmin(request)%>"><a href="EnqueteGroupViewAction.do?groupId=${row.groupId}" class="btn btn-warning">편집</a></c:when>
						<c:otherwise><a href="#" class="btn btn-secondary">편집</a></c:otherwise>
					</c:choose>
					</td>
					<td>
					<c:choose>
						<c:when test="${row.expired == false && userId == row.creater}"><a href="EnqueteListAction.do?groupId=${row.groupId}">${row.groupName}</a></c:when>
						<c:otherwise>${row.groupName}</c:otherwise>
					</c:choose>
					<td>${row.maxEnquete}</td>
					<td>${row.createrName}</td>
					<td>${row.expireDate}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>