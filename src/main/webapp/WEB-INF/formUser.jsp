<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.fabricadeprogramador.entidade.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	User user = (User) request.getAttribute("user");
	session.setAttribute("userId", user.getId());
%>
	<form action="usercontroller.do" method="post">
	    <input type="hidden" name="userId" value="<%out.print(user.getId());%>"/>
		Name:<input type="text" name="name" value="<%out.print(user.getName());%>"/><br/>
		Login:<input type="text" name="login" value="<%out.print(user.getName());%>"/><br/>
		Password:<input type="password" name="password" value="<%out.print(user.getName());%>"/><br/>
		
		<input type="submit" value="Save"/>
	</form>
</body>
</html>