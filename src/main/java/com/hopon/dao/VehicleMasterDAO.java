package com.hopon.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.hopon.dto.UserRegistrationDTO;
import com.hopon.dto.VehicleMasterDTO;
import com.hopon.utils.ApplicationUtil;
import com.hopon.utils.QueryExecuter;
import com.hopon.utils.Validator;
import com.mysql.jdbc.Statement;

public class VehicleMasterDAO {
  
  public VehicleMasterDTO registerVechile(Connection con, VehicleMasterDTO vehicleMasterDTO) throws SQLException {
    
    StringBuilder query = new StringBuilder();
    query
        .append("INSERT INTO vehicles_master (id,user_id, image_id, make, model,color,registration_no,Capacity, created_dt) VALUES (?,?,?,?,?,?,?,?, '"
            + ApplicationUtil.currentTimeStamp() + "') ");
    
    PreparedStatement pstmt = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
    // pstmt.setString(1, vehicleMasterDTO.getVehicleID());
    pstmt.setString(1, null);
    pstmt.setString(2, vehicleMasterDTO.getUserID());
    pstmt.setString(3, vehicleMasterDTO.getImageID());
    pstmt.setString(4, vehicleMasterDTO.getMake());
    pstmt.setString(5, vehicleMasterDTO.getModel());
    pstmt.setString(6, vehicleMasterDTO.getColor());
    pstmt.setString(7, vehicleMasterDTO.getReg_NO());
    pstmt.setString(8, vehicleMasterDTO.getCapacity());
    pstmt.executeUpdate();
    
    ResultSet tableKeys = pstmt.getGeneratedKeys();
    tableKeys.next();
    int autoGeneratedID = tableKeys.getInt(1);
    vehicleMasterDTO.setVehicleID(String.valueOf(autoGeneratedID));
    tableKeys.close();
    pstmt.close();
    // rideManagementDTO.setRideID(pstmt.getGeneratedKeys().getString(1));
    
    return vehicleMasterDTO;
  }
  
  public List<VehicleMasterDTO> findAllVehicle(Connection con, String userID) throws SQLException {
    
    StringBuilder query = new StringBuilder();
    query
        .append(" SELECT  id,user_id,make,model,color,registration_no,Capacity,Avilable,is_default,driverid,drivername FROM vehicles_master where user_id = '"
            + userID + "' and status = 'A' ");
    
    List<VehicleMasterDTO> vehicleDTO = new ArrayList<VehicleMasterDTO>();
    PreparedStatement pstmt = con.prepareStatement(query.toString());
    ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
    while (rs.next()) {
      VehicleMasterDTO dto = new VehicleMasterDTO();
      dto.setVehicleID(rs.getString(1));
      dto.setUserID(rs.getString(2));
      dto.setMake(rs.getString(3));
      // dto.setDate_of_creation(Date.valueOf(rs.getString(4)));
      dto.setModel(rs.getString(4));
      dto.setColor(rs.getString(5));
      dto.setReg_NO(rs.getString(6));
      dto.setCapacity(rs.getString(7));
      dto.setAvilable(rs.getString(8));
      if (rs.getString(9).equals("0")) {
        dto.setIs_default(false);
      } else {
        dto.setIs_default(true);
      }
      if (rs.getString(10).equals("0")) {
        dto.setDriverid("");
      } else {
        dto.setDriverid(rs.getString(10));
      }
      if (rs.getString(11).equals("0")) {
        dto.setDrivername("NoDriver");
      } else {
        dto.setDrivername(rs.getString(11));
      }
      vehicleDTO.add(dto);
    }
    rs.close();
    pstmt.close();
    return vehicleDTO;
  }
  
  public VehicleMasterDTO updateDefaultStatus(Connection con, VehicleMasterDTO userdto) throws SQLException {
    StringBuilder query = new StringBuilder();
    query.append("UPDATE vehicles_master SET  is_default = '0', updated_dt = '" + ApplicationUtil.currentTimeStamp() + "' where user_id ='"
        + userdto.getUserID() + "' AND status = 'A'");
    PreparedStatement pstmt = con.prepareStatement(query.toString());
    pstmt.executeUpdate();
    pstmt.close();
    
    query = new StringBuilder();
    query.append("UPDATE vehicles_master SET  is_default = '1', updated_dt = '" + ApplicationUtil.currentTimeStamp() + "' where id ='"
        + userdto.getVehicleID() + "'");
    pstmt = con.prepareStatement(query.toString());
    pstmt.executeUpdate();
    pstmt.close();
    
    return userdto;
  }
  
