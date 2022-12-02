<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	function deleteConfirm(enqueteId) {
		if(confirm("앙케이트를 삭제하겠습니까?\n앙케이트를 삭제할 경우 투고도 함께 삭제됩니다.")) {
			document.enqueteEdit.action = "EnqueteDeleteAction.do";
			document.enqueteEdit.submit();
		}
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp">
		<jsp:param name="groupId" value="${enquete.groupId}"/>
	</jsp:include>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">앙케이트 수정</h1>
			<p id="chkhtml">
		</div>
	</div>
	
	<div class="container">
		<form action="<c:url value="/EnqueteUpdateAction.do"/>" name="enqueteEdit" class="form-horizontal off-sm-2" method="post">
			<input type="hidden" name="enqueteId" value="${enquete.enqueteId}">
			<input type="hidden" name="groupId" value="${enquete.groupId}">
			<input type="hidden" id="jsonData" name="jsonData" value='${enquete.jsonData}'>
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
			</div>
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록" onclick="return regist();">
					<a href="<c:url value="javascript:deleteConfirm();"/>" class="btn btn-danger">삭제</a>
				</div>
			</div>
		</form>
	</div>
	
<script type="text/javascript">
	const jsonEnquete = document.getElementById("jsonData").value;
	const htmlEnquete = convertToHTML(jsonEnquete);
	const container = document.getElementById("questionContain");
	for (let i = 0; i < htmlEnquete.length; i++) {
		container.appendChild(htmlEnquete[i]);
	}
</script>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>