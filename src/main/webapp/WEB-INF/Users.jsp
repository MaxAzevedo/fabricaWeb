<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.fabricadeprogramador.entidade.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Usuários</title>
</head>
<body>
	<table>
		<tr><th>Nome</th></tr>
		<%
		LinkedList<User> users = (LinkedList<User>) request.getAttribute("users");
		for(User user:users){
		%>
		<tr>
			<td><% out.print(user.getName()); %></td>
			<td> <a href="usercontroller.do?action=delete&id=<% out.print(user.getId());%>">Excluir</a></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>