  public VehicleMasterDTO updateSeat(Connection con, VehicleMasterDTO userdto) throws SQLException {
    
    StringBuilder query = new StringBuilder();
    query.append("UPDATE vehicles_master SET  Avilable = '" + userdto.getAvilable() + "', updated_dt = '" + ApplicationUtil.currentTimeStamp()
        + "' where id ='" + userdto.getVehicleID() + "'");
    PreparedStatement pstmt = con.prepareStatement(query.toString());
    pstmt.executeUpdate();
    pstmt.close();
    
    return userdto;
  }
  
  public VehicleMasterDTO updateVehicle(Connection con, VehicleMasterDTO userdto) throws SQLException {
   
    StringBuilder query = new StringBuilder();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    query.append("SELECT  rides_management.user_id , rides_management.start_time , vehicles_master.id, vehicles_master.Capacity FROM rides_management, vehicles_master where vehicles_master.registration_no = '" + userdto.getReg_NO() + "' and vehicles_master.status = 'A' and rides_management.vehicleID = vehicles_master.id and DATEDIFF(rides_management.start_time,'"+ ApplicationUtil.currentTimeStamp() +"') > 1");
   
    pstmt= con.prepareStatement(query.toString());
    rs = QueryExecuter.getResultSet(pstmt, query.toString());
    if (rs.next())
   	{
    	userdto.setVehicleID(rs.getString(3));
        userdto.setUserID(rs.getString(1));
        userdto.setCapacity(rs.getString(4));
    }
   
   else{
	   userdto.setCapacity(userdto.getCapacity());
    	rs.close();
    	pstmt.close();
    	PreparedStatement pstmt1 = null;
    	StringBuilder query1 = new StringBuilder();
    	
    	 if (userdto.getCapacity() != null) {
    		 query1.append("UPDATE vehicles_master SET  make = '" + userdto.getMake() + "',model = '" + userdto.getModel() + "',registration_no = '"
    	          + userdto.getReg_NO() + "',Capacity = '" + userdto.getCapacity() + "', updated_dt = '" + ApplicationUtil.currentTimeStamp()
    	          + "' where id ='" + userdto.getVehicleID() + "'");
    		}
    	 if (userdto.getAvilable().equalsIgnoreCase("link")) {
    		 query1.append("UPDATE vehicles_master SET  driverid = '" + userdto.getUserID() + "', drivername = '" + userdto.getDrivername()
    	          + "', updated_dt = '" + ApplicationUtil.currentTimeStamp() + "' where id ='" + userdto.getVehicleID() + "'");
    	    }
    	 if (userdto.getAvilable().equalsIgnoreCase("delink")) {
    		 query1.append("UPDATE vehicles_master SET  driverid = '0', drivername = '0', updated_dt = '" + ApplicationUtil.currentTimeStamp()
    	          + "' where id ='" + userdto.getVehicleID() + "'");
    	    }
    	 pstmt1 = con.prepareStatement(query1.toString());
    	 pstmt1.executeUpdate();
    	 pstmt1.close();
   		}
   return userdto;
  }
  
  public VehicleMasterDTO inActivateVehicle(Connection con, VehicleMasterDTO userdto) throws SQLException {
    
    StringBuilder query = new StringBuilder();
    query.append("UPDATE vehicles_master SET  status ='I', updated_dt = '" + ApplicationUtil.currentTimeStamp() + "' where id ='"
        + userdto.getVehicleID() + "'");
    PreparedStatement pstmt = con.prepareStatement(query.toString());
    pstmt.executeUpdate();
    pstmt.close();
    
    return userdto;
  }
  
  public boolean testVehicleRegistrationNumber(Connection con, String registrationNo) throws SQLException {
    StringBuilder query = new StringBuilder();
    query.append(" SELECT count(*) FROM vehicles_master where registration_no = '" + registrationNo + "' AND status = 'A'");
    
    PreparedStatement pstmt = con.prepareStatement(query.toString());
    ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
    Boolean test = false;
    while (rs.next()) {
      if (rs.getInt(1) > 0)
        test = true;
    }
    rs.close();
    pstmt.close();
    return test;
  }
  
