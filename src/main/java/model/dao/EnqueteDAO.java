package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBAccess;
import model.dto.EnqueteDTO;

public class EnqueteDAO {
	public static EnqueteDAO instance = new EnqueteDAO();

	public static EnqueteDAO getInstance() {
		return instance;
	}
	
	private EnqueteDAO() {}
	
	public EnqueteDTO selectById(int enqueteId) {
		EnqueteDTO enquete = null;

		String sql = "SELECT * FROM Enquete WHERE EnqueteId = ?";
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, enqueteId);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						enquete = new EnqueteDTO();
						enquete.setEnqueteId(rs.getInt("EnqueteId"));
						enquete.setEnqueteName(rs.getString("EnqueteName"));
						enquete.setGroupId(rs.getInt("GroupId"));
						enquete.setBeginDate(rs.getDate("BeginDate"));
						enquete.setEndDate(rs.getDate("EndDate"));
						enquete.setOpenStatus(rs.getInt("OpenStatus"));
						enquete.setJsonData(rs.getString("JsonData"));
						enquete.setRegDate(rs.getDate("RegDate"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return enquete;
	}
	
	public EnqueteDTO[] selectByGroupId(int groupId, String orderBy) {
		EnqueteDTO[] result = new EnqueteDTO[0];

		String sql = "SELECT * FROM Enquete WHERE groupId=?";
		if (orderBy != null && orderBy.isBlank() == false) {
			sql += " ORDER BY " + orderBy;
		} else {
			sql += " ORDER BY EnqueteId";
		}
		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, groupId);
				try (ResultSet rs = pstmt.executeQuery()) {
					ArrayList<EnqueteDTO> enquetes = new ArrayList<>();
					while (rs.next()) {
						EnqueteDTO enquete = new EnqueteDTO();
						enquete.setEnqueteId(rs.getInt("EnqueteId"));
						enquete.setEnqueteName(rs.getString("EnqueteName"));
						enquete.setGroupId(rs.getInt("GroupId"));
						enquete.setBeginDate(rs.getDate("BeginDate"));
						enquete.setEndDate(rs.getDate("EndDate"));
						enquete.setOpenStatus(rs.getInt("OpenStatus"));
						enquete.setJsonData(rs.getString("JsonData"));
						enquete.setRegDate(rs.getDate("RegDate"));
						enquetes.add(enquete);
					}
					result = enquetes.toArray(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public EnqueteDTO[] selectAllOpened() {
		EnqueteDTO[] result = new EnqueteDTO[0];

		String sql = "SELECT * FROM Enquete WHERE OpenStatus = 1 AND"
				+ " (BeginDate IS NULL OR TO_CHAR(SYSDATE, 'YYYYMMDD') BETWEEN TO_CHAR(BeginDate, 'YYYYMMDD') AND TO_CHAR(EndDate, 'YYYYMMDD'))"
				+ " ORDER BY EnqueteId DESC";
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					ArrayList<EnqueteDTO> enquetes = new ArrayList<>();
					while (rs.next()) {
						EnqueteDTO enquete = new EnqueteDTO();
						enquete.setEnqueteId(rs.getInt("EnqueteId"));
						enquete.setEnqueteName(rs.getString("EnqueteName"));
						enquete.setGroupId(rs.getInt("GroupId"));
						enquete.setBeginDate(rs.getDate("BeginDate"));
						enquete.setEndDate(rs.getDate("EndDate"));
						enquete.setOpenStatus(rs.getInt("OpenStatus"));
						enquete.setJsonData(rs.getString("JsonData"));
						enquete.setRegDate(rs.getDate("RegDate"));
						enquetes.add(enquete);
					}
					result = enquetes.toArray(result);
				}
			}
		} catch (SQLException e) {
			System.out.println("sql:" + sql);
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insert(EnqueteDTO enquete) {
		int result = 0;

		String sql = "INSERT INTO Enquete (EnqueteId, EnqueteName, GroupId, BeginDate, EndDate, OpenStatus, JsonData, RegDate)"
				+ " VALUES (Enquete_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				int index = 0;
				pstmt.setString(++index, enquete.getEnqueteName());
				pstmt.setInt(++index, enquete.getGroupId());
				pstmt.setDate(++index,enquete.getBeginDate());
				pstmt.setDate(++index,enquete.getEndDate());
				pstmt.setInt(++index, enquete.getOpenStatus());
				pstmt.setString(++index, enquete.getJsonData());
				pstmt.setDate(++index, new java.sql.Date(new java.util.Date().getTime()));
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int update(EnqueteDTO enquete) {
		int result = 0;

		String sql = "UPDATE Enquete SET EnqueteName=?, GroupId=?, BeginDate=?, EndDate=?, OpenStatus=?, JsonData=?"
				+ " WHERE EnqueteId=?";
		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				int index = 0;
				pstmt.setString(++index, enquete.getEnqueteName());
				pstmt.setInt(++index, enquete.getGroupId());
				pstmt.setDate(++index,enquete.getBeginDate());
				pstmt.setDate(++index,enquete.getEndDate());
				pstmt.setInt(++index, enquete.getOpenStatus());
				pstmt.setString(++index, enquete.getJsonData());
				pstmt.setInt(++index, enquete.getEnqueteId());
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteById(int enqueteId) {
		int result = 0;
		
		// TODO: delete answer

		String sql;
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			sql = "DELETE FROM Answer WHERE EnqueteId=?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, enqueteId);
				result = pstmt.executeUpdate();
			}
			sql = "DELETE FROM Enquete WHERE EnqueteId=?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, enqueteId);
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
