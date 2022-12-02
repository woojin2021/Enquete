package model.dto;

import java.sql.Date;

public class AnswerDTO {
	private int answerId;
	private int enqueteId;
	private int questionId;
    private int answerValue;
    private int answerSummary;
	private Date regDate;
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getEnqueteId() {
		return enqueteId;
	}
	public void setEnqueteId(int enqueteId) {
		this.enqueteId = enqueteId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getAnswerValue() {
		return answerValue;
	}
	public void setAnswerValue(int answer) {
		this.answerValue = answer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
    public int getAnswerSummary() {
		return answerSummary;
	}
	public void setAnswerSummary(int answerSummary) {
		this.answerSummary = answerSummary;
	}

}
