package com.hopon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hopon.dto.HoponAccountDTO;
import com.hopon.dto.PaymentTxnsDTO;
import com.hopon.dto.UserRegistrationDTO;
import com.hopon.utils.ApplicationUtil;
import com.hopon.utils.QueryExecuter;
import com.hopon.utils.Validator;
import com.mysql.jdbc.Statement;

public class PaymentTxnsDAO {
	public PaymentTxnsDTO add(Connection con, PaymentTxnsDTO dto) throws SQLException {
		if(Validator.isEmpty(dto.getPaymentDT())) {
			dto.setPaymentDT(ApplicationUtil.currentTimeStamp());
		}
		if(Validator.isEmpty(dto.getUpdateDate())) {
			dto.setUpdateDate(ApplicationUtil.currentTimeStamp());
		}
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO paymenttxns(paymentTxnsId, FromPayer, ToPayee, seeker_id, TripDetails, PaymentDT, Notes, UpdatedDT, UpdatedBy, CreatedDT, CreatedBy, Dist, amount) values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		PreparedStatement pstmt = con.prepareStatement(query.toString() ,Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, dto.getFromPayer());
		pstmt.setInt(2, dto.getToPayee());
		pstmt.setInt(3, dto.getSeekerId());
		pstmt.setString(4, dto.getTripDetails());
		pstmt.setString(5, dto.getPaymentDT());
		pstmt.setString(6, dto.getNotes());
		pstmt.setString(7, dto.getUpdateDate());
		pstmt.setInt(8, dto.getUpdatedBy());
		pstmt.setString(9, dto.getCreatedDate());
		pstmt.setInt(10, dto.getCreatedBy());
		pstmt.setFloat(11, dto.getDist());
		pstmt.setFloat(12, dto.getAmount());
		pstmt.executeUpdate();
		ResultSet tableKeys = pstmt.getGeneratedKeys();
		tableKeys.next();
		int autoGeneratedID = tableKeys.getInt(1);
		tableKeys.close();
		pstmt.close();
		dto.setPaymentTxnsId(autoGeneratedID);
		return dto;
	}
	public List<PaymentTxnsDTO> fetchAll(Connection con) throws SQLException {
		List<PaymentTxnsDTO> dtos = new ArrayList<PaymentTxnsDTO>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT paymentTxnsId, FromPayer, ToPayee, seeker_id, TripDetails, PaymentDT, Notes, UpdatedDT, UpdatedBy, CreatedDT, CreatedBy, Dist, amount from paymenttxns WHERE 1");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while(rs.next()) {
			PaymentTxnsDTO dto = new PaymentTxnsDTO();
			dto.setPaymentTxnsId(rs.getInt(1));
			dto.setFromPayer(rs.getInt(2));
			dto.setToPayee(rs.getInt(3));
			dto.setSeekerId(rs.getInt(4));
			dto.setTripDetails(rs.getString(5));
			dto.setPaymentDT(rs.getString(6));
			dto.setNotes(rs.getString(7));
			dto.setUpdateDate(rs.getString(8));
			dto.setUpdatedBy(rs.getInt(9));
			dto.setCreatedDate(rs.getString(10));
			dto.setCreatedBy(rs.getInt(11));
			dto.setDist(rs.getInt(12));
			dto.setAmount(rs.getInt(13));
			dtos.add(dto);
		}
		rs.close();
		pstmt.close();
		return dtos;
	}
	public List<PaymentTxnsDTO> fetchAllByDate(Connection con, Date date) throws SQLException {
		List<PaymentTxnsDTO> dtos = new ArrayList<PaymentTxnsDTO>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT paymentTxnsId, FromPayer, ToPayee, seeker_id, TripDetails, PaymentDT, Notes, UpdatedDT, UpdatedBy, CreatedDT, CreatedBy, Dist, amount from paymenttxns WHERE DATE(PaymentDT) =  ?");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.setString(1, ApplicationUtil.dateFormat1.format(date));
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while(rs.next()) {
			PaymentTxnsDTO dto = new PaymentTxnsDTO();
			dto.setPaymentTxnsId(rs.getInt(1));
			dto.setFromPayer(rs.getInt(2));
			dto.setToPayee(rs.getInt(3));
			dto.setSeekerId(rs.getInt(4));
			dto.setTripDetails(rs.getString(5));
			dto.setPaymentDT(rs.getString(6));
			dto.setNotes(rs.getString(7));
			dto.setUpdateDate(rs.getString(8));
			dto.setUpdatedBy(rs.getInt(9));
			dto.setCreatedDate(rs.getString(10));
			dto.setCreatedBy(rs.getInt(11));
			dto.setDist(rs.getInt(12));
			dto.setAmount(rs.getInt(13));
			dtos.add(dto);
		}
		rs.close();
		pstmt.close();
		return dtos;
	}

	public PaymentTxnsDTO paymentTxnCancel(Connection con, PaymentTxnsDTO dto) throws SQLException {
		if(Validator.isEmpty(dto.getUpdateDate())) {
			dto.setUpdateDate(ApplicationUtil.currentTimeStamp());
		}
		StringBuilder query = new StringBuilder();
		query.append("UPDATE paymenttxns SET FromPayer = ?, ToPayee = ?, UpdatedDT = ?, UpdatedBy = ? WHERE seeker_id = ?");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.setInt(1, dto.getFromPayer());
		pstmt.setInt(2, dto.getToPayee());
		pstmt.setString(3, dto.getUpdateDate());
		pstmt.setInt(4, dto.getUpdatedBy());
		pstmt.setFloat(5, dto.getSeekerId());
		pstmt.executeUpdate();
		pstmt.close();
		return dto;
	}
	public void paymentTxnHoponToUser(Connection con,
			HoponAccountDTO hoponAccountDto, UserRegistrationDTO userDto) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public void paymentTxnUserToHopon(Connection con,
			HoponAccountDTO hoponAccountDto, UserRegistrationDTO userDto) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
