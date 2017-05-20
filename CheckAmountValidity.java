package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.JdbcConnection;

public class CheckAmountValidity {
	public boolean checkValidity(int totalBalance, int userId) {
		Connection con = null;
		con = JdbcConnection.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con
					.prepareStatement("select plan from credit_card c,user_table u where u.ccnumber=c.ccnumber and u.user_id=?");
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			
			String plan;
			if (rs.next()) {
				plan = rs.getString(1);
			

			if (plan.equalsIgnoreCase("platinum")) {
				if (totalBalance <= 75000) {
					return true;
				}
			} else if (plan.equalsIgnoreCase("gold")) {
				if (totalBalance <= 60000) {
					return true;
				}
			} else if (plan.equalsIgnoreCase("silver")) {
				if (totalBalance <= 50000) {
					return true;
				}
			}}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
