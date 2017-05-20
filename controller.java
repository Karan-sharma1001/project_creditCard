package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Types;

import com.beans.Bill;
import com.beans.Login;
import com.beans.Transaction;
import com.dao.CheckAmountValidity;
import com.dao.GenerateBillImpl;
import com.dao.LoginValidateImpl;

import com.util.JdbcConnection;

public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw;

	public controller() {
		super();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String operation = request.getParameter("operation");
		if (operation.equals("userlogin")) {
			userLogin(request, response);
		}

		if (operation.equals("adminlogin")) {
			adminLogin(request, response);
		}
		if (operation.equals("viewcard")) {
			viewCard(request, response);
		}
		if (operation.equals("viewcustomer")) {
			viewCustomer(request, response);
		}
		if (operation.equals("login")) {
			session.invalidate();
			response.sendRedirect("logout.jsp");
		}
		if (operation.equals("add_transaction")) {
			addTransaction(request, response);
		}
		if (operation.equals("delete_transaction")) {
			deleteTransaction(request, response);
		}
		if (operation.equals("search_transaction")) {
			searchTransaction(request, response);
		}
		if (operation.equals("modify_transaction")) {
			modifyTransaction(request, response);
		}
		if (operation.equals("view_transaction")) {
			viewTransaction(request, response);
		}
		if (operation.equals("show_bill")) {
			showBill(request, response);
		}

	}

	private void showBill(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession hs = request.getSession();
		int user_id = (Integer) hs.getAttribute("cid");
		System.out.println("value" + user_id);
		GenerateBillImpl g = new GenerateBillImpl();
		Bill b = g.generateBill(user_id);
		hs.setAttribute("bill", b);
		RequestDispatcher rd = request.getRequestDispatcher("bill.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void viewTransaction(HttpServletRequest request,
			HttpServletResponse response) {
		Connection con = null;
		con = JdbcConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String name = request.getParameter("viewby");
		String value = request.getParameter("viewbyvalue");
		HttpSession hs = request.getSession();
		Login l = new Login();
		System.out.println("value=" + l.getUser_id());
		/* String user_id=(String) */System.out
				.println((hs.getAttribute("cid")));

		try {

			PrintWriter pw = response.getWriter();
			if (name.equals("viewbytransactionid")) {
				pst = con
						.prepareStatement("select * from transactions where transaction_id=? and user_id=?");
				pst.setInt(1, Integer.parseInt(value));
				pst.setInt(2, (Integer) hs.getAttribute("cid"));
				rs = pst.executeQuery();
				pw.println("<br/><br/><center><h3><u>TRANSACTION DETAILS BASED ON TRANSACTION ID</u></h3>");
			} else if (name.equals("viewbylocation")) {
				pst = con
						.prepareStatement("select * from transactions where location=? and user_id=?");
				pst.setString(1, value);
				pst.setInt(2, (Integer) hs.getAttribute("cid"));
				rs = pst.executeQuery();
				pw.println("<br/><br/><center><h3><u>TRANSACTION DETAILS BASED ON LOCATION</u></h3>");
			}
			if (rs.next()) {

				pw.println("<table border=1><tr><th>TRANSACTION ID</th><th>USER ID</th><th>TRANSACTION DATE</th><th>VENDOR</th><th>LOCATION</th><th>AMOUNT</th><th>REMARKS</th></tr>");
				while (rs.next()) {
					pw.println("<tr><td>" + rs.getString(1) + "</td><td>"
							+ rs.getString(2) + "</td><td>" + rs.getString(3)
							+ "</td><td>" + rs.getString(4) + "</td><td>"
							+ rs.getString(5) + "</td><td>" + rs.getString(6)
							+ "</td><td>" + rs.getString(7) + "</td></tr>");
				}

				pw.println("</table></center></body>");
			} else {
				pw.println("NO TRANSACTIONS FOUND!!!");
			}
			pw.close();
			rs.close();
			pst.close();
			con.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void searchTransaction(HttpServletRequest request,
			HttpServletResponse response) {
		Connection con = null;
		con = JdbcConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String name = request.getParameter("searchby");
		String value = request.getParameter("searchbyvalue");
		try {
			PrintWriter pw = response.getWriter();
			if (name.equals("searchbyuser")) {
				pst = con
						.prepareStatement("select * from transactions where user_id=?");
				pst.setInt(1, Integer.parseInt(value));
				rs = pst.executeQuery();
				pw.println("<br/><br/><center><h3><u>TRANSACTION DETAILS BASED ON USER</u></h3>");
			} else if (name.equals("searchbylocation")) {
				pst = con
						.prepareStatement("select * from transactions where location=?");
				pst.setString(1, value);
				rs = pst.executeQuery();
				pw.println("<br/><br/><center><h3><u>TRANSACTION DETAILS BASED ON LOCATION</u></h3>");
			} else if (name.equals("searchbyvendor")) {
				pst = con
						.prepareStatement("select * from transactions where vendor=?");
				pst.setString(1, value);
				rs = pst.executeQuery();
				pw.println("<br/><br/><center><h3><u>TRANSACTION DETAILS BASED ON VENDOR</u></h3>");
			} else if (name.equals("searchbydate")) {
				pst = con
						.prepareStatement("select * from transactions where transaction_date=?");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				java.util.Date ud = sdf.parse(value);
				java.sql.Date sd = new java.sql.Date(ud.getTime());
				pst.setDate(1, sd);
				rs = pst.executeQuery();
				pw.println("<br/><br/><center><h3><u>TRANSACTION DETAILS BASED ON DATE</u></h3>");
			} else if (name.equals("searchall")) {
				pst = con.prepareStatement("select * from transactions");
				rs = pst.executeQuery();
				pw.println("<br/><br/><center><h3><u>ALL TRANSACTION DETAILS</u></h3>");
			}
			if (rs.next()) {

				pw.println("<table border=1><tr><th>TRANSACTION ID</th><th>USER ID</th><th>TRANSACTION DATE</th><th>VENDOR</th><th>LOCATION</th><th>AMOUNT</th><th>REMARKS</th></tr>");
				while (rs.next()) {
					pw.println("<tr><td>" + rs.getString(1) + "</td><td>"
							+ rs.getString(2) + "</td><td>" + rs.getString(3)
							+ "</td><td>" + rs.getString(4) + "</td><td>"
							+ rs.getString(5) + "</td><td>" + rs.getString(6)
							+ "</td><td>" + rs.getString(7) + "</td></tr>");
				}

				pw.println("</table></center></body>");
			} else {
				pw.println("NO TRANSACTIONS FOUND!!!");
			}
			pw.close();
			rs.close();
			pst.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private void modifyTransaction(HttpServletRequest request,
			HttpServletResponse response) {
		Connection con = null;
		con = JdbcConnection.getConnection();
		PreparedStatement pst = null;
		int result = 0;
		int tid = Integer.parseInt(request.getParameter("transaction_id"));

		String name = request.getParameter("modify_option");
		String value = request.getParameter("new_value");

		try {
			PrintWriter pw = response.getWriter();
			pst = con
					.prepareStatement("select * from transactions where transaction_id=?");
			pst.setInt(1, tid);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				if (name.equals("modify_date")) {
					pst = con
							.prepareStatement("update transactions set transaction_date=? where transaction_id=?");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
					java.util.Date ud = sdf.parse(value);
					java.sql.Date sd = new java.sql.Date(ud.getTime());

					pst.setDate(1, sd);
					pst.setInt(2, tid);
					result = pst.executeUpdate();
					pst.close();
				} else if (name.equals("modify_vendor")) {
					pst = con
							.prepareStatement("update transactions set vendor=? where transaction_id=?");
					pst.setString(1, value);
					pst.setInt(2, tid);

					result = pst.executeUpdate();

					pst.close();
					con.commit();
				} else if (name.equals("modify_location")) {
					pst = con
							.prepareStatement("update transactions set location=? where transaction_id=?");
					pst.setString(1, value);
					pst.setInt(2, tid);
					result = pst.executeUpdate();
					pst.close();
				} else if (name.equals("modify_amount")) {
					int userId = 0;
					pst = con
							.prepareStatement("select * from transactions where transaction_id=?");
					pst.setInt(1, tid);
					rs = pst.executeQuery();
					if (rs.next()) {
						userId = rs.getInt(2);
						pw.println(userId);
					} else {
						pw.println("User Id does not exist for this transaction");

					}
					CallableStatement cst = null;
					cst = con.prepareCall("{call getbillamount(?,?)}");
					cst.setInt(1, userId);
					cst.registerOutParameter(2, Types.INTEGER);
					cst.executeUpdate();
					int billamount = cst.getInt(2);
					System.out.println("billamount" + billamount);
					int totalBalance = billamount + Integer.parseInt(value);
					CheckAmountValidity check = new CheckAmountValidity();
					boolean validity = check
							.checkValidity(totalBalance, userId);
					System.out.println(validity);
					if (validity == true) {
						pst = con
								.prepareStatement("update transactions set amount=? where transaction_id=?");
						pst.setInt(1, Integer.parseInt(value));
						pst.setInt(2, tid);
						result = pst.executeUpdate();
					} else {
						pw.println("Amount limit crossed. Cant modify");
					}

				}
				con.commit();
				if (result > 0) {
					pw.println("Transaction updated successfully!!!!");
				}
				pw.close();
				pst.close();
				con.close();
			} else {
				pw.println("Transaction_id does not exist");
				RequestDispatcher rd = request
						.getRequestDispatcher("modifytransaction.jsp");
				rd.include(request, response);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private void deleteTransaction(HttpServletRequest request,
			HttpServletResponse response) {
		int tid = Integer.parseInt(request.getParameter("transaction_id"));
		Connection con = null;
		con = JdbcConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			PrintWriter pw = response.getWriter();
			pst = con
					.prepareStatement("select * from transactions where transaction_id=?");
			pst.setInt(1, tid);
			rs = pst.executeQuery();
			if (rs.next()) {
				pst = con
						.prepareStatement("delete from transactions where transaction_id=?");
				pst.setInt(1, tid);
				int result = pst.executeUpdate();
				if (result > 0) {
					pw.println("<h3>Deleted Successfully</h3>");
					RequestDispatcher rd = request
							.getRequestDispatcher("deletetransaction.jsp");
					rd.include(request, response);
				} else {
					pw.println("<h3>Error Occured</h3>");
					RequestDispatcher rd = request
							.getRequestDispatcher("deletetransaction.jsp");
					rd.include(request, response);
				}
			} else {
				pw.println("<h3>Invalid Transaction Id</h3>");
				RequestDispatcher rd = request
						.getRequestDispatcher("deletetransaction.jsp");
				rd.include(request, response);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void addTransaction(HttpServletRequest request,
			HttpServletResponse response) {

		Connection con = null;
		CallableStatement cst = null;
		con = JdbcConnection.getConnection();
		PreparedStatement pst = null;
		java.util.Date ud;
		try {
			int trans_id = Integer.parseInt(request
					.getParameter("transaction_id"));
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			String trans_date = request.getParameter("transaction_date");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			ud = sdf.parse(trans_date);
			java.sql.Date sd = new java.sql.Date(ud.getTime());
			String vendor = request.getParameter("vendor");
			String location = request.getParameter("location");
			int amount = Integer.parseInt(request.getParameter("amount"));
			String remarks = request.getParameter("remarks");
			Transaction t = new Transaction(trans_id, user_id, sd, vendor,
					location, amount, remarks);
			PrintWriter pw = response.getWriter();

			cst = con.prepareCall("{call getbillamount(?,?)}");
			cst.setInt(1, t.getUser_id());
			cst.registerOutParameter(2, Types.INTEGER);
			cst.execute();
			int currentAmount = cst.getInt(2);
			int totalAmount = currentAmount + amount;
			CheckAmountValidity check = new CheckAmountValidity();
			boolean validity = check.checkValidity(totalAmount, user_id);
			if (validity == true) {
				pst = con
						.prepareStatement("insert into transactions values(?,?,?,?,?,?,?)");
				pst.setInt(1, t.getTransaction_id());
				pst.setInt(2, t.getUser_id());
				pst.setDate(3, sd);
				pst.setString(4, t.getVendor());
				pst.setString(5, t.getLocation());
				pst.setInt(6, t.getAmount());
				pst.setString(7, t.getRemarks());

				System.out.println(t.getTransaction_id() + " " + t.getUser_id()
						+ " " + t.getVendor() + " " + t.getAmount() + " "
						+ t.getLocation() + " " + t.getRemarks() + " " + sd);

				int res = pst.executeUpdate();
				System.out.println("result :" + res);
				if (res > 0) {
					pw.println("Transaction added successfully!!!");
				} else {
					pw.println("Some error occured");
				}

			} else {
				pw.println("Amount limit crossed. Cant add!!");
			}

		} catch (ParseException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void viewCustomer(HttpServletRequest request,
			HttpServletResponse response) {
		Connection con;
		Statement st;
		ResultSet rs;
		HttpSession hs = request.getSession();
		con = JdbcConnection.getConnection();
		try {
			PrintWriter pw = response.getWriter();
			st = con.createStatement();
			rs = st.executeQuery("select * from user_table");
			pw.println("<div><center><font size=6 color=blue>GLOBAL BANK</font><br/><font size=4 color=black><b><u>CREDIT CARD MANAGEMENT</u></b></font></div>");
			pw.println("<br/><br/><center><h3><u>CUSTOMER DETAILS</u></h3>");
			pw.println("<table border=1><tr><th>CUSTOMER ID</th><th>NAME</th><th>CONTACT</th><th>EMAIL</th><th>ADDRESS</th><th>CREDIT CARD NUMBER</th></tr>");
			while (rs.next()) {
				pw.println("<tr><td>" + rs.getString(1) + "</td><td>"
						+ rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td><td>" + rs.getString(4) + "</td><td>"
						+ rs.getString(5) + "</td><td>" + rs.getString(6)
						+ "</td></tr>");
			}

			pw.println("</table></center></body>");
			pw.println("<a href='welcomadmin.jsp'>" + "Click here to go back"
					+ "</a>");
			pw.close();
			rs.close();
			st.close();
			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void viewCard(HttpServletRequest request,
			HttpServletResponse response) {
		Connection con;
		Statement st;
		ResultSet rs;
		HttpSession hs = request.getSession();
		con = JdbcConnection.getConnection();
		try {
			PrintWriter pw = response.getWriter();
			st = con.createStatement();
			rs = st.executeQuery("select * from credit_card");
			pw.println("<div><center><font size=6 color=blue>GLOBAL BANK</font><br/><font size=4 color=black><b><u>CREDIT CARD MANAGEMENT</u></b></font></div>");
			pw.println("<br/><br/><center><h3><u>CARD DETAILS</u></h3>");
			pw.println("<table border=1><tr><th>CARD NUMBER</th><th>PLAN</th><th>BALANCE</th></tr>");
			while (rs.next()) {
				pw.println("<tr><td>" + rs.getString(1) + "</td><td>"
						+ rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td></tr>");
			}

			pw.println("</table></center></body>");
			pw.println("<a href='welcomadmin.jsp'>" + "Click here to go back"
					+ "</a>");
			pw.close();
			rs.close();
			st.close();
			con.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void adminLogin(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession hs = request.getSession();
		Login adminLogin = new Login();

		String username = request.getParameter("loginid");
		String password = request.getParameter("password");

		adminLogin.setUser_id(username);
		adminLogin.setPassword(password);

		LoginValidateImpl lv = new LoginValidateImpl();

		String val = lv.adminValidate(adminLogin);

		try {
			pw = response.getWriter();
			System.out.println(val);
			if (val.equals("valid")) {
				hs.setAttribute("admin", username);
				RequestDispatcher rd = request
						.getRequestDispatcher("welcomadmin.jsp");
				rd.forward(request, response);

			} else {
				pw.println("<font color=red>* Invalid loginid/password </font>");
				RequestDispatcher rd = request
						.getRequestDispatcher("adminlogin.jsp");
				rd.include(request, response);

				pw.close();
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void userLogin(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter pw = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String value = null;

		Login login = new Login();
		LoginValidateImpl loginImpl = new LoginValidateImpl();
		String username = request.getParameter("loginid");
		String password = request.getParameter("password");
		login.setUser_id(username);
		login.setPassword(password);

		value = loginImpl.userValidate(login.getUser_id(), login.getPassword());

		try {

			pw = response.getWriter();
			con = JdbcConnection.getConnection();
			HttpSession hs = request.getSession();

			if (value.equalsIgnoreCase("valid")) {
				pst = con
						.prepareStatement("select user_id from login where username=?");
				pst.setString(1, username);
				rs = pst.executeQuery();
				if (rs.next()) {
					int user_id = rs.getInt(1);
					hs.setAttribute("cid", user_id);
				}
				hs.setAttribute("cname", username);

				RequestDispatcher rd = request
						.getRequestDispatcher("welcomeuser.jsp");

				rd.forward(request, response);

			} else {
				RequestDispatcher rd = request
						.getRequestDispatcher("customerlogin.jsp");
				pw.println("<font color=red>* Customer not found.Please relogin</font>");

				rd.include(request, response);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
