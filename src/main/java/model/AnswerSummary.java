package model;

public class AnswerSummary {
	private int questionId;
	private String question;
	private String answer1;
	private int answerSummary1;
	private String answer2;
	private int answerSummary2;
	private String answer3;
	private int answerSummary3;
	private String answer4;
	private int answerSummary4;
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public int getAnswerSummary1() {
		return answerSummary1;
	}

	public void setAnswerSummary1(int answerSummary1) {
		this.answerSummary1 = answerSummary1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public int getAnswerSummary2() {
		return answerSummary2;
	}

	public void setAnswerSummary2(int answerSummary2) {
		this.answerSummary2 = answerSummary2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public int getAnswerSummary3() {
		return answerSummary3;
	}

	public void setAnswerSummary3(int answerSummary3) {
		this.answerSummary3 = answerSummary3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public int getAnswerSummary4() {
		return answerSummary4;
	}

	public void setAnswerSummary4(int answerSummary4) {
		this.answerSummary4 = answerSummary4;
	}

	public double getAnswerShare1() {
		return getShare(answerSummary1);
	}

	public double getAnswerShare2() {
		return getShare(answerSummary2);
	}

	public double getAnswerShare3() {
		return getShare(answerSummary3);
	}

	public double getAnswerShare4() {
		return getShare(answerSummary4);
	}
	
	public int getTotalAnswer() {
		return answerSummary1 + answerSummary2 + answerSummary3 + answerSummary4;
	}
	
	private double getShare(int count) {
		double totalAnswer =  getTotalAnswer();
		double share = count / totalAnswer;
		share = Math.round(share * 100 * 100) / 100.0;
		return share;
	}

}
