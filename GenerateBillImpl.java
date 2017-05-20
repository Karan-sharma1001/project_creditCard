package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.beans.Bill;
import com.util.JdbcConnection;

public class GenerateBillImpl implements GenerateBill {

	@Override
	public Bill generateBill(int user_id) {
		Connection con;
		PreparedStatement pst;
		CallableStatement cst;
		ResultSet rs;
		Bill b = null;
		String plan = null;
		int cid = 0;
		double balance;
		double totalBill = 0;
		double discount = 0;
		double billamt = 0, limit = 0;
		con = JdbcConnection.getConnection();
		try {
			pst = con
					.prepareStatement("select plan,user_id from credit_card c,user_table u where u.ccnumber=c.ccnumber and u.user_id=?");
			
			pst.setInt(1,user_id);
			rs = pst.executeQuery();

			if (rs.next()) {
				plan = rs.getString(1);
				cid = rs.getInt(2);
				cst = con.prepareCall("{call getbillamount(?,?)}");
				cst.setInt(1, cid);
				cst.registerOutParameter(2, Types.INTEGER);
				cst.executeUpdate();
				totalBill = cst.getInt(2);

				if (plan.equalsIgnoreCase("platinum")) {
					discount = totalBill * 0.05;
					billamt = totalBill - discount;
					limit = 75000;

				} else if (plan.equalsIgnoreCase("gold")) {
					billamt = totalBill;
					limit = 60000;
				} else if (plan.equalsIgnoreCase("silver")) {
					billamt = totalBill;
					limit = 50000;
				}
				balance = limit - billamt;
				b = new Bill();
				b.setTotal(totalBill);
				b.setDiscount(discount);
				b.setBillamt(billamt);
				b.setPlan(plan);
				b.setBalance(balance);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
