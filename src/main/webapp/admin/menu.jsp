<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String userName = (String)session.getAttribute(common.Configure.SSM_USERNAME);
%>
<nav class="navbar navbar-expand navbar-dark bg-dark">
	<div class="container-fluid">
		<div class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<li style="padding-top:7px; color:white;">[<%=userName%>님]</li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="/EnqueteGroupListAction.do"/>">그룹 선택</a></li>
				<c:if test="${param.groupId != null }">
				<li class="nav-item"><a class="nav-link" href="<c:url value="/EnqueteListAction.do?groupId=${param.groupId}"/>">앙케이트 목록</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link" href="<c:url value="/LogoutAction.do"/>">로그아웃</a></li>
			</ul>
		</div>
	</div>
</nav>
