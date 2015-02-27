package com.hopon.dao;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hopon.dto.CircleDTO;
import com.hopon.dto.GuestRideDTO;
import com.hopon.dto.RideManagementDTO;
import com.hopon.dto.RideSeekerDTO;
import com.hopon.dto.SummaryMessageDTO;
import com.hopon.dto.UserRegistrationDTO;
import com.hopon.utils.ApplicationUtil;
import com.hopon.utils.ListOfValuesManager;
import com.hopon.utils.LoggerSingleton;
import com.hopon.utils.QueryExecuter;
import com.hopon.utils.Validator;
import com.mysql.jdbc.Statement;

public class RideSeekerDAO {
	public RideManagementDTO registerRideSeeker(Connection con,
			RideManagementDTO rideSeekerDTO) {
		StringBuilder query = new StringBuilder();
		if (rideSeekerDTO.getTripType() != 0) {

			query.append("INSERT INTO ride_seeker_details (seeker_id,user_id, start_point, via_point, destination_point,"
					+ "ride_cost,start_tw_early,status,vehicleID,MatchInCircle,FlexiTimeBefore,FlexiTimeAfter,FromCity,"
					+ "ToCity,FromPin,ToPin,created_by,created_dt, start_time, isSharedTaxi,custom, start_point_lat, "
					+ "start_point_long, via_point_lat, via_point_long, end_point_lat, end_point_long, ride_distance, "
					+ "start_tw_late, end_tw_early, end_tw_late, is_result, approverID, recurring, fullDay, circle_id,trip_type,daily_rides,group_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			
		} else {

			query.append("INSERT INTO ride_seeker_details (seeker_id,user_id, start_point, via_point, destination_point,"
					+ "ride_cost,start_tw_early,status,vehicleID,MatchInCircle,FlexiTimeBefore,FlexiTimeAfter,FromCity,"
					+ "ToCity,FromPin,ToPin,created_by,created_dt, start_time, isSharedTaxi,custom, start_point_lat, "
					+ "start_point_long, via_point_lat, via_point_long, end_point_lat, end_point_long, ride_distance, "
					+ "start_tw_late, end_tw_early, end_tw_late, is_result, approverID, recurring, fullDay, circle_id,trip_type,daily_rides) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		}
		
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(query.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, rideSeekerDTO.getRideID());
			pstmt.setString(2, rideSeekerDTO.getUserID());
			pstmt.setString(3, rideSeekerDTO.getFromAddress1());
			if (rideSeekerDTO.getViaPoint() == null
					|| rideSeekerDTO.getViaPoint().equals("")) {
				rideSeekerDTO.setViaPoint(rideSeekerDTO.getFromAddress1());
			}
			pstmt.setString(4, rideSeekerDTO.getViaPoint());
			pstmt.setString(5, rideSeekerDTO.getToAddress1());
			pstmt.setString(6, rideSeekerDTO.getRideCost());
			pstmt.setString(7, rideSeekerDTO.getStartdateValue());
			pstmt.setString(8, rideSeekerDTO.getEnddateValue());
			if (rideSeekerDTO.getStatus() != null
					&& rideSeekerDTO.getStatus() != "") {
				pstmt.setString(8, rideSeekerDTO.getStatus());
			} else {
				pstmt.setString(8, "A");
			}
			pstmt.setString(9, rideSeekerDTO.getVehicleID());

			if (rideSeekerDTO.isAutomatchInCircle() == true) {
				pstmt.setString(10, "1");
			} else {
				pstmt.setString(10, "0");
			}
			if (rideSeekerDTO.getFlexiTimeBefore() != null) {
				pstmt.setString(11, rideSeekerDTO.getFlexiTimeBefore()
						.toString());
			}
			if (rideSeekerDTO.getFlexiTimeAfter() != null) {
				pstmt.setString(12, rideSeekerDTO.getFlexiTimeAfter()
						.toString());
			}
			pstmt.setString(13, rideSeekerDTO.getFromAddressCity());
			pstmt.setString(14, rideSeekerDTO.getToAddressCity());
			pstmt.setString(15, rideSeekerDTO.getFromAddressPin());
			pstmt.setString(16, rideSeekerDTO.getToAddressPin());
			pstmt.setString(17, rideSeekerDTO.getCreatedBy());
			
			pstmt.setString(18,ApplicationUtil.currentTimeStamp());
			pstmt.setString(19, rideSeekerDTO.getStartdateValue());
			if (rideSeekerDTO.isSharedTaxi()) {
				pstmt.setString(20, "1");
			} else {
				pstmt.setString(20, "0");
			}
			pstmt.setString(21, rideSeekerDTO.getCustom());

			pstmt.setFloat(22, rideSeekerDTO.getStartPointLatitude());
			pstmt.setFloat(23, rideSeekerDTO.getStartPointLongitude());
			if (rideSeekerDTO.getViaPointLatitude() == 0) {
				rideSeekerDTO.setViaPointLatitude(rideSeekerDTO
						.getStartPointLatitude());
			}
			if (rideSeekerDTO.getViaPointLongitude() == 0) {
				rideSeekerDTO.setViaPointLongitude(rideSeekerDTO
						.getStartPointLongitude());
			}
			pstmt.setFloat(24, rideSeekerDTO.getViaPointLatitude());
			pstmt.setFloat(25, rideSeekerDTO.getViaPointLongitude());
			pstmt.setFloat(26, rideSeekerDTO.getEndPointLatitude());
			pstmt.setFloat(27, rideSeekerDTO.getEndPointLongitude());

			pstmt.setFloat(28, rideSeekerDTO.getRideDistance());
			pstmt.setString(29, rideSeekerDTO.getStartDateLate());
			pstmt.setString(30, rideSeekerDTO.getEndDateEarly());
			pstmt.setString(31, rideSeekerDTO.getEndDateLate());
			pstmt.setString(32, "N");
			pstmt.setInt(33, rideSeekerDTO.getApproverID());
			if (Validator.isEmpty(rideSeekerDTO.getRecurring()))
				rideSeekerDTO.setRecurring("N");
			pstmt.setString(34, rideSeekerDTO.getRecurring());
			if (Validator.isEmpty(rideSeekerDTO.getFullDay()))
				rideSeekerDTO.setFullDay("N");
			pstmt.setString(35, rideSeekerDTO.getFullDay());
			pstmt.setInt(36, rideSeekerDTO.getCircleId());
			pstmt.setInt(37, rideSeekerDTO.getTripType());
			if (rideSeekerDTO.getTripType() == 0) {
				pstmt.setString(38, "N");
			} else {
				pstmt.setString(38, "Y");
			}
			if (rideSeekerDTO.getTripType() != 0) {
				pstmt.setString(39, rideSeekerDTO.getGroupId());
			}
			int i = pstmt.executeUpdate();
			System.out.println("Excute update:" + i);
			// rideManagementDTO.setRideID(pstmt.getGeneratedKeys().getString(1));
			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			int autoGeneratedID = tableKeys.getInt(1);

			tableKeys.close();
			pstmt.close();

			rideSeekerDTO.setRideID(String.valueOf(autoGeneratedID));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// rideManagementDTO.setRideID(pstmt.getGeneratedKeys().getString(1));

		return rideSeekerDTO;
	}

	public RideManagementDTO registerDailyRideSeeker(Connection con,
			RideManagementDTO rideManagementDTO) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ride_seeker_details (user_id, start_point, via_point, destination_point,"
				+ "start_tw_early,FromCity,"
				+ "ToCity,FromPin,ToPin,created_by,created_dt, start_time,start_time2,start_point_lat, "
				+ "start_point_long, via_point_lat, via_point_long, end_point_lat, end_point_long, "
				+ "start_tw_late, end_tw_early, end_tw_late,trip_type,end_time,isSharedTaxi,recurring,daily_rides,circle_id,ride_cost,ride_distance) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		PreparedStatement pstmt;

		try {
			pstmt = con.prepareStatement(query.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, rideManagementDTO.getUserID());
			pstmt.setString(2, rideManagementDTO.getFromAddress1());
			if (rideManagementDTO.getViaPoint() == null
					|| rideManagementDTO.getViaPoint().equals("")) {
				rideManagementDTO.setViaPoint(rideManagementDTO.getToAddress1());
			}
			pstmt.setString(3, rideManagementDTO.getViaPoint());
			pstmt.setString(4, rideManagementDTO.getToAddress1());
			pstmt.setString(5, rideManagementDTO.getStartdateValue());
			pstmt.setString(6, rideManagementDTO.getToAddressCity());
			pstmt.setString(7, rideManagementDTO.getFromAddressCity());
			pstmt.setString(8, rideManagementDTO.getToAddressPin());
			pstmt.setString(9, rideManagementDTO.getFromAddressPin());
			pstmt.setString(10, rideManagementDTO.getCreatedBy());
			pstmt.setString(11, ApplicationUtil.currentTimeStamp());

			pstmt.setString(12, rideManagementDTO.getStartdateValue());
			pstmt.setString(13, rideManagementDTO.getStartdateValue1());

			pstmt.setFloat(14, rideManagementDTO.getEndPointLatitude());
			pstmt.setFloat(15, rideManagementDTO.getEndPointLongitude());
			if (rideManagementDTO.getViaPointLatitude() == 0) {
				rideManagementDTO.setViaPointLatitude(rideManagementDTO
						.getEndPointLatitude());
			}
			if (rideManagementDTO.getViaPointLongitude() == 0) {
				rideManagementDTO.setViaPointLongitude(rideManagementDTO
						.getEndPointLongitude());
			}
			pstmt.setFloat(16, rideManagementDTO.getViaPointLatitude());
			pstmt.setFloat(17, rideManagementDTO.getViaPointLongitude());
			pstmt.setFloat(18, rideManagementDTO.getStartPointLatitude());
			pstmt.setFloat(19, rideManagementDTO.getStartPointLongitude());

			pstmt.setString(20, rideManagementDTO.getStartDateLate());
			pstmt.setString(21, rideManagementDTO.getEndDateEarly());
			pstmt.setString(22, rideManagementDTO.getEndDateLate());
			pstmt.setString(25, "0");
			pstmt.setString(26, "Y");
			pstmt.setString(27, "Y");
			pstmt.setInt(28, rideManagementDTO.getCircleId());
			pstmt.setString(29, rideManagementDTO.getRideCost());
			pstmt.setFloat(30, rideManagementDTO.getRideDistance());

			if ((rideManagementDTO.getTripType() != 1)
					&& (rideManagementDTO.getTripType() != 2)) {

				rideManagementDTO.setTripType(0);

			}
			pstmt.setInt(23, rideManagementDTO.getTripType());
			pstmt.setString(24, rideManagementDTO.getEnddateValue());
			int i = pstmt.executeUpdate();
			System.out.println("executeupdate:" + i);

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			int autoGeneratedID = tableKeys.getInt(1);
			tableKeys.close();
			pstmt.close();

			rideManagementDTO.setRideID(String.valueOf(autoGeneratedID));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// rideManagementDTO.setRideID(pstmt.getGeneratedKeys().getString(1));

		return rideManagementDTO;
	}

	public RideManagementDTO viewDailyRideData(Connection conn, String userId) {

		StringBuilder query = new StringBuilder();
		RideManagementDTO dto = new RideManagementDTO();
		query.append("SELECT start_point,destination_point,start_time,start_time2,end_time,trip_type,user_id,"
				+ "via_point,start_point_lat,start_point_long,end_point_lat,end_point_long,via_point_lat,"
				+ "via_point_long,start_tw_early,start_tw_late,end_tw_early,end_tw_late,created_dt,"
				+ "created_by,FromCity,ToCity,FromPin,ToPin,isSharedTaxi,recurring,circle_id,ride_cost,ride_distance,seeker_id "
				+ "FROM ride_seeker_details WHERE user_id=? AND daily_rides='Y' AND status='A'");

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, userId);
			ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());

			if (rs.next()) {
				dto.setUserID(rs.getString(7));
				dto.setViaPoint(rs.getString(8));
				dto.setStartPointLatitude(rs.getFloat(9));
				dto.setStartPointLongitude(rs.getFloat(10));
				dto.setEndPointLatitude(rs.getFloat(11));
				dto.setEndPointLongitude(rs.getFloat(12));
				dto.setViaPointLatitude(rs.getFloat(13));
				dto.setViaPointLongitude(rs.getFloat(14));

				dto.setStartDateEarly(rs.getString(15));
				dto.setStartDateLate(rs.getString(16));
				dto.setEndDateEarly(rs.getString(17));
				dto.setEndDateLate(rs.getString(18));
				
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss.S");
				SimpleDateFormat formatter1 = new SimpleDateFormat(
						ApplicationUtil.datePattern3);

				
				String str = rs.getString(19);

				try {
					dto.setCreated_dt(formatter1.parse(str));
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				dto.setCreatedBy(rs.getString(20));

				dto.setFromAddressCity(rs.getString(21));
				dto.setToAddressCity(rs.getString(22));
				dto.setFromAddressPin(rs.getString(23));
				dto.setToAddressPin(rs.getString(24));
				dto.setSharedTaxi(rs.getBoolean(25));
				dto.setRecurring(rs.getString(26));
				dto.setCircleId(rs.getInt(27));
				dto.setRideCost(rs.getString(28));
				dto.setRideDistance(rs.getFloat(29));
				dto.setRideID(rs.getString(30));
				dto.setFromAddress1(rs.getString(1));

				dto.setToAddress1(rs.getString(2));

				dto.setTripType(rs.getInt(6));

			
				try {

					Date date = formatter.parse(rs.getString(3));

					dto.setStartdateValue(formatter1.format(date));

				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					if (rs.getString(4) != null) {
						Date date = formatter.parse(rs.getString(4));
						dto.setStartdateValue1(formatter1.format(date));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {

					Date date = formatter.parse(rs.getString(5));
					dto.setEnddateValue(formatter1.format(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			rs.close();
			pstmt.close();
			return dto;
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return dto;
	}

	public boolean updateDailyRideData(Connection con,
			RideManagementDTO rideManagementDTO) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ride_seeker_details SET start_point=?, via_point=?, destination_point=?,"
				+ "start_tw_early=?,FromCity=?,"
				+ "ToCity=?,FromPin=?,ToPin=?,created_by=?, start_time=?,start_time2=?,start_point_lat=?, "
				+ "start_point_long=?, via_point_lat=?, via_point_long=?, end_point_lat=?, end_point_long=?, "
				+ "start_tw_late=?, end_tw_early=?, end_tw_late=?,trip_type=?,end_time=?,isSharedTaxi=?,recurring=?,daily_rides=?,circle_id=?,ride_cost=?,ride_distance=? WHERE user_id=? AND daily_rides='Y' AND status='A'");

		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(query.toString(),
					Statement.RETURN_GENERATED_KEYS);

			// pstmt.setString(, rideSeekerDTO.getUserID());
			pstmt.setString(1, rideManagementDTO.getFromAddress1());
			if (rideManagementDTO.getViaPoint() == null
					|| rideManagementDTO.getViaPoint().equals("")) {
				rideManagementDTO.setViaPoint(rideManagementDTO.getToAddress1());
			}
			pstmt.setString(2, rideManagementDTO.getViaPoint());
			pstmt.setString(3, rideManagementDTO.getToAddress1());
			pstmt.setString(4, rideManagementDTO.getStartdateValue());
			pstmt.setString(5, rideManagementDTO.getToAddressCity());
			pstmt.setString(6, rideManagementDTO.getFromAddressCity());
			pstmt.setString(7, rideManagementDTO.getToAddressPin());
			pstmt.setString(8, rideManagementDTO.getFromAddressPin());
			pstmt.setString(9, rideManagementDTO.getCreatedBy());

			pstmt.setString(10, rideManagementDTO.getStartdateValue());
			pstmt.setString(11, rideManagementDTO.getStartdateValue1());

			pstmt.setFloat(12, rideManagementDTO.getEndPointLatitude());
			pstmt.setFloat(13, rideManagementDTO.getEndPointLongitude());

			if (rideManagementDTO.getViaPointLatitude() == 0) {
				rideManagementDTO.setViaPointLatitude(rideManagementDTO
						.getEndPointLatitude());
			}
			if (rideManagementDTO.getViaPointLongitude() == 0) {
				rideManagementDTO.setViaPointLongitude(rideManagementDTO
						.getEndPointLongitude());
			}
			pstmt.setFloat(14, rideManagementDTO.getViaPointLatitude());
			pstmt.setFloat(15, rideManagementDTO.getViaPointLongitude());

			pstmt.setFloat(16, rideManagementDTO.getStartPointLatitude());
			pstmt.setFloat(17, rideManagementDTO.getStartPointLongitude());

			pstmt.setString(18, rideManagementDTO.getStartDateLate());
			pstmt.setString(19, rideManagementDTO.getEndDateEarly());
			pstmt.setString(20, rideManagementDTO.getEndDateLate());
			pstmt.setString(23, "0");
			pstmt.setString(24, "Y");
			pstmt.setString(25, "Y");
			pstmt.setInt(26, rideManagementDTO.getCircleId());
			pstmt.setString(27, rideManagementDTO.getRideCost());
			pstmt.setFloat(28, rideManagementDTO.getRideDistance());
			pstmt.setString(29, rideManagementDTO.getUserID());

			if ((rideManagementDTO.getTripType() != 1) && (rideManagementDTO.getTripType() != 2)) {

				rideManagementDTO.setTripType(0);

			}
			pstmt.setInt(21, rideManagementDTO.getTripType());
			pstmt.setString(22, rideManagementDTO.getEnddateValue());
			int i = pstmt.executeUpdate();
			if (i != 0) {
				return true;
			}
			System.out.println("executeupdate:" + i);

			pstmt.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		// rideManagementDTO.setRideID(pstmt.getGeneratedKeys().getString(1));
		return false;

	}

	public boolean cancelDailyRideData11(Connection con,RideManagementDTO rideManagementDTO) throws SQLException {
		
		String query = "UPDATE ride_seeker_details set status=? WHERE user_id=? AND daily_rides='Y' AND status='A'";
	
		try {
			PreparedStatement pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, "I");
			pstmt.setString(2, rideManagementDTO.getUserID());
			int i = pstmt.executeUpdate();
			if (i != 0) {
				pstmt.close();
				System.out.println("executeupdate value:" + i);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean cancelDailyRideData(Connection con,
			RideManagementDTO rideSeekerDTO) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ride_seeker_details SET status=? WHERE user_id=? AND daily_rides='Y' AND status='A'");

		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(query.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, "I");
			pstmt.setString(2, rideSeekerDTO.getUserID());
			int i = pstmt.executeUpdate();
			if (i != 0) {
				pstmt.close();
				System.out.println("executeupdate:" + i);
			}
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public List<RideSeekerDTO> findAllRideSeeker(Connection con, String userID)
			throws SQLException {

		List<RideSeekerDTO> rideSeekerList = new ArrayList<RideSeekerDTO>();
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ride_seeker_details.seeker_id,  ride_seeker_details.start_point, "
				+ "ride_seeker_details.via_point,ride_seeker_details.destination_point,"
				+ "ride_seeker_details.ride_cost,ride_seeker_details.start_tw_early,"
				+ "ride_seeker_details.status, trip_frequency.Trip_Freq_P,trip_frequency.Days, "
				+ "ride_seeker_details.custom, ride_seeker_details.ride_match_rideid, "
				+ "ride_seeker_details.is_result, ride_seeker_details.recurring,trip_frequency.End_date, "
				+ "ride_seeker_details.fullDay,ride_seeker_details.daily_rides,ride_seeker_details.ride_distance,ride_seeker_details.start_time2,ride_seeker_details.trip_type FROM ride_seeker_details, trip_frequency where ride_seeker_details.user_id = '"
				+ userID
				+ "' and ride_seeker_details.seeker_id = trip_frequency.ride_seeker_id and ride_seeker_details.status IN('A', 'T', 'O') AND TIMESTAMPDIFF(SECOND,ride_seeker_details.start_tw_early,'"
				+ ApplicationUtil.currentTimeStamp() + "') < 0 ");
		
		// query.append(" SELECT  ride_seeker_details.seeker_id,  ride_seeker_details.start_point, ride_seeker_details.via_point,ride_seeker_details.destination_point,ride_seeker_details.ride_cost,ride_seeker_details.start_tw_early,ride_seeker_details.status, trip_frequency.Trip_Freq_P,trip_frequency.Days, ride_seeker_details.custom, ride_seeker_details.ride_match_rideid, ride_seeker_details.is_result FROM ride_seeker_details, trip_frequency where ride_seeker_details.user_id = '"+userID+"' and ride_seeker_details.seeker_id = trip_frequency.ride_seeker_id and ride_seeker_details.status != '0' AND TIMESTAMPDIFF(SECOND,ride_seeker_details.start_tw_early,'"
		// +ApplicationUtil.currentTimeStamp+"') < 0 ");
		// query.append(" SELECT  ride_seeker_details.seeker_id,  ride_seeker_details.start_point, ride_seeker_details.via_point,ride_seeker_details.destination_point,ride_seeker_details.ride_cost,ride_seeker_details.start_tw_early,ride_seeker_details.status, trip_frequency.Trip_Freq_P,trip_frequency.Days, vehicles_master.Avilable FROM ride_seeker_details,vehicles_master,trip_frequency where ride_seeker_details.user_id = '"+userID+"' and ride_seeker_details.vehicleID = vehicles_master.id and ride_seeker_details.seeker_id = trip_frequency.ride_seeker_id and ride_seeker_details.status != '0' and vehicles_master.status = 'A' ");

		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while (rs.next()) {

			RideSeekerDTO dto = new RideSeekerDTO();

			dto.setSeekerID(rs.getString(1));
			dto.setFromAddress1(rs.getString(2));
			dto.setToAddress1(rs.getString(4));
			dto.setRideCost(rs.getString(5));

			SimpleDateFormat formatter = new SimpleDateFormat(
					ApplicationUtil.datePattern3);
			SimpleDateFormat formatter1 = new SimpleDateFormat(
					ApplicationUtil.datePattern9);
			try {
				Date date = formatter.parse(rs.getString(6));

				dto.setStartdateValue(formatter1.format(date));

			} catch (ParseException e) {
				LoggerSingleton.getInstance().error(
						e.getStackTrace()[0].getClassName() + "->"
								+ e.getStackTrace()[0].getMethodName()
								+ "() : "
								+ e.getStackTrace()[0].getLineNumber() + " :: "
								+ "Date is : " + rs.getString(6) + "."
								+ e.getMessage());
			}
			dto.setStatus(rs.getString(7));
			dto.setFrequencyID(rs.getString(8));
			dto.setFrequencyinweek(rs.getString(9).substring(1,
					rs.getString(9).length() - 1));
			dto.setCustom(rs.getString(10));
			dto.setRideMatchRideId(rs.getString(11));
			dto.setIsResult(rs.getString(12));
			dto.setRecurring(rs.getString(13));
			if (rs.getString(14) != null) {
				try {
					Date date1 = formatter.parse(rs.getString(14));
					dto.setEndDate(date1);
					dto.setEnddateValue(formatter1.format(date1));
				} catch (ParseException e) {
					LoggerSingleton.getInstance().error(
							e.getStackTrace()[0].getClassName() + "->"
									+ e.getStackTrace()[0].getMethodName()
									+ "() : "
									+ e.getStackTrace()[0].getLineNumber()
									+ " :: " + "Date is : " + rs.getString(14)
									+ "." + e.getMessage());
				}
			}
			dto.setFullDay(rs.getString(15));
			dto.setDaily_rides(rs.getString(16));
			dto.setRideDistance(rs.getFloat(17));
			dto.setTripType(rs.getInt(19));
			dto.setGuest_id(rs.getInt(20));
			try {
				if (rs.getString(18) != null) {
					Date date = formatter.parse(rs.getString(18));
					dto.setStartdateValue1(formatter1.format(date));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			rideSeekerList.add(dto);
		}
		rs.close();
		pstmt.close();
		return rideSeekerList;
	}

	public RideSeekerDTO cancleRideSeeker(Connection con,
			RideSeekerDTO rideSeekerDTO) throws SQLException {// also use for
																// update
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ride_seeker_details SET  status = '"
				+ rideSeekerDTO.getStatus() + "', updated_dt = '"
				+ ApplicationUtil.currentTimeStamp() + "' where seeker_id ='"
				+ rideSeekerDTO.getSeekerID() + "'");

		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.executeUpdate();
		pstmt.close();

		return rideSeekerDTO;
	}

	public List<RideSeekerDTO> fetchCompletedRideSeekerList(Connection con)
			throws SQLException {
		List<RideSeekerDTO> rideSeekerList = new ArrayList<RideSeekerDTO>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT ride_seeker_details.seeker_id,  ride_seeker_details.start_point, ride_seeker_details.via_point, "
				+ "ride_seeker_details.destination_point,ride_seeker_details.ride_cost,ride_seeker_details.start_tw_early, ride_seeker_details.status, trip_frequency.Trip_Freq_P,trip_frequency.Days, ride_seeker_details.ride_match_rideid, ride_seeker_details.custom, ride_seeker_details.is_result, ride_seeker_details.recurring, ride_seeker_details.fullDay FROM ride_seeker_details, trip_frequency "
				+ "WHERE ride_seeker_details.seeker_id = trip_frequency.ride_seeker_id and ride_seeker_details.status = 'I' and "
				+ "DATEDIFF('"+ ApplicationUtil.currentTimeStamp()
				+ "', ride_seeker_details.start_tw_early) = 1");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while (rs.next()) {
			RideSeekerDTO dto = new RideSeekerDTO();
			dto.setSeekerID(rs.getString(1));
			dto.setFromAddress1(rs.getString(2));
			dto.setToAddress1(rs.getString(4));
			SimpleDateFormat formatter = new SimpleDateFormat(
					ApplicationUtil.datePattern3);
			SimpleDateFormat formatter1 = new SimpleDateFormat(
					ApplicationUtil.datePattern9);
			try {
				Date date = formatter.parse(rs.getString(6));
				dto.setStartdateValue(formatter1.format(date));
			} catch (ParseException e) {
				LoggerSingleton.getInstance().error(
						e.getStackTrace()[0].getClassName() + "->"
								+ e.getStackTrace()[0].getMethodName()
								+ "() : "
								+ e.getStackTrace()[0].getLineNumber() + " :: "
								+ "Date is : " + rs.getString(6) + "."
								+ e.getMessage());
			}
			dto.setStatus(rs.getString(7));
			dto.setFrequencyID(rs.getString(8));
			dto.setFrequencyinweek(rs.getString(9).substring(1,
					rs.getString(9).length() - 1));
			dto.setCustom(rs.getString(10));
			dto.setRideMatchRideId(rs.getString(11));
			dto.setIsResult(rs.getString(12));
			dto.setRecurring(rs.getString(13));
			dto.setFullDay(rs.getString(14));
			rideSeekerList.add(dto);
		}
		rs.close();
		pstmt.close();
		return rideSeekerList;
	}

	public RideSeekerDTO getRideSeekerData(Connection con, int seekerId)
			throws SQLException {
		RideSeekerDTO dto = new RideSeekerDTO();
		StringBuilder query = new StringBuilder();
		query.append("SELECT ride_seeker_details.seeker_id, ride_seeker_details.user_id, ride_seeker_details.start_point, "
				+ "ride_seeker_details.destination_point, ride_seeker_details.start_tw_early, ride_seeker_details.status, "
				+ "users.first_name, ride_seeker_details.ride_match_rideid, ride_seeker_details.is_result, "
				+ "ride_seeker_details.isSharedTaxi, ride_seeker_details.start_point_lat, ride_seeker_details.start_point_long, "
				+ "ride_seeker_details.end_point_lat, ride_seeker_details.end_point_long, ride_seeker_details.via_point_lat, "
				+ "ride_seeker_details.via_point_long, ride_seeker_details.created_by, ride_seeker_details.approverID, ride_seeker_details.recurring, ride_seeker_details.subSeekerId, ride_seeker_details.fullDay FROM ride_seeker_details left outer join users on ride_seeker_details.user_id = users.id where ride_seeker_details.seeker_id = '"
				+ seekerId + "'");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		if (rs.next()) {
			dto.setSeekerID(rs.getString(1));
			dto.setUserID(rs.getString(2));
			dto.setFromAddress1(rs.getString(3));
			dto.setToAddress1(rs.getString(4));
			SimpleDateFormat formatter = new SimpleDateFormat(
					ApplicationUtil.datePattern3);
			SimpleDateFormat formatter1 = new SimpleDateFormat(
					ApplicationUtil.datePattern9);
			try {
				Date date = formatter.parse(rs.getString(5));
				dto.setStartdateValue(formatter1.format(date));
			} catch (ParseException e) {
			}
			dto.setStatus(rs.getString(6));
			dto.setUserName(rs.getString(7));
			dto.setRideMatchRideId(rs.getString(8));
			dto.setIsResult(rs.getString(9));
			if (rs.getString(10).equals("1")) {
				dto.setSharedTaxi(true);
			} else {
				dto.setSharedTaxi(false);
			}

			dto.setStartPointLatitude(rs.getFloat(11));
			dto.setStartPointLongitude(rs.getFloat(12));
			dto.setEndPointLatitude(rs.getFloat(13));
			dto.setEndPointLongitude(rs.getFloat(14));
			dto.setViaPointLatitude(rs.getFloat(15));
			dto.setViaPointLongitude(rs.getFloat(16));
			dto.setCreatedBy(rs.getString(17));
			dto.setApproverID(rs.getInt(18));
			dto.setRecurring(rs.getString(19));
			dto.setSubSeekers(rs.getString(20));
			dto.setFullDay(rs.getString(21));
		}
		rs.close();
		pstmt.close();
		return dto;
	}

	public boolean checkRideSeekerDuplicacy(Connection con,
			RideManagementDTO rideSeekerDTO) throws SQLException {
		StringBuilder query = new StringBuilder();

		if (rideSeekerDTO.getVehicleID() != null) {
			query.append("SELECT count(*) from ride_seeker_details WHERE user_id = ? AND start_point = ? AND destination_point = ? AND "
					+ " TIMESTAMPDIFF(HOUR, start_time, ?) BETWEEN -1 AND 1 AND status IN('A', 'O', 'T') AND FromCity = ? AND ToCity = ? AND vehicleID = ?");
		} else {
			query.append("SELECT count(*) from ride_seeker_details WHERE user_id = ? AND start_point = ? AND destination_point = ? AND "
					+ " TIMESTAMPDIFF(HOUR, start_time, ?) BETWEEN -1 AND 1 AND status IN('A', 'O', 'T') AND FromCity = ? AND ToCity = ?");
		}
		// AND FromCity = ? AND ToCity = ? AND FromPin = ? AND ToPin = ?

		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.setString(1, rideSeekerDTO.getUserID());
		pstmt.setString(2, rideSeekerDTO.getFromAddress1());
		pstmt.setString(3, rideSeekerDTO.getToAddress1());
		pstmt.setString(4, rideSeekerDTO.getStartdateValue());

		pstmt.setString(5, rideSeekerDTO.getFromAddressCity());
		pstmt.setString(6, rideSeekerDTO.getToAddressCity());
		/*
		 * pstmt.setString(7, rideSeekerDTO.getFromAddressPin());
		 * pstmt.setString(8, rideSeekerDTO.getToAddressPin());
		 */

		if (rideSeekerDTO.getVehicleID() != null) {
			pstmt.setString(7, rideSeekerDTO.getVehicleID());
		}
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		boolean test = false;
		if (rs.next()) {
			if (rs.getInt(1) > 0) {
				test = true;
			}
		}
		rs.close();
		pstmt.close();
		return test;
	}

	public void makeRideSeekerCancelForUser(Connection con,
			UserRegistrationDTO userRegistrationDto) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ride_seeker_details SET status = 'I', updated_dt = '"
				+ ApplicationUtil.currentTimeStamp()
				+ "' WHERE user_id = "
				+ userRegistrationDto.getId());

		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.executeUpdate();
		pstmt.close();
	}

	public int calculateSingleRide(Connection con, List<Integer> seekerId)
			throws SQLException {
		StringBuilder query = new StringBuilder();
		String subQuery = "";
		for (Integer i : seekerId) {
			subQuery += i + ",";
		}
		subQuery = subQuery.substring(0, subQuery.length() - 1);
		query.append("SELECT seeker_id, max(ride_distance) as maxDistance from ride_seeker_details WHERE seeker_id IN ("
				+ subQuery
				+ ") group by seeker_id order by maxDistance desc LIMIT 1");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		if (rs.next()) {
			return rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		return 0;
	}

	public void changeStatus(Connection con, int seekerId, String status)
			throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ride_seeker_details set status = '" + status
				+ "' where seeker_id = '" + seekerId + "'");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.executeUpdate();
		pstmt.close();
	}

	public List<RideSeekerDTO> findRideSeekerDataByIds(Connection con,
			List<Integer> seekerIds) throws SQLException {
		List<RideSeekerDTO> dtos = new ArrayList<RideSeekerDTO>();
		String subquery = "";
		for (Integer i : seekerIds) {
			subquery += i + ", ";
		}
		StringBuilder query = new StringBuilder();
		query.append("select seeker_id, status, is_result, start_tw_early, c.fromNight, c.toNight, r.recurring from ride_seeker_details r LEFT OUTER JOIN approver a ON r.approverID = a.id LEFT OUTER JOIN company c ON c.userID = a.createdBy where r.seeker_id IN ("
				+ subquery + " 0) ");// FOR UPDATE
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while (rs.next()) {
			RideSeekerDTO dto = new RideSeekerDTO();
			dto.setSeekerID(rs.getString(1));
			dto.setStatus(rs.getString(2));
			dto.setIsResult(rs.getString(3));
			try {
				Date date = ApplicationUtil.dateFormat3.parse(rs.getString(4));
				dto.setStartdateValue(ApplicationUtil.dateFormat9.format(date));
				dto.setStartDate(date);
				dto.setNightRide(false);
				String a = rs.getString(5);
				String b = rs.getString(6);
				String c = ApplicationUtil.dateFormat17.format(date);
				boolean d = ApplicationUtil.compareDates(rs.getString(5),
						rs.getString(6),
						ApplicationUtil.dateFormat17.format(date));
				if (rs.getString(5) != null
						&& rs.getString(6) != null
						&& ApplicationUtil.compareDates(rs.getString(5),
								rs.getString(6),
								ApplicationUtil.dateFormat17.format(date))) {
					dto.setNightRide(true);
				}
				dto.setRecurring(rs.getString(7));
			} catch (ParseException e) {
			}
			dtos.add(dto);
		}
		rs.close();
		pstmt.close();
		return dtos;
	}

	public RideSeekerDTO changeField(Connection con, RideSeekerDTO rideSeekerDTO)
			throws SQLException {
		StringBuilder query = new StringBuilder();
		String subQuery = "";
		if (!Validator.isEmpty(rideSeekerDTO.getIsResult())) {
			subQuery += " is_result = '" + rideSeekerDTO.getIsResult() + "', ";
		}
		if (!Validator.isEmpty(rideSeekerDTO.getRideMatchRideId())) {
			subQuery += " ride_match_rideid = '"
					+ rideSeekerDTO.getRideMatchRideId() + "', ";
		}
		query.append("UPDATE ride_seeker_details SET " + subQuery
				+ "  updated_dt = '" + ApplicationUtil.currentTimeStamp()
				+ "' where seeker_id ='" + rideSeekerDTO.getSeekerID() + "'");

		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.executeUpdate();
		pstmt.close();

		return rideSeekerDTO;
	}

	public List<RideSeekerDTO> fetchRideSeekerUnApproved(Connection con)
			throws SQLException {
		List<RideSeekerDTO> dtos = new ArrayList<RideSeekerDTO>();
		StringBuilder query = new StringBuilder();
		query.append("select r.seeker_id, r.user_id, r.start_point, r.destination_point, r.start_tw_early, r.status, u.first_name, r.ride_match_rideid, r.is_result, r.isSharedTaxi, r.start_point_lat, r.start_point_long, r.end_point_lat, r.end_point_long, r.via_point_lat, r.via_point_long, r.created_by, r.approverID from ride_seeker_details r left outer join users u on r.user_id = u.id where r.status IN ('O', 'T') AND u.status = 'A' AND TIMESTAMPDIFF(HOUR, '"
				+ ApplicationUtil.currentTimeStamp()
				+ "', r.start_tw_early) >= 10");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while (rs.next()) {
			RideSeekerDTO dto = new RideSeekerDTO();

			dto.setSeekerID(rs.getString(1));
			dto.setUserID(rs.getString(2));
			dto.setFromAddress1(rs.getString(3));
			dto.setToAddress1(rs.getString(4));
			try {
				Date date = ApplicationUtil.dateFormat3.parse(rs.getString(5));
				dto.setStartdateValue(ApplicationUtil.dateFormat9.format(date));
			} catch (ParseException e) {
			}
			dto.setStatus(rs.getString(6));
			dto.setUserName(rs.getString(7));
			dto.setRideMatchRideId(rs.getString(8));
			dto.setIsResult(rs.getString(9));
			if (rs.getString(10).equals("1")) {
				dto.setSharedTaxi(true);
			} else {
				dto.setSharedTaxi(false);
			}

			dto.setStartPointLatitude(rs.getFloat(11));
			dto.setStartPointLongitude(rs.getFloat(12));
			dto.setEndPointLatitude(rs.getFloat(13));
			dto.setEndPointLongitude(rs.getFloat(14));
			dto.setViaPointLatitude(rs.getFloat(15));
			dto.setViaPointLongitude(rs.getFloat(16));
			dto.setCreatedBy(rs.getString(17));
			dto.setApproverID(rs.getInt(18));
			dtos.add(dto);
		}
		rs.close();
		pstmt.close();
		return dtos;
	}

	public List<RideSeekerDTO> fetchRecurringRideList(Connection con)
			throws SQLException {
		List<RideSeekerDTO> dtos = new ArrayList<RideSeekerDTO>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT rs.seeker_id, rs.user_id, rs.start_point, rs.destination_point, rs.start_tw_early, rs.status, rs.ride_match_rideid, rs.is_result, rs.isSharedTaxi, rs.start_point_lat, rs.start_point_long, rs.end_point_lat, rs.end_point_long, rs.via_point_lat, rs.via_point_long, rs.created_by, rs.approverID, rs.recurring, rs.via_point, tp.Trip_Freq_P,tp.Days, tp.End_date, rs.subSeekerId, rs.FromCity, rs.ToCity, rs.FromPin, rs.ToPin, rs.trip_type, rs.start_time2, rs.circle_id,tp.count, rs.daily_rides, rs.group_id FROM ride_seeker_details rs, trip_frequency tp WHERE rs.seeker_id = tp.ride_seeker_id and rs.status IN('A', 'T', 'O') and tp.status IN('A') and rs.recurring = 'Y' AND DATE_ADD(NOW(), INTERVAL +2 DAY) <= DATE(tp.End_date) AND DATE_ADD(NOW(), INTERVAL +2 DAY) >= DATE(tp.Start_date)");
		// Here add more condition for 2 month limit.
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		while (rs.next()) {
			RideSeekerDTO dto = new RideSeekerDTO();
			dto.setSeekerID(rs.getString(1));
			dto.setUserID(rs.getString(2));
			dto.setFromAddress1(rs.getString(3));
			dto.setToAddress1(rs.getString(4));
			SimpleDateFormat formatter = new SimpleDateFormat(
					ApplicationUtil.datePattern3);
			SimpleDateFormat formatter1 = new SimpleDateFormat(
					ApplicationUtil.datePattern9);
			try {
				Date date = formatter.parse(rs.getString(5));
				dto.setStartDate(date);
				dto.setStartdateValue(formatter1.format(date));
			} catch (ParseException e) {
			}
			if (rs.getInt(28) == 2) {
				Date date1;
				try {
					date1 = formatter.parse(rs.getString(29));
					dto.setStartDate1(date1);
					dto.setStartdateValue1(formatter1.format(date1));
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			}
			dto.setStatus(rs.getString(6));
			dto.setRideMatchRideId(rs.getString(7));
			dto.setIsResult(rs.getString(8));
			if (rs.getString(9).equals("1")) {
				dto.setSharedTaxi(true);
			} else {
				dto.setSharedTaxi(false);
			}

			dto.setStartPointLatitude(rs.getFloat(10));
			dto.setStartPointLongitude(rs.getFloat(11));
			dto.setEndPointLatitude(rs.getFloat(12));
			dto.setEndPointLongitude(rs.getFloat(13));
			dto.setViaPointLatitude(rs.getFloat(14));
			dto.setViaPointLongitude(rs.getFloat(15));
			dto.setCreatedBy(rs.getString(16));
			dto.setApproverID(rs.getInt(17));
			dto.setRecurring(rs.getString(18));
			dto.setViaPoint(rs.getString(19));
			dto.setFrequencyID(rs.getString(20));
			dto.setFrequencyinweek(rs.getString(21));
			dto.setSubSeekers(rs.getString(22));
			try {
				dto.setEndDate(ApplicationUtil.dateFormat3.parse(rs
						.getString(22)));
			} catch (ParseException e) {
			}
			dto.setSubSeekers(rs.getString(23));
			dto.setFromAddressCity(rs.getString(24));
			dto.setToAddressCity(rs.getString(25));
			dto.setFromAddressPin(rs.getString(26));
			dto.setToAddressPin(rs.getString(27));
			dto.setTripType(rs.getInt(28));
			dto.setCircleId(rs.getInt(30));
			dto.setCount(rs.getInt(31));
			dto.setDaily_rides(rs.getString(32));
			dto.setGroupId(rs.getString(33));
			dtos.add(dto);
		}
		rs.close();
		pstmt.close();
		return dtos;

	}

	public void addSubSeekers(Connection con, RideSeekerDTO ride)
			throws SQLException {
		StringBuilder query = new StringBuilder();

		query.append("UPDATE ride_seeker_details SET subSeekerId = '"+ ride.getSubSeekers() + "', updated_dt = '"
				+ ApplicationUtil.currentTimeStamp() + "' where seeker_id ='"
				+ ride.getSeekerID() + "'");

		PreparedStatement pstmt = con.prepareStatement(query.toString());
		pstmt.executeUpdate();
		pstmt.close();
	}

	public RideSeekerDTO cancelSubSeekers(Connection con,
			RideSeekerDTO rideSeekerDTO) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("select r.seeker_id from ride_seeker_details r where r.`status` = 'A' AND r.start_time > CURRENT_TIME() AND r.seeker_id IN("
				+ rideSeekerDTO.getSubSeekers() + ")");
		PreparedStatement pstmt = con.prepareStatement(query.toString());
		ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
		String ride = "";
		while (rs.next()) {
			ride += rs.getInt(1) + ",";
		}
		if (ride.length() > 0)
			ride = ride.substring(0, ride.length() - 1);
		rs.close();
		pstmt.close();
		if (ride.length() > 0) {
			query = new StringBuilder();
			query.append("UPDATE ride_seeker_details r SET r.status = '"
					+ rideSeekerDTO.getStatus() + "', r.updated_dt = '"
					+ ApplicationUtil.currentTimeStamp()
					+ "' where r.seeker_id IN(" + ride + ")");
			pstmt = con.prepareStatement(query.toString());
			pstmt.executeUpdate();
			pstmt.close();
		}
		rideSeekerDTO.setSubSeekers(ride);
		return rideSeekerDTO;
	}

	public List<RideManagementDTO> DailyRidePaymentHelper(Connection con) {
		StringBuilder query = new StringBuilder();
		List<RideManagementDTO> rideList = new ArrayList<RideManagementDTO>();

		query.append("SELECT rs.ride_cost,u.totalCredit,rs.user_id,rs.seeker_id,"
				+ "rs.ride_distance,rs.circle_id,tp.status "
				+ "FROM ride_seeker_details rs,trip_frequency tp INNER JOIN users u "
				+ "WHERE rs.user_id=u.id AND tp.ride_seeker_id=rs.seeker_id AND rs.status='A' "
				+ "AND rs.daily_rides='Y' AND tp.status ='I'");
		try {
			PreparedStatement psmt = con.prepareStatement(query.toString());
			ResultSet rs = QueryExecuter.getResultSet(psmt, query.toString());
			while (rs.next()) {
				RideManagementDTO dto = new RideManagementDTO();
				dto.setRideCost(rs.getString(1));
				dto.setTotalCredit(rs.getFloat(2));
				dto.setUserID(rs.getString(3));
				dto.setSeekerID(rs.getString(4));
				dto.setRideDistance(rs.getFloat(5));
				dto.setCircleId(rs.getInt(6));
				dto.setStatus(rs.getString(7));
				rideList.add(dto);
			}
			rs.close();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rideList;
	}

	public List<RideManagementDTO> fetchingHolidaynxtweek(Connection con, RideManagementDTO dto) {
		List<RideManagementDTO> rideManagementDTOList = new ArrayList<RideManagementDTO>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT h.holiday_date FROM holiday_list h WHERE circle_id= '"+dto.getCircleId()+ "' AND h.holiday_date >= ADDDATE(curdate(), INTERVAL 9-DAYOFWEEK(curdate()) DAY) AND h.holiday_date<=ADDDATE(curdate(), INTERVAL 13-DAYOFWEEK(curdate()) DAY)");
	
		try {
			PreparedStatement psmt = con.prepareStatement(query.toString());
			ResultSet rs = QueryExecuter.getResultSet(psmt, query.toString());
			while (rs.next()) {
				dto.setHoliday_date(rs.getDate(1));
				rideManagementDTOList.add(dto);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rideManagementDTOList;
	}

	public List<RideManagementDTO> fetchingHolidayList(RideManagementDTO dto) {

		List<RideManagementDTO> rideManagementDTOList = new ArrayList<RideManagementDTO>();
		Connection con = ListOfValuesManager.getBroadConnection();
		StringBuilder query = new StringBuilder();
		query.append("SELECT holiday_date FROM holiday_list WHERE circle_id= '"+dto.getCircleId()+ "'");

		try {
			PreparedStatement psmt = con.prepareStatement(query.toString());
			ResultSet rs = QueryExecuter.getResultSet(psmt, query.toString());
			while (rs.next()) {
				dto.setHoliday_date(rs.getDate(1));

				rideManagementDTOList.add(dto);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rideManagementDTOList;
	}

	public RideManagementDTO getRideIDByUserID(int user_id, Connection con) {
		StringBuilder query = new StringBuilder();
		RideManagementDTO dto = new RideManagementDTO();
		query.append("SELECT seeker_id FROM ride_seeker_details WHERE user_id='"
				+ user_id
				+ "' AND status='A' AND daily_rides='Y' AND recurring='Y'");
		try {
			PreparedStatement pstmt = con.prepareStatement(query.toString());
			ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
			while (rs.next()) {
				dto.setSeekerID(rs.getString(1));	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public boolean upadteGuestInfo(Connection con,String guest_id,String seeker_id){
		StringBuilder query=new StringBuilder();
		query.append("UPDATE ride_seeker_details SET guest_id=? WHERE seeker_id=?");
		try {
			PreparedStatement pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1,guest_id);
			pstmt.setString(2, seeker_id);
			int i=pstmt.executeUpdate();
			System.out.println("Excute update:"+i);
			pstmt.close();
			if(i==1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}


	public RideSeekerDTO showGuestRidePopup(Connection con,String seeker_id){
		StringBuilder query=new StringBuilder();
		RideSeekerDTO dto=new RideSeekerDTO();
		query.append("SELECT first_name,last_name,mobile_no,email_id,id from guests WHERE seeker_id='"+seeker_id+"'");
		try {
			PreparedStatement pstmt=con.prepareStatement(query.toString());
			ResultSet rs=QueryExecuter.getResultSet(pstmt, query.toString());
			while(rs.next()){
				dto.setGuest_fname(rs.getString(1));
				dto.setGuest_lname(rs.getString(2));
				dto.setGuest_mobile_no(rs.getString(3));
				dto.setGuest_email_id(rs.getString(4));
				dto.setGuest_id(rs.getInt(5));
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;	
	}
}
