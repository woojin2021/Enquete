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
<script type="text/javascript" src="<c:url value="/resources/js/createQuestion.js"/>"></script>
<meta charset="UTF-8">
<title>앙케이트 관리</title>
<script type="text/javascript">
	function regist() {
		// 설문지를 json(text)으로 변환해서 저장
		const json = convertToJSON("questionContain");
		document.getElementById("jsonData").value = json;
		
		let beginDate = document.getElementById("beginDate").value;
		let endDate = document.getElementById("endDate").value;
		if (beginDate=="" && endDate=="") {
			// 상시 오픈
		} else if (beginDate=="" || endDate=="" || beginDate > endDate) {
			alert("공개기간을 확인해주세요.");
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
			<h1 class="display-3">앙케이트 신규 등록</h1>
			<p id="chkhtml">
		</div>
	</div>
	
	<div class="container">
		<form name="enqueteGroup" class="form-horizontal off-sm-2" action="<c:url value="/EnqueteCreateAction.do"/>" method="post">
			<input type="hidden" name="enqueteId" value="${enquete.enqueteId}">
			<input type="hidden" name="groupId" value="<%=request.getParameter("groupId")%>">
			<input type="hidden" id="jsonData" name="jsonData" value="${enquete.jsonData}">
			<div class="form-group row">
				<label class="col-sm-2">앙케이트명</label>
				<div class="col-sm-3">
					<input type="text" name="enqueteName" class="form-control" required="required" value="${enquete.enqueteName}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">공개기간</label>
				<div class="col-sm-2">
					<label for="beginDate" class="form-label">시작일</label>
					<input type="date" id="beginDate" name="beginDate" class="form-control" value="${enquete.beginDate}">
				</div>
				<div class="col-sm-2">
					<label for="endDate" class="form-label">종료일</label>
					<input type="date" id="endDate" name="endDate" class="form-control" value="${enquete.endDate}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상태</label>
				<div class="col-sm-3">
					<input type="radio" name="openStatus" value="0" ${(enquete.openStatus == 0)?'checked':''} checked="checked">준비중
					<input type="radio" name="openStatus" value="1" ${(enquete.openStatus == 1)?'checked':''} >공개
					<input type="radio" name="openStatus" value="2" ${(enquete.openStatus == 2)?'checked':''} >종료
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">설문지</label>
				<div class="col-sm-3">
					<input type="button" class="btn btn-success" value="설문추가" onclick="addQuestion('questionContain')">
					<input type="button" class="btn btn-warning" value="설문삭제" onclick="delQuestion('questionContain')">
				</div>
			</div>
			<div id="questionContain" class="form-group row">
				<div class="form-group row">
					<label class="offset-sm-1 col-sm-1">#1</label>
					<div class="col-sm-8" style="background-color: lightblue;">
						<div class="form-group row">
							<input type="text" name="q1" class="form-control" value="설문내용">
						</div>
						<div class="form-group row">
							<div class="col-sm-3">
								<input type="text" name="q1_1" value="선택1" class="form-control">
							</div>
							<div class="col-sm-3">
								<input type="text" name="q1_2" value="선택2" class="form-control">
							</div>
							<div class="col-sm-3">
								<input type="text" name="q1_3" value="선택3" class="form-control">
							</div>
							<div class="col-sm-3">
								<input type="text" name="q1_4" value="선택4" class="form-control">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록" onclick="return regist();">
					<a href="<c:url value="/EnqueteListAction.do"/>" class="btn btn-danger">취소</a>
				</div>
			</div>
		</form>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>