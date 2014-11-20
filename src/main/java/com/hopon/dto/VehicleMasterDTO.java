package com.hopon.dto;

public class VehicleMasterDTO {
  
  private String avilable;
  private String capacity;
  private String color;
  private String createdDt;
  private String driverid;
  private String drivername;
  private String imageID;
  private boolean is_default;
  private String make;
  private String model;
  private String reg_NO;
  private String updatedDt;
  private String userID;
  private String vehicleID;
  private boolean select= false ;
  
  public boolean getSelect() {
	return select;
}

public void setSelect(boolean select) {
	this.select = select;
}

public String getAvilable() {
    return this.avilable;
  }
  
  public String getCapacity() {
    return this.capacity;
  }
  
  public String getColor() {
    return this.color;
  }
  
  public String getCreatedDt() {
    return this.createdDt;
  }
  
  public String getDriverid() {
    return this.driverid;
  }
  
  public String getDrivername() {
    return this.drivername;
  }
  
  public String getImageID() {
    return this.imageID;
  }
  
  public String getMake() {
    return this.make;
  }
  
  public String getModel() {
    return this.model;
  }
  
  public String getReg_NO() {
    return this.reg_NO;
  }
  
  public String getUpdatedDt() {
    return this.updatedDt;
  }
  
  public String getUserID() {
    return this.userID;
  }
  
  public String getVehicleID() {
    return this.vehicleID;
  }
  
  public boolean isIs_default() {
    return this.is_default;
  }
  
  public void setAvilable(final String avilable) {
    this.avilable = avilable;
  }
  
  public void setCapacity(final String capacity) {
    this.capacity = capacity;
  }
  
  public void setColor(final String color) {
    this.color = color;
  }
  
  public void setCreatedDt(final String createdDt) {
    this.createdDt = createdDt;
  }
  
  public void setDriverid(final String driverid) {
    this.driverid = driverid;
  }
  
  public void setDrivername(final String drivername) {
    this.drivername = drivername;
  }
  
  public void setImageID(final String imageID) {
    this.imageID = imageID;
  }
  
  public void setIs_default(final boolean is_default) {
    this.is_default = is_default;
  }
  
  public void setMake(final String make) {
    this.make = make;
  }
  
  public void setModel(final String model) {
    this.model = model;
  }
  
  public void setReg_NO(final String reg_NO) {
    this.reg_NO = reg_NO;
  }
  
  public void setUpdatedDt(final String updatedDt) {
    this.updatedDt = updatedDt;
  }
  
  public void setUserID(final String userID) {
    this.userID = userID;
  }
  
  public void setVehicleID(final String vehicleID) {
    this.vehicleID = vehicleID;
  }
  
}
