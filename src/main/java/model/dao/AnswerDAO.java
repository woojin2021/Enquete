package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBAccess;
import model.AnswerSummary;
import model.dto.AnswerDTO;

public class AnswerDAO {
	public static AnswerDAO instance = new AnswerDAO();

	public static AnswerDAO getInstance() {
		return instance;
	}
	
	private AnswerDAO() {}

	public AnswerSummary[] selectByEnqueteId(int enqueteId) {
		AnswerSummary[] result = new AnswerSummary[0];

//		int total = 0;
		String sql = "";
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
//			sql = "SELECT COUNT(QuestionId) FROM (SELECT QuestionId FROM Answer WHERE EnqueteId = ? GROUP BY QuestionId) T";
//			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setInt(1, enqueteId);
//				try (ResultSet rs = pstmt.executeQuery()) {
//					if (rs.next()) {
//						total = rs.getInt(1);
//					}
//				}
//			}
			sql = "SELECT * FROM (SELECT QuestionId, AnswerValue FROM Answer WHERE EnqueteId = ?)"
					+ " PIVOT(COUNT(AnswerValue) FOR AnswerValue IN (1, 2, 3, 4)) ORDER BY QuestionId";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, enqueteId);
				ArrayList<AnswerSummary> summaries = new ArrayList<>();
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						AnswerSummary summary = new AnswerSummary();
						summary.setAnswerSummary1(rs.getInt("1"));
						summary.setAnswerSummary2(rs.getInt("2"));
						summary.setAnswerSummary3(rs.getInt("3"));
						summary.setAnswerSummary4(rs.getInt("4"));
						summaries.add(summary);
					}
					result = summaries.toArray(result);
				}
			}
		} catch (SQLException e) {
			System.out.println("sql:" + sql);
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insert(AnswerDTO answer) {
		int result = 0;

		String sql = "INSERT INTO Answer (AnswerId, EnqueteId, QuestionId, AnswerValue, RegDate)"
				+ " VALUES (Answer_seq.NEXTVAL, ?, ?, ?, ?)";
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				int index = 0;
				pstmt.setInt(++index, answer.getEnqueteId());
				pstmt.setInt(++index, answer.getQuestionId());
				pstmt.setInt(++index, answer.getAnswerValue());
				pstmt.setDate(++index, new java.sql.Date(new java.util.Date().getTime()));
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("sql:" + sql);
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteByEnqueteId(int enqueteId) {
		int result = 0;
		
		String sql = "DELETE FROM Answer WHERE EnqueteId=?";
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, enqueteId);
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("sql:" + sql);
			e.printStackTrace();
		}
		
		return result;
	}
	
}
