<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Add Transaction</h2>
	<form action="controller" name="add_transaction" method="post">
		<table>
			<tr>
				<td>Transaction Id:</td>
				<td><input type="text" name="transaction_id"></td>
				<td><input type="hidden" name="operation"
					value="add_transaction"></td>
			</tr>
			<tr>
				<td>User Id:</td>
				<td><input type="text" name="user_id"></td>
			</tr>

			<tr>
				<td>Transaction date:</td>
				<td><input type="text" name="transaction_date"></td>
			</tr>
			<tr>
				<td>Vendor:</td>
				<td><input type="text" name="vendor"></td>
			</tr>
			<tr>
				<td>Location:</td>
				<td><input type="text" name="location"></td>
			</tr>
			<tr>
				<td>Amount:</td>
				<td><input type="text" name="amount"></td>
			</tr>
			<tr>
				<td>Remarks:</td>
				<td><input type="text" name="remarks"></td>
			</tr>
			<tr>

				<td><input type="submit" value="ADD"></td>
				<td><input type="reset" value="CLEAR"></td>
			</tr>
		</table>
		<a href="transaction.jsp">Click here to go back</a>
	</form>
</body>
</html>