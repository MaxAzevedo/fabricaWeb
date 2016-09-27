package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.entidade.User;
import br.com.fabricadeprogramador.persistencia.jdbc.UserDAO;

public class TestUserDAO {

	public static UserDAO userDAO = new UserDAO();
	
	public static void main(String[] args) {
		//testRegister();
		testDelete();
	}
	
	public static void testRegister(){
		User user = new User();
		user.setName("Pedro");
		user.setLogin("pedin");
		user.setPassword("1234");
		
		userDAO.register(user);
		
		System.out.println("registered");
	}
	
	public static void testAlter(){
		User user = new User();
		user.setId(2);
		user.setName("Pedro Alterado");
		user.setLogin("pedin_alterado");
		user.setPassword("123456");
		
		userDAO.update(user);
		
		System.out.println("updated");
		
		
	}
	
	public static void testDelete(){
		User user = new User();
		user.setId(2);
		
		userDAO.delete(user.getId());
		
		System.out.println("Deleted");
	}

}
