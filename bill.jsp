<%@page import="com.beans.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill</title>
 <%!Bill b=null; 
int user_id;%>
</head>
<body>

<%b=(Bill)session.getAttribute("bill");
user_id=(Integer) session.getAttribute("cid"); %>
<form>
	<table>
		<tr><td>Customer ID</td><td><%out.println(user_id);%></td></tr>
<tr><td>Total</td><td><%=b.getTotal() %></td></tr>
<tr><td>Discount</td><td><%=b.getDiscount() %></td></tr>
<tr><td>Bill Amount</td><td><%=b.getBillamt() %></td></tr>
<tr><td colspan=2>Available Balance :   <%=b.getBalance() %></td></tr>
	</table>
	</form>
</body>
</html>