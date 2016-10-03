package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import br.com.fabricadeprogramador.entidade.User;
import br.com.fabricadeprogramador.persistencia.jdbc.UserDAO;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

@WebServlet("/usercontroller.do")
public class UsuarioController extends HttpServlet {
	
	
	public UsuarioController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setLogin(login);
		user.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		
		userDAO.save(user);
		
		resp.getWriter().println("Success");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		String action = req.getParameter("action");
		if(action.equals("list")){
			LinkedList<User> users = userDAO.getAllUser();
			req.setAttribute("users", users);
			RequestDispatcher reqDeispacher = req.getRequestDispatcher("WEB-INF/Users.jsp");
			reqDeispacher.forward(req, resp);
		}
		
		
		if(action.equals("delete")){
			String id = req.getParameter("id");
			if(id!=null && !id.equals("")){
				userDAO.delete(Integer.parseInt(id));
			}
		}
		
	}
}