  public VehicleMasterDTO getVehicleDtoById(Connection con, String vehicleID) throws SQLException {
    StringBuilder query = new StringBuilder();
    query.append(" SELECT  id, make, model, color, registration_no, Capacity FROM vehicles_master where id = '" + vehicleID + "' and status = 'A' ");
    
    List<VehicleMasterDTO> vehicleDTO = new ArrayList<VehicleMasterDTO>();
    PreparedStatement pstmt = con.prepareStatement(query.toString());
    VehicleMasterDTO dto = new VehicleMasterDTO();
    ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
    while (rs.next()) {
      dto.setVehicleID(rs.getString(1));
      dto.setMake(rs.getString(2));
      dto.setModel(rs.getString(3));
      dto.setColor(rs.getString(4));
      dto.setReg_NO(rs.getString(5));
      dto.setCapacity(rs.getString(6));
      vehicleDTO.add(dto);
    }
    rs.close();
    pstmt.close();
    return dto;
  }
  
  public UserRegistrationDTO findUserDtoByVehicleId(Connection con, int vehicleId) throws SQLException {
    UserRegistrationDTO dto = new UserRegistrationDTO();
    StringBuilder query = new StringBuilder();
    
    // In the case when driver is linked with a user.
    query
        .append("select u.id, u.email_id, u.mobile_no, u.gender, u.birthdate, u.address, u.first_name from vehicles_master v inner JOIN users u ON v.driverid = u.id where v.id = '"
            + vehicleId + "'");
    PreparedStatement pstmt = con.prepareStatement(query.toString());
    ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
    if (rs.next()) {
      dto.setId(rs.getString(1));
      dto.setEmail_id(rs.getString(2));
      dto.setMobile_no(rs.getString(3));
      dto.setGender(rs.getString(4));
      dto.setBirthdate(rs.getString(5));
      dto.setAddress(rs.getString(6));
      dto.setFirst_name(rs.getString(7));
    }
    rs.close();
    pstmt.close();
    
    // In the case when driver is not linked with any user.
    if (Validator.isEmpty(dto.getId())) {
      query = new StringBuilder();
      query
          .append("select u.id, u.email_id, u.mobile_no, u.gender, u.birthdate, u.address, u.first_name from vehicles_master v inner JOIN users u ON v.user_id = u.id where v.id = '"
              + vehicleId + "'");
      pstmt = con.prepareStatement(query.toString());
      rs = QueryExecuter.getResultSet(pstmt, query.toString());
      if (rs.next()) {
        dto.setId(rs.getString(1));
        dto.setEmail_id(rs.getString(2));
        dto.setMobile_no(rs.getString(3));
        dto.setGender(rs.getString(4));
        dto.setBirthdate(rs.getString(5));
        dto.setAddress(rs.getString(6));
        dto.setFirst_name(rs.getString(7));
      }
      rs.close();
      pstmt.close();
    }
    return dto;
  }
  
  public VehicleMasterDTO getVehicleDtoByRideId(Connection con, int rideID) throws SQLException {
    StringBuilder query = new StringBuilder();
    query
        .append(" SELECT  v.id, v.make, v.model, v.color, v.registration_no, v.Capacity FROM vehicles_master v, rides_management r where v.id = r.vehicleID AND r.ride_id = '"
            + rideID + "' and v.status = 'A' ");
    
    List<VehicleMasterDTO> vehicleDTO = new ArrayList<VehicleMasterDTO>();
    PreparedStatement pstmt = con.prepareStatement(query.toString());
    VehicleMasterDTO dto = new VehicleMasterDTO();
    ResultSet rs = QueryExecuter.getResultSet(pstmt, query.toString());
    while (rs.next()) {
      dto.setVehicleID(rs.getString(1));
      dto.setMake(rs.getString(2));
      dto.setModel(rs.getString(3));
      dto.setColor(rs.getString(4));
      dto.setReg_NO(rs.getString(5));
      dto.setCapacity(rs.getString(6));
      vehicleDTO.add(dto);
    }
    rs.close();
    pstmt.close();
    return dto;
  }
}
