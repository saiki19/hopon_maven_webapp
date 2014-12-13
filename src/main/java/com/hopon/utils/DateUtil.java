/**
 * 
 */
package com.hopon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Enum <code>DateUtil</code> does the conversion of date from yyyy-MM-dd to
 * EEE, dd M yyyy HH:mm:ss<br>
 * <br>
 * 
 * @see <code>toAppDate(String)</code>
 * @see <code>toDBDate(String)</code>
 * 
 * @author Shreekantha DK
 *
 */

public enum DateUtil {

	/**
	 * enum DateUtil object, through this we can access the methods that are
	 * defined in this enum.
	 * 
	 */
	DATEUTIL;

	// standard date format
	private static final String DBDATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	// Friendly date format
	private static final String APPDATEFORMAT = "EEE, dd MMM yyyy, HH:mm a";

	private String date;

	// SimpleDateFormatter for standard date format
	private SimpleDateFormat dbDate = new SimpleDateFormat(DBDATEFORMAT);

	// SimpleDateFormatter for Friendly date format
	private SimpleDateFormat appDate = new SimpleDateFormat(APPDATEFORMAT);

	/**
	 * Method <code>toAppDate(String)</code> converts the date from yyyy-MM-dd
	 * HH:mm:ss to EEE, dd MMM yyyy HH:mm:ss<br>
	 * Example :<br>
	 * date from 2014-NOV-11 11:11 to Tue, 11 Nov 2014 11:11
	 * 
	 * @param value
	 * @return date
	 * @exception {@link ParseException}
	 */
	public String toAppDate(String value) {

		if (value != null && value.length() != 0) {

			try {

				date = appDate.format(dbDate.parse(value));

			} catch (ParseException e) {

				LoggerSingleton.getInstance().error(
						e.getStackTrace()[0].getClassName() + "->"
								+ e.getStackTrace()[0].getMethodName()
								+ "() : "
								+ e.getStackTrace()[0].getLineNumber() + " :: "
								+ "Date is : " + value + "." + e.getMessage());
			}
		}
		return date;

	}

	/**
	 * Method <code>toDBDate(String)</code> converts the date from EEE, dd MMM
	 * yyyy HH:mm:ss to yyyy-MM-dd HH:mm:ss<br>
	 * Example :<br>
	 * date from 2014-11-11 11:11:11 to Tue, 11 Nov 2014 11:11 am
	 * 
	 * @param value
	 * @return date
	 * @exception {@link ParseException}
	 */
	public String toDBDate(String value) {
		if (value != null && value.length() != 0) {
			try {
				date = dbDate.format(appDate.parse(value));
			} catch (ParseException e) {
				e.printStackTrace();
				LoggerSingleton.getInstance().error(
						e.getStackTrace()[0].getClassName() + "->"
								+ e.getStackTrace()[0].getMethodName()
								+ "() : "
								+ e.getStackTrace()[0].getLineNumber() + " :: "
								+ "Date is : " + value + "." + e.getMessage());
			}
		}
		return date;
	}
}
