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
	<form name="search" action="controller" method="post">
		<select name="searchby">
			<option value="searchbyuser">View By UserId</option>
			<option value="searchbylocation">View By Location</option>
			<option value="searchbyvendor">View By Vendor</option>
			<option value="searchbydate">View By Date</option>
			<option value="searchall">View All</option>
			
		</select><br>
		<input type="hidden" name="operation" value="search_transaction">
		<br> Enter value:<input type="text" name="searchbyvalue"><br>
		<input type="submit" value="Search">
	</form>
	<a href="transaction.jsp">Click here to back</a>
</body>
</html>