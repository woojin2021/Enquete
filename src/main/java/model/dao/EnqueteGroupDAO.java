package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBAccess;
import model.dto.EnqueteGroupDTO;

public class EnqueteGroupDAO {
	private static EnqueteGroupDAO instance = new EnqueteGroupDAO();
	
	private EnqueteGroupDAO() {}
	
	public static EnqueteGroupDAO getInstance() {
		return instance;
	}

	public EnqueteGroupDTO selectById(int groupId) {
		EnqueteGroupDTO group = null;

		String sql = "SELECT GroupId, GroupName, MaxEnquete, ExpireDate, Creater, RegDate FROM EnqueteGroup"
				+ " WHERE GroupId = ?";
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, groupId);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						group = new EnqueteGroupDTO();
						group.setGroupId(rs.getInt("GroupId"));
						group.setGroupName(rs.getString("GroupName"));
						group.setMaxEnquete(rs.getInt("MaxEnquete"));
						group.setExpireDate(rs.getDate("ExpireDate"));
						group.setCreater(rs.getString("Creater"));
						group.setRegDate(rs.getDate("RegDate"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return group;
	}
	
	public EnqueteGroupDTO[] selectAll(String orderBy) {
		EnqueteGroupDTO[] result = new EnqueteGroupDTO[0];

		String sql = "SELECT g.*, a.Name FROM EnqueteGroup g"
				+ " INNER JOIN AddressTBL a ON g.creater = a.userId";
		if (orderBy != null && orderBy.isBlank() == false) {
			sql += " ORDER BY " + orderBy;
		} else {
			sql += " ORDER BY GroupId";
		}
		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					ArrayList<EnqueteGroupDTO> groups = new ArrayList<>();
					while (rs.next()) {
						EnqueteGroupDTO group = new EnqueteGroupDTO();
						group.setGroupId(rs.getInt("GroupId"));
						group.setGroupName(rs.getString("GroupName"));
						group.setMaxEnquete(rs.getInt("MaxEnquete"));
						group.setExpireDate(rs.getDate("ExpireDate"));
						group.setCreater(rs.getString("Creater"));
						group.setRegDate(rs.getDate("RegDate"));
						group.setCreaterName(rs.getString("Name"));
						groups.add(group);
					}
					result = groups.toArray(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int insert(EnqueteGroupDTO group) {
		int result = 0;

		String sql = "INSERT INTO EnqueteGroup (GroupId, GroupName, MaxEnquete, ExpireDate, Creater, RegDate)"
				+ " VALUES (EnqueteGroup_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				int index = 0;
				pstmt.setString(++index, group.getGroupName());
				pstmt.setInt(++index, group.getMaxEnquete());
				pstmt.setDate(++index, group.getExpireDate());
				pstmt.setString(++index, group.getCreater());
				pstmt.setDate(++index, new java.sql.Date(new java.util.Date().getTime()));
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int update(EnqueteGroupDTO group) {
		int result = 0;

		String sql = "UPDATE EnqueteGroup SET GroupName=?, MaxEnquete=?, ExpireDate=?, Creater=?"
				+ " WHERE GroupId = ?";
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				int index = 0;
				pstmt.setString(++index, group.getGroupName());
				pstmt.setInt(++index, group.getMaxEnquete());
				pstmt.setDate(++index, group.getExpireDate());
				pstmt.setString(++index, group.getCreater());
 				pstmt.setInt(++index, group.getGroupId());
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteById(int groupId) {
		int result = 0;
		
		// TODO: delete enquete and answer

		String sql;
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
//			sql = "DELETE FROM Answer WHERE EnqueteId IN ("
//					+ "SELECT EnqueteId FROM Enquete WHERE GroupId=?";
//			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				pstmt.setInt(1, groupId);
//				result = pstmt.executeUpdate();
//			}
			sql = "DELETE FROM Enquete WHERE GroupId=?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, groupId);
				result = pstmt.executeUpdate();
			}
			sql = "DELETE FROM EnqueteGroup WHERE GroupId=?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, groupId);
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
