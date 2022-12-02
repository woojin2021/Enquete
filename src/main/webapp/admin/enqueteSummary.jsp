<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="container">
		<c:set var="cnt" value="0"/>
		<c:forEach var="row" items="${summary}">
		<c:set var="cnt" value="${cnt + 1}"/>
		<div class="form-group row">
			<label class="col-sm-12">Q1&nbsp;&nbsp;&nbsp;${row.question}</label>
			<div class="offset-sm-1 col-sm-11">
				<p>${row.answer1} <strong>(${row.answerSummary1}/${row.totalAnswer})</strong>
				<p>${row.answer2} <strong>(${row.answerSummary2}/${row.totalAnswer})</strong>
				<p>${row.answer3} <strong>(${row.answerSummary3}/${row.totalAnswer})</strong>
				<p>${row.answer4} <strong>(${row.answerSummary4}/${row.totalAnswer})</strong>
				<div class="progress">
					<div class="progress-bar" style="width: ${row.answerShare1}%">${row.answerShare1}%</div>
					<div class="progress-bar bg-success" style="width:  ${row.answerShare2}%">${row.answerShare2}%</div>
					<div class="progress-bar bg-info" style="width:  ${row.answerShare3}%">${row.answerShare3}%</div>
					<div class="progress-bar bg-warning" style="width:  ${row.answerShare4}%">${row.answerShare4}%</div>
				</div>
			</div>
		</div>
		</c:forEach>
		<c:if test="${cnt == 0}">
<div class="alert alert-danger text-center" role="alert">
  <strong>========== NO DATA ==========</strong>
</div>
		</c:if>
	</div>