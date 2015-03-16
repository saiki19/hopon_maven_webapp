package com.hopon.dto;

public class GuestRideDTO {
	private String guest_fname;
	private String guest_lname;
	private String guest_mobile_no;
	private String guest_email_id;
	private String guest_id;
	private String seekerID;
	private String created_by;
	
	

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getSeekerID() {
		return seekerID;
	}

	public void setSeekerID(String seekerID) {
		this.seekerID = seekerID;
	}

	public String getGuest_fname() {
		return guest_fname;
	}

	public void setGuest_fname(String guest_fname) {
		this.guest_fname = guest_fname;
	}

	public String getGuest_lname() {
		return guest_lname;
	}

	public void setGuest_lname(String guest_lname) {
		this.guest_lname = guest_lname;
	}

	public String getGuest_mobile_no() {
		return guest_mobile_no;
	}

	public void setGuest_mobile_no(String guest_mobile_no) {
		this.guest_mobile_no = guest_mobile_no;
	}

	public String getGuest_email_id() {
		return guest_email_id;
	}

	public void setGuest_email_id(String guest_email_id) {
		this.guest_email_id = guest_email_id;
	}

	public String getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}
	

}
