package br.com.fabricadeprogramador;

import java.util.LinkedList;

import br.com.fabricadeprogramador.entidade.User;
import br.com.fabricadeprogramador.persistencia.jdbc.UserDAO;

public class TestUserDAO {

	public static UserDAO userDAO = new UserDAO();
	
	public static void main(String[] args) {
		//testRegister();
		//testDelete();
		//testSave();
		//testSearch();
		//testGetAllUsers();
		testAuthenticate();
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
	
	public static void testSave(){
		User user = new User();
		user.setName("Izauro");
		user.setLogin("izauro");
		user.setPassword("izauro123");
		
		userDAO.save(user);

		user = new User();
		user.setId(3);
		user.setName("Izauro Alterado");
		user.setLogin("izauro_alterado");
		user.setPassword("izauro123");
		
		userDAO.save(user);
		
	}
	
	public static void testSearch(){
		User user = userDAO.searchById(1);
		System.out.println(user.toString());
	}
	
	public static void testGetAllUsers(){
		LinkedList<User> users = userDAO.getAllUser();
		System.out.println(users.toString());
	}
	
	public static void testAuthenticate(){
		System.out.println(userDAO.autheticateUser("joao", "1234").toString());
	}
}
