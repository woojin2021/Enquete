package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DBAccess;
import model.UserGroup;
import model.dto.AddressDTO;

public class AddressDAO {
	private static AddressDAO instance = new AddressDAO();
	
	private AddressDAO() {}
	
	public static AddressDAO getInstance() {
		return instance;
	}	

	public int insert(AddressDTO user) {
		int result = 0;

		String sql = "INSERT INTO AddressTBL (UserId, Password, Name, Mailaddress, Extend00, Extend01, Admin, Regdate)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				int index = 0;
				pstmt.setString(++index, user.getUserid());
				pstmt.setString(++index, user.getPassword());
				pstmt.setString(++index, user.getName());
				pstmt.setString(++index, user.getMailaddress());
				pstmt.setString(++index, user.getExtend00());
				pstmt.setString(++index, user.getExtend01());
				pstmt.setInt(1, user.getAdmin());
				pstmt.setDate(++index, new java.sql.Date(new java.util.Date().getTime()));
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public AddressDTO selectByUserId(String userId) {
		AddressDTO user = null;
		
		DBAccess acc = new DBAccess();
		String sql = "SELECT * FROM AddressTBL WHERE UserID = ?";
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, userId);
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						user = new AddressDTO();
						user.setUserid(rs.getString("UserId"));
					    user.setPassword(rs.getString("Password"));
					    user.setName(rs.getString("Name"));
					    user.setMailaddress(rs.getString("Mailaddress"));
					    user.setExtend00(rs.getString("Extend00"));
					    user.setExtend01(rs.getString("Extend01"));
					    user.setExtend02(rs.getString("Extend02"));
					    user.setExtend03(rs.getString("Extend03"));
					    user.setExtend04(rs.getString("Extend04"));
					    user.setExtend05(rs.getString("Extend05"));
					    user.setAdmin(rs.getInt("Admin"));
					    user.setLastlogindate(rs.getDate("Lastlogindate"));
					    user.setRegdate(rs.getDate("Regdate"));
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("failed to select AddressTBL: " + sql);
			e.printStackTrace();
		}
		
		return user;
	}

	public int selectCount(String whereSql) {
		int result = 0;
		
		DBAccess acc = new DBAccess();
		String sql = "SELECT COUNT(0) FROM AddressTBL" + whereSql;
		try (Connection conn = acc.getConnection()) {
			try (Statement stmt = conn.createStatement()) {
				try (ResultSet rs = stmt.executeQuery(sql)) {
					if (rs.next()) {
						result = rs.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	public AddressDTO[] selectList(int start, int end, String whereSql) {
		AddressDTO[] result = new AddressDTO[0];
		
		String sql = "SELECT v.* FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY UserId) pin, UserId, Name, Admin, LastLoginDate"
				+ " FROM AddressTBL" + whereSql + ") v"
				+ " WHERE pin BETWEEN ? AND ?";
		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				try (ResultSet rs = pstmt.executeQuery()) {
					ArrayList<AddressDTO> aList = new ArrayList<>();
					while (rs.next()) {
						AddressDTO user = new AddressDTO();
						user.setUserid(rs.getString("UserId"));
					    user.setName(rs.getString("Name"));
					    user.setAdmin(rs.getInt("Admin"));
					    user.setLastlogindate(rs.getDate("LastLoginDate"));
						aList.add(user);
					}
					result = aList.toArray(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateDetail(AddressDTO user) {
		int result = 0;

		String sql = "UPDATE AddressTBL SET Password=?, Name=?, Mailaddress=?, Extend00=?, Extend01=?, Admin=? WHERE UserID=?";
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				int index = 0;
				pstmt.setString(++index, user.getPassword());
				pstmt.setString(++index, user.getName());
				pstmt.setString(++index, user.getMailaddress());
				pstmt.setString(++index, user.getExtend00());
				pstmt.setString(++index, user.getExtend01());
				pstmt.setInt(++index, user.getAdmin());
				pstmt.setString(++index, user.getUserid());
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int delete(AddressDTO user) {
		int result = 0;

		String sql = "DELETE FROM AddressTBL WHERE UserID=?";
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, user.getUserid());
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateAdmin(AddressDTO user) {
		int result = 0;

		String sql = "UPDATE AddressTBL SET Admin=? WHERE UserID=?";
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, user.getAdmin());
				pstmt.setString(2, user.getUserid());
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateLastLogin(AddressDTO user) {
		int result = 0;

		String sql = "UPDATE AddressTBL SET LastLoginDate=? WHERE UserID=?";
//		System.out.println("sql:" + sql);
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, user.getAdmin());
				pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public UserGroup[] getUserGroupList() {
		UserGroup[] result = new UserGroup[0];
		
		String sql = "SELECT Extend00, Extend01 FROM AddressTBL WHERE Extend00 IS NOT NULL AND Extend01 IS NOT NULL GROUP BY Extend00, Extend01 ORDER BY Extend00, Extend01";
		DBAccess acc = new DBAccess();
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					ArrayList<UserGroup> groups = new ArrayList<>();
					UserGroup temp = null;
					String groupName = "";
					while (rs.next()) {
						if (!rs.getString("Extend00").equals(groupName)) {
							temp = new UserGroup(rs.getString("Extend00"));
							temp.addSubGroup(new UserGroup(rs.getString("Extend01")));
							groups.add(temp);
							groupName = rs.getString("Extend00");
						} else {
							temp.addSubGroup(new UserGroup(rs.getString("Extend01")));
						}
					}
					result = groups.toArray(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Deprecated
	public void createDummyData() {
		DBAccess acc = new DBAccess();
		String sql = "INSERT INTO AddressTBL (UserId, Password, Name, Mailaddress, Extend00, Extend01, Admin, Regdate)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = acc.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				for (int i = 0; i < 256; i++) {
					int index = 0;
					pstmt.setString(++index, "user" + String.format("%03d", i));
					pstmt.setString(++index, "pass" + String.format("%03d", i));
					pstmt.setString(++index, "유저" + String.format("%03d", i));
					pstmt.setString(++index, "user" + String.format("%03d", i) + "@example.com");
					pstmt.setString(++index, "Section-" + (i / 100));
					pstmt.setString(++index, "Part-" + (i / 10));
					pstmt.setInt(++index, 2); //0:사이트 관리자, 1:앙케이트 관리자, 2:일반 유저
					pstmt.setDate(++index, new java.sql.Date(new java.util.Date().getTime()));
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			System.err.println("failed to create dump");
			e.printStackTrace();
		}
		
	}

}
