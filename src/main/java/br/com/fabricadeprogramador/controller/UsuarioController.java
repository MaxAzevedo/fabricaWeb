package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.entidade.User;
import br.com.fabricadeprogramador.persistencia.jdbc.UserDAO;

@WebServlet("/usercontroller.do")
public class UsuarioController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2018774741868079893L;

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
		String id = req.getParameter("userId");
		String name = req.getParameter("name");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		User user = new User();
		if(!id.equals("null")){
			user.setId(Integer.parseInt(id));			
		}
		user.setName(name);
		user.setLogin(login);
		user.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		
		userDAO.save(user);
		
		resp.sendRedirect("usercontroller.do?action=list");
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
		else if(action.equals("delete")){
			String id = req.getParameter("id");
			if(id!=null && !id.equals("")){
				userDAO.delete(Integer.parseInt(id));
				resp.sendRedirect("usercontroller.do?action=list");
			}
		}
		else if(action.equals("edit")){
			String userId = req.getParameter("id");
			User user = userDAO.searchById(Integer.parseInt(userId));
			req.setAttribute("user", user);
			RequestDispatcher reqDeispacher = req.getRequestDispatcher("WEB-INF/formUser.jsp");
			reqDeispacher.forward(req, resp);
			resp.sendRedirect("usercontroller.do?action=list");
		}
		else if(action.equals("add")){
			User user = new User ("","","");
			req.setAttribute("user", user);
			RequestDispatcher reqDeispacher = req.getRequestDispatcher("WEB-INF/formUser.jsp");
			reqDeispacher.forward(req, resp);
			resp.sendRedirect("usercontroller.do?action=list");
		}
	}
}
