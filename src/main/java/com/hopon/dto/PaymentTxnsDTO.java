package com.hopon.dto;

public class PaymentTxnsDTO {
	private int payment_txnsid;
	private float amount;
	private int from_payer;
	private int to_payee;
	private int seeker_id;
	private String trip_details;
	private String payment_dt;
	private String notes;
	private String updated_dt;
	private int updated_by;
	private String created_dt;
	private int created_by;
	private float distance;
	private String travel;
	
	
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	public int getPayment_txnsid() {
		return payment_txnsid;
	}
	public void setPayment_txnsid(int payment_txnsid) {
		this.payment_txnsid = payment_txnsid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getFrom_payer() {
		return from_payer;
	}
	public void setFrom_payer(int from_payer) {
		this.from_payer = from_payer;
	}
	public int getTo_payee() {
		return to_payee;
	}
	public void setTo_payee(int to_payee) {
		this.to_payee = to_payee;
	}
	public int getSeeker_id() {
		return seeker_id;
	}
	public void setSeeker_id(int seeker_id) {
		this.seeker_id = seeker_id;
	}
	public String getTrip_details() {
		return trip_details;
	}
	public void setTrip_details(String trip_details) {
		this.trip_details = trip_details;
	}
	public String getPayment_dt() {
		return payment_dt;
	}
	public void setPayment_dt(String payment_dt) {
		this.payment_dt = payment_dt;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getUpdated_dt() {
		return updated_dt;
	}
	public void setUpdated_dt(String updated_dt) {
		this.updated_dt = updated_dt;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public String getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(String created_dt) {
		this.created_dt = created_dt;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
}