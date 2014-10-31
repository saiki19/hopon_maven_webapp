package com.hopon.dto;

import java.util.List;

public class SummaryMessageDTO {
	private String fromAddress1;
	private String toAddress1;
	// user table
	private String first_name;
	private long mobile_no;
	// poolrequest
	private String rideSeekerID;
	// ridemanage table
	private int rideID;
	private int vehicleID;
	// rideseeker table
	private String seeker_id;
	private String driverid;
	private String drivername;

	private String start_time;

	public int getToMember() {
		return toMember;
	}

	public void setToMember(int toMember) {
		this.toMember = toMember;
	}

	private int toMember;

	private int user_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFromAddress1() {
		return fromAddress1;
	}

	public void setFromAddress1(String fromAddress1) {
		this.fromAddress1 = fromAddress1;
	}

	public String getToAddress1() {
		return toAddress1;
	}

	public void setToAddress1(String toAddress1) {
		this.toAddress1 = toAddress1;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public long getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(long mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getRideSeekerID() {
		return rideSeekerID;
	}

	public void setRideSeekerID(String rideSeekerID) {
		this.rideSeekerID = rideSeekerID;
	}

	public int getRideID() {
		return rideID;
	}

	public void setRideID(int rideID) {
		this.rideID = rideID;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getSeeker_id() {
		return seeker_id;
	}

	public void setSeeker_id(String seeker_id) {
		this.seeker_id = seeker_id;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

}
