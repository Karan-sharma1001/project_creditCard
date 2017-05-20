<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>WELCOME!!!</h1>
	<br>
	<br>

	<form action="controller" name="adminlogin" method="post">
		<table>
			<tr>
				<th colspan=2 align="center">ADMIN LOGIN</th>
			</tr>
			<tr>
				<td><b>Login ID</b></td>
				<td><input type="text" name="loginid"></td>
			</tr>
			<tr>
				<td><b>Password</b></td>
				<td><input type="password" name="password"><input
					type="hidden" name="operation" value="adminlogin"></td>
			</tr>
			<tr>
				<td colspan=2 align="center"><input type="submit" value="Login">
					<input type="reset" value="Reset"></td>
			</tr>

		</table>
		<a href="Resetpassword.jsp">Click here to reset password</a>
	</form>
</body>
</html>