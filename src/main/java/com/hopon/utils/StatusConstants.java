package com.hopon.utils;
/**
 * enum StatusConstants defines all database status values
 * 
 * @author Shreekantha Dk
 * 
 * */
public enum StatusConstants {

	P("P"), A("A"), I("I"), R("R"), C("C"), O("O"), T("T"), N("N"), Y("Y"), ACTIVE(
			"ACTIVE"), INACTIVE("INACTIVE"), S("S"), F("F"), D("D"), V("V"), ZERO(
			"0"), ONE("1");

	String s;

	private StatusConstants() {
	}

	private StatusConstants(String i) {
		this.s = i;
	}

	public String get() {
		return s;
	}

}
