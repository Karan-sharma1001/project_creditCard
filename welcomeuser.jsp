<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome <%=session.getAttribute("cname")%></h1>

	<form method="post">
	<input type="submit" value="Show Bill" onclick="form.action='./controller?operation=show_bill'">
	<input type="submit" value="View Transaction" onclick="form.action='viewalltransactionuser.jsp'">
	<input type="submit" value="Logout" onclick="form.action='./controller?operation=logout'">
	</form>
	
	
</body>
</html>