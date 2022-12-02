<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>앙케이트</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<script type="text/javascript">
function convertToHTMLCode(json) {
	const objJson = eval("(" + json + ")");
	const enquetes = objJson.enquetes;
	let htmlCode = '';
	for (let i = 0; i < enquetes.length; i++) {
		const subject = enquetes[i].subject;
		htmlCode += '<div class="form-group row">';
		htmlCode += '<label class="col-sm-12">Q' + (i+1) + '&nbsp;&nbsp;&nbsp;' + subject + '</label>';
		htmlCode += '<div class="offset-sm-1 col-sm-8">';
		let defaultCheck = 'checked';
		for (let j = 0; j < enquetes[i].options.length; j++) {
			const option = enquetes[i].options[j].option;
			htmlCode += '<div class="form-check">';
			htmlCode += '<input class="form-check-input" type="radio" name="ANS_' + (i+1)+ '" value="' + (j+1) + '" ' + defaultCheck + '>' + option;
			htmlCode += '</div>';
			defaultCheck = '';
		}
		htmlCode += '</div></div>';
	}
	
	return htmlCode;
}
</script>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-5">${enquete.enqueteName}</h1>
			<p id="chkhtml">
		</div>
	</div>
	
	<!-- start -->
	<form action="<c:url value="/EnqueteAnswerAction.do"/>" name="enqueteGroup" class="form-horizontal off-sm-2" method="post">
		<input type="hidden" name="enqueteId" value="${enquete.enqueteId}">
		<input type="hidden" id="jsonData" name="jsonData" value='${enquete.jsonData}'>
		<!-- start -->
		<div id="questionContain" class="container">
			<div class="form-group row">
				<label class="col-sm-12">Q1</label>
				<div class="offset-sm-1 col-sm-8">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="exampleRadios" value="option1" checked>Default radio
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="exampleRadios" value="option2">Second default radio
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="exampleRadios" value="option3" disabled>Disabled radio
					</div>
				</div>
			</div>
		</div>
		<!-- end -->
		<div class="container">
			<div class="form-group row justify-content-center">
				<div class="col-sm-6">
					<input type="submit" class="btn btn-primary" value="등록">
				</div>
				<div class="col-sm-6">
					<a class="btn btn-danger" href="welcome.jsp">취소</a>
				</div>
			</div>
		</div>
	</form>

<script type="text/javascript">
	const jsonEnquete = document.getElementById("jsonData").value;
	const htmlEnquete = convertToHTMLCode(jsonEnquete);
	const container = document.getElementById("questionContain");
	container.innerHTML = htmlEnquete;
</script>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>