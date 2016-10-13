package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.entidade.User;
import br.com.fabricadeprogramador.persistencia.jdbc.UserDAO;

@WebServlet("/authentication.do")
public class LoginController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		User user = new User();
		
		user.setLogin(login);
		user.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		
		User userAuth = userDAO.autheticateUser(login, password);
		
		if(userAuth!=null){
			HttpSession session = req.getSession();
			session.setAttribute("user", userAuth);
			
			session.setMaxInactiveInterval(600);
			
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		} else {
			resp.getWriter().print("");
		}
		
		
		
	}
}	
