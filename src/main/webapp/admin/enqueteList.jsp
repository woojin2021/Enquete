<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ page import="model.dto.AddressDTO" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<script type="text/javascript" src="./resources/js/createQuestion.js"></script>
<script type="text/javascript">
	function showList(pageNum) {
		document.getElementById("pageNum").value = pageNum;
		document.search.submit();
	}
	var xhrObject; // XMLhttpRequest객체를 저장할 변수, 전역변수선언
	var currentRowId;
	function showSummary(rowId, enqueteId) {		
		xhrObject = new XMLHttpRequest();
		
		currentRowId = rowId;
		var url = '<c:url value="/EnqueteSummaryAction.do"/>'; //요청 url 설정
		
		var reqparam = "enqueteId=" + enqueteId;
		xhrObject.onreadystatechange = showSummaryImple; // 응답결과를 처리메소드인 resultProcess() 메소드 설정
		xhrObject.open("Post", url, "true"); //서버의 요청설정 -url변수에 설정된 리소스를 요청할 준비
		xhrObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xhrObject.send(reqparam);
	}
	function showSummaryImple() {
		if(xhrObject.readyState == 4){
			if(xhrObject.status == 200){
				var result = xhrObject.responseText;
				
				const oldSummary = document.getElementById("summary");
				if (oldSummary != null) {
					oldSummary.remove();
				}
				
				const currentRow = document.getElementById(currentRowId);
				const table = currentRow.parentElement;
				
				const newRow = document.createElement("tr");
				newRow.setAttributeNode(createAttribute("id", "summary"));
				const newContent = document.createElement("td");
				newContent.setAttributeNode(createAttribute("colspan", "4"));
				newContent.innerHTML = result;
				newRow.appendChild(newContent);
				
				if (currentRow == table.lastElementChild) {
					table.insertBefore(newRow, null);
				} else {
					const rows = table.children;
					for (let i = 0; i < rows.length; i++) {
						if (currentRow == rows[i]) {
							table.insertBefore(newRow, rows[i + 1]);
							break;
						}
					}
				}
			}
		}
	}
</script>
<style type="text/css">
.donut {
    width: calc(100% - 16px);
    padding-bottom: calc(100% - 16px);
    margin: 0 auto;
    border-radius: 50%;
    position: relative;
    text-align: center;
    transition: background .3s ease-in-out;
    /*background: conic-gradient(#3F8BC9 0% 72%, #F2F2F2 72% 100%);*/
}
</style>
<meta charset="UTF-8">
<title>앙케이트 관리</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">앙케이트 목록</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<table style="width:100%">
				<tr>
					<td><h3>${group.groupName}${fn:length(enquetes) >= group.maxEnquete}</h3></td>
					<td align="right">
						<c:choose>
						<c:when test="${fn:length(enquetes) >= group.maxEnquete }">
						<a href="javascript:alert('최대치(' + ${group.maxEnquete} + ')에 도달하여 더 이상 앙케이트를 작성할 수 없습니다.');" class="btn btn-secondary">신규</a>
						</c:when>
						<c:otherwise>
						<a href="admin/enqueteAdd.jsp?groupId=${group.groupId}" class="btn btn-success">신규</a>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</div>
		<div style="padding-top: 50px;">
			<table class="table table-hover">
				<tr>
					<th>#</th>
					<th>앙케이트</th>
					<th>상태</th>
					<th>관리</th>
				</tr>
				<c:set var="num" value="0" />
				<c:forEach var="row" items="${enquetes}">
				<c:set var="num" value="${num + 1}" />
				<tr id="row_${num}">
					<td>${num}</td>
					<td>
						<a href="EnqueteUpdateForm.do?enqueteId=${row.enqueteId}">${row.enqueteName}</a>
					</td>
					<td>${row.statusName}</td>
					<td>
						<input type="button" class="btn btn-primary" value="집계" onclick="showSummary('row_${num}', '${row.enqueteId}')">
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>