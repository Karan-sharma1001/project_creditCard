package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.beans.Login;
import com.util.JdbcConnection;

public class LoginValidateImpl implements LoginValidate {

	@Override
	public String userValidate(String username, String password) {
		Connection con = null;
		con = JdbcConnection.getConnection();
		String value = null;
		PreparedStatement pst = null;
		String role = null;
		String query = "select * from login where username=? and password=?";
		try {
			
			pst = con.prepareStatement(query);

			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				role = rs.getString(4);
				System.out.println(role);
				if (role.equalsIgnoreCase("user")) {
					value = "valid";
				} else {
					value = "invalid";
				}
			} else {

				value = "invalid";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;

	}

	@Override
	public String adminValidate(Login al) {
		String res = null;
		Connection con = null;
		PreparedStatement pst;
		ResultSet rs;
		con = JdbcConnection.getConnection();
		try {
			pst = con
					.prepareStatement("select * from login where username=? and password=?");
			pst.setString(1, al.getUser_id());
			pst.setString(2, al.getPassword());
			rs = pst.executeQuery();
			if (rs.next()) {
				String role = rs.getString(4);
				System.out.println(role);
				if (role.equalsIgnoreCase("admin")) {
					res = "valid";
				} else {
					res = "invalid";
				}

			} else {
				res = "invalid";
			}
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return res;
	}

}
