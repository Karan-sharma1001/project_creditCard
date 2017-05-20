<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Transactions</h2>
<form method="post">
<input type="submit" value="Add" onclick="form.action='addtransaction.jsp'">
<input type="submit" value="Delete" onclick="form.action='deletetransaction.jsp'">
<input type="submit" value="Modify" onclick="form.action='modifytransaction.jsp'">
<input type="submit" value="View All" onclick="form.action='viewalltransaction.jsp'">
<input type="submit" value="Logout" onclick="form.action='controller?operation=logout'">

</form> 
<a href="welcomadmin.jsp">Click here to go back</a>
</body>
</html>