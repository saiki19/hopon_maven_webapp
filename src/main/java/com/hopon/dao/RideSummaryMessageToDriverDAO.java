package com.hopon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hopon.dto.SummaryMessageDTO;
import com.hopon.utils.ListOfValuesManager;
import com.hopon.utils.QueryExecuter;

/*
 * This is for<code>RideSummaryMessageToDriverClass</code>
 */
public class RideSummaryMessageToDriverDAO {

	public List<SummaryMessageDTO> rideSummaryMessage() {
		Connection con = ListOfValuesManager.getBroadConnection();
		List<SummaryMessageDTO> messageList = new ArrayList<SummaryMessageDTO>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT pq.rideseeker_id,rs.user_id,us.first_name,us.mobile_no,rs.start_point ,rs.destination_point,rs.start_time,vm1.driverid,vm1.drivername,rm1.ride_id,rm1.vehicleID FROM ride_seeker_details rs LEFT OUTER JOIN users us ON us.id = rs.user_id LEFT OUTER JOIN pool_requests pq ON rs.seeker_id IN(SELECT pq.rideseeker_id FROM rides_management rm,pool_requests pq WHERE rm.ride_id=pq.ride_id )LEFT OUTER JOIN rides_management rm1 ON rm1.ride_id=pq.ride_id LEFT OUTER JOIN vehicles_master vm1 ON vm1.id=rm1.vehicleID where CURRENT_TIMESTAMP < rs.start_time");
		PreparedStatement psmt;
		messageList.clear();
		try {
			psmt = con.prepareStatement(query.toString());
			ResultSet rs = QueryExecuter.getResultSet(psmt, query.toString());
			StringBuilder message = new StringBuilder();
			while (rs.next()) {
				SummaryMessageDTO dto = new SummaryMessageDTO();
				dto.setSeeker_id(rs.getString(1));
				dto.setUser_id(rs.getInt(2));
				dto.setFromAddress1(rs.getString(5));
				dto.setToAddress1(rs.getString(6));
				dto.setStart_time(rs.getString(7));
				dto.setFirst_name(rs.getString(3));
				dto.setMobile_no(rs.getLong(4));
				dto.setDriverid(rs.getString(8));
				dto.setDrivername(rs.getString(9));
				dto.setRideID(rs.getInt(10));
				dto.setVehicleID(rs.getInt(11));
				message.append("SeekerId:" + rs.getString(1) + "\nUserID:"
						+ rs.getInt(2) + "\nFrom Address:" + rs.getString(5)
						+ "\nDest Address:" + rs.getString(6) + "\nStart Time:"
						+ rs.getString(7) + "\nFirst Name:" + rs.getString(3)
						+ "\nMobile:" + rs.getLong(4) + "\ndriverid:"
						+ rs.getString(8) + "\nDrivername:" + rs.getString(9)
						+ "\nRideId:" + rs.getInt(10) + "\nVehicleId:"
						+ rs.getInt(11));
				messageList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}
}
