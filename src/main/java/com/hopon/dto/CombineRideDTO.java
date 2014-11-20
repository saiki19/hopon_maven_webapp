package com.hopon.dto;

public class CombineRideDTO {
  private int capacity;
  private boolean drop = false;
  private String endPoint;
  private String rideId;
  private String rideTime;
  private String startPoint;
  private boolean take = false;
  private int usedCapacity;
  private int userId;
  private String userName;
  private int vehicleID;
  private String vehicleRegNo;
  private boolean reassign = false;
 /* private boolean select= false ;
  
  public boolean getSelect() {
	return select;
}

public void setSelect(boolean select) {
	this.select = select;
}
*/
  
  public boolean isReassign() {
	return reassign;
}

public void setReassign(boolean reassign) {
	System.out.println("Inside CombineRiseDTO & value of reassign :"+reassign);
	this.reassign = reassign;
}

public int getCapacity() {
    return this.capacity;
  }
  
  public String getEndPoint() {
    return this.endPoint;
  }
  
  public String getRideId() {
    return this.rideId;
  }
  
  public String getRideTime() {
    return this.rideTime;
  }
  
  public String getStartPoint() {
    return this.startPoint;
  }
  
  public int getUsedCapacity() {
    return this.usedCapacity;
  }
  
  public int getUserId() {
    return this.userId;
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public int getVehicleID() {
    return this.vehicleID;
  }
  
  public String getVehicleRegNo() {
    return this.vehicleRegNo;
  }
  
  public boolean isDrop() {
    return this.drop;
  }
  
  public boolean isTake() {
    return this.take;
  }
  
  public void setCapacity(final int capacity) {
    this.capacity = capacity;
  }
  
  public void setDrop(final boolean drop) {
	  System.out.println("Inside CombineRiseDTO & value of drop :"+drop);
    this.drop = drop;
  }
  
  public void setEndPoint(final String endPoint) {
    this.endPoint = endPoint;
  }
  
  public void setRideId(final String rideId) {
    this.rideId = rideId;
  }
  
  public void setRideTime(final String rideTime) {
    this.rideTime = rideTime;
  }
  
  public void setStartPoint(final String startPoint) {
    this.startPoint = startPoint;
  }
  
  public void setTake(final boolean take) {
	this.take = take;
  }
  
  public void setUsedCapacity(final int usedCapacity) {
    this.usedCapacity = usedCapacity;
  }
  
  public void setUserId(final int userId) {
    this.userId = userId;
  }
  
  public void setUserName(final String userName) {
    this.userName = userName;
  }
  
  public void setVehicleID(final int vehicleID) {
    this.vehicleID = vehicleID;
  }
  
  public void setVehicleRegNo(final String vehicleRegNo) {
    this.vehicleRegNo = vehicleRegNo;
  }
}
