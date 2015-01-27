package com.hopon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hopon.dto.HoponAccountDTO;
import com.hopon.utils.ApplicationUtil;
import com.hopon.utils.QueryExecuter;
import com.mysql.jdbc.Statement;

public class HoponAccountDAO {
	public List<HoponAccountDTO> fetchAll(Connection con) throws SQLException {
		List<HoponAccountDTO> dtos = new ArrayList<HoponAccountDTO>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT idHoponAccounts, name, balance, description, updated_dt, updated_by, created_by, created_dt from hoponaccounts WHERE 1");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while(rs.next()) {
			HoponAccountDTO dto = new HoponAccountDTO();
			dto.setIdHoponAccounts(rs.getInt(1));
			dto.setName(rs.getString(2));
			dto.setBalance(rs.getFloat(3));
			dto.setDescription(rs.getString(4));
			dto.setUpdateDate(rs.getString(5));
			dto.setUpdatedBy(rs.getInt(6));
			dto.setCreatedBy(rs.getInt(7));
			dto.setCreatedDate(rs.getString(8));
			dtos.add(dto);
		}
		rs.close();
		pstmt.close();
		return dtos;
	}
	public HoponAccountDTO fetchAllFromName(Connection con, String name) throws SQLException {
		HoponAccountDTO dto = new HoponAccountDTO();
		StringBuilder query = new StringBuilder();
		query.append("SELECT idHoponAccounts, name, balance, description, updated_dt, updated_by, created_by, created_dt from hoponaccounts WHERE name = ?");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.setString(1, name);
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while(rs.next()) {
			dto.setIdHoponAccounts(rs.getInt(1));
			dto.setName(rs.getString(2));
			dto.setBalance(rs.getFloat(3));
			dto.setDescription(rs.getString(4));
			dto.setUpdateDate(rs.getString(5));
			dto.setUpdatedBy(rs.getInt(6));
			dto.setCreatedBy(rs.getInt(7));
			dto.setCreatedDate(rs.getString(8));
		}
		rs.close();
		pstmt.close();
		return dto;
	}
	public HoponAccountDTO fetchAllFromId(Connection con, int id) throws SQLException {
		HoponAccountDTO dto = new HoponAccountDTO();
		StringBuilder query = new StringBuilder();
		query.append("SELECT idHoponAccounts, name, balance, description, updated_dt, updated_by, created_by, created_dt from hoponaccounts WHERE idHoponAccounts = ?");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.setInt(1, id);
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while(rs.next()) {
			dto.setIdHoponAccounts(rs.getInt(1));
			dto.setName(rs.getString(2));
			dto.setBalance(rs.getFloat(3));
			dto.setDescription(rs.getString(4));
			dto.setUpdateDate(rs.getString(5));
			dto.setUpdatedBy(rs.getInt(6));
			dto.setCreatedBy(rs.getInt(7));
			dto.setCreatedDate(rs.getString(8));
		}
		rs.close();
		pstmt.close();
		return dto;
	}
	public HoponAccountDTO fetchHoponAccountBalanceById(Connection con,
			HoponAccountDTO dto, int id) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT balance FROM hoponaccounts WHERE idHoponAccounts="+id);
		PreparedStatement psmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(psmt, query.toString());
		if (rs.next()) {
			dto.setBalance(rs.getFloat(1));
			System.out.println("Balance fro HoponAccount:" + rs.getFloat(1));
		}
		return dto;

	}
	
	public HoponAccountDTO updateHoponAccountBalanceById(Connection con,
			HoponAccountDTO dto, int id) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE hoponaccounts SET balance = ?, updated_dt = ? WHERE idHoponAccounts ="+id);
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.setFloat(1, dto.getBalance());
		pstmt.setString(2, ApplicationUtil.currentTimeStamp());
		pstmt.executeUpdate();
		System.out.println("Excute update:"+pstmt.executeUpdate());
		pstmt.close();
		return dto;
	}
	
	
	
	

}
