<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
 
window.history.forward();
function noBack(){
window.history.forward();
}
</script>
</head>
<body>
<font size=4 color="rgb(255,153,102)">Logged in as admin <%=session.getAttribute("admin") %></font><br/><br/><br/>
<form method="post" >
<input type="submit" onclick="form.action='controller?operation=viewcard'" value="Cards">
<input type="submit" onclick="form.action='transaction.jsp'" value="Transactions">
<input type="submit" onclick="form.action='controller?operation=viewcustomer'" value="View Customers">
<input type="submit" onclick="form.action='CreditController?operation=logout'" value="logout">
</form>
<a href="homepage.jsp">Home</a>

</body>
</html>