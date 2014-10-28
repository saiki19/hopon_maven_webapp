package com.hopon.demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hopon.utils.ApplicationUtil;
import com.hopon.utils.ListOfValuesManager;

public class Demo {

	public static void getMessages(String email) {

		Connection con = ListOfValuesManager.getLocalConnection();
		
		String q1="Select id from users where email_id=?";

		String q = "Select message from messageboard where To_Member=? and CreatedbyDT=?";

		try {
			PreparedStatement pst = con.prepareStatement(q1);
			pst.setString(1, email);
			ResultSet rs=pst.executeQuery();
			int id=0;
			if(rs.next()){
				id=rs.getInt(1);
				System.out.println("id:"+id);
			}
			pst = con.prepareStatement(q);
			pst.setInt(1, id);
			
			//pst.setDate(2,);//(2,);
		//	pst.setDate(2, ApplicationUtil.currentTimeStamp().toString());
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				System.out.println("message:"+rs.getString(1).split(",")[1]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
