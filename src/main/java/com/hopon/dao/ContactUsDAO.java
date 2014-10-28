package com.hopon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hopon.dto.ContactusDTO;
import com.hopon.utils.LoggerSingleton;
/**
 * 
 * @author:Nagaraja N
 * 
 * This is<code>ContactUs</code>Method.
 *
 */

public class ContactUsDAO {
	public void insertContactInfo(Connection con, ContactusDTO dto)
			throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO contactus(first_name, last_name, mobile_no, email_id, comment) VALUES(?, ?, ?, ?, ?)");
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, dto.getFirst_name());
			pstmt.setString(2, dto.getLast_name());
			pstmt.setString(3, dto.getMobile_no());
			pstmt.setString(4, dto.getEmail_id());
			pstmt.setString(5, dto.getComment());
			int i=pstmt.executeUpdate();
			
		} catch (Exception e) {
			LoggerSingleton.getInstance().error(
					e.getStackTrace()[0].getClassName() + "->"+ e.getStackTrace()[0].getMethodName()+ "() : "+ e.getStackTrace()[0].getLineNumber() + " :: "+ e.getMessage());
		} finally {

			pstmt.close();
		}

	}

}
