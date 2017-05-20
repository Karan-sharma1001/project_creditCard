<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Select appropriate option to view</h3>
	<form name="view" action="controller" method="post">
		<select name="viewby">
			<option value="viewbytransactionid">View By Transaction Id</option>
			<option value="viewbylocation">View By Location</option>
			<option value="viewbyvendor">View By Vendor</option>
			<option value="viewbydate">View By Date</option>
			<option value="viewall">View All</option>
			
		</select><br>
		<input type="hidden" name="operation" value="view_transaction">
		<br> Enter value:<input type="text" name="viewbyvalue"><br>
		<input type="submit" value="View">
	</form>
	<a href="welcomeuser.jsp">Click here to back</a>
</body>
</html>