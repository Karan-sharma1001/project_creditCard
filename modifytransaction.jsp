<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		PrintWriter pw = response.getWriter();
		java.util.Date ud = new java.util.Date();
		int dt = ud.getDate();
		if (dt > 15) {
			pw.println("<h1> Transaction cannot be modified after 15th of month</h1>");
			/*RequestDispatcher rd = request
					.getRequestDispatcher("transaction.jsp");
			rd.include(request, response);*/
		} else {
	%>

	<form action="controller" name="modify_transaction" method="post">

		<table>
			<tr>
				<td>Enter Transaction ID:</td>
				<td><input type="text" name="transaction_id"></td>
				<td><input type="hidden" name="operation"
					value="modify_transaction"></td>
			</tr>
			<tr>
				<td>Select proper option to be modified:</td>
				<td><select name="modify_option">
						<option value="modify_date">Change date</option>
						<option value="modify_vendor">Change vendor</option>
						<option value="modify_location">Change location</option>
						<option value="modify_amount">Change amount</option>
				</select></td>

			</tr>
			<tr>
				<td>Enter new value:</td>
				<td><input type="text" name="new_value"></td>
			</tr>
			<tr align="center">
				<td><input type="submit" value="Modify"></td>
				<td><input type="reset" value="Clear"></td>

			</tr>

		</table>


	</form>
	<%
		}
	%>
	<a href="welcomadmin.jsp">Home</a>
	<a href="transaction.jsp">Click here to go back</a>
</body>
</html>