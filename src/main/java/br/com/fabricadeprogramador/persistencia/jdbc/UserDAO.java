package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import br.com.fabricadeprogramador.entidade.User;

public class UserDAO {
	
	private Connection conx = ConnectionFactory.getConnection();
	
	public void register(User user){
	
		String sql = "INSERT INTO public.usuario( login, senha, nome) VALUES (?,?,md5(?))";
		
	   try {
		   
		PreparedStatement prepareStatment = conx.prepareStatement(sql.toString());
		prepareStatment.setString(1, user.getLogin());
		prepareStatment.setString(2, user.getPassword());
		prepareStatment.setString(3, user.getName());
		
		prepareStatment.execute();
		
		prepareStatment.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	public void update(User user) {
		String sql = "UPDATE public.usuario SET login=? , senha=md5(?) , nome=? WHERE id=?";
		
		   try {
			   
			PreparedStatement prepareStatment = conx.prepareStatement(sql.toString());
			prepareStatment.setString(1, user.getLogin());
			prepareStatment.setString(2, user.getPassword());
			prepareStatment.setString(3, user.getName());
			prepareStatment.setInt(4, user.getId());
			
			prepareStatment.execute();
			
			prepareStatment.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void delete(Integer userId) {
		String sql = "DELETE FROM public.usuario WHERE id=?";
		
		try {
			   
			PreparedStatement prepareStatment = conx.prepareStatement(sql.toString());
			prepareStatment.setInt(1, userId);
			
			prepareStatment.execute();
			
			prepareStatment.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void save(User user) {
		if (user.getId() == null){
			this.register(user);
		}
		else {
			this.update(user);
		}
	}
	
	/**
	 * Search the user by id
	 * @param userId number of user's id
	 * @return user object (when it find it) or null.
	 */
	public User searchById( int userId){
		
		String sql = "SELECT * FROM usuario WHERE id=?";
		
		try {
			
			PreparedStatement prepareStatment  = conx.prepareStatement(sql);
			
			prepareStatment.setInt(1, userId);
			
			ResultSet result = prepareStatment.executeQuery();
			
			
			if(result.next()){
				User user = new User();
				user.setId(result.getInt("id"));
				user.setLogin(result.getString("login"));
				user.setName(result.getString("nome"));
				user.setPassword(result.getString("senha"));
				
				prepareStatment.close();
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Get all user from the BD
	 * @return A List of Users or null;
	 */
	public LinkedList<User> getAllUser(){
	
		String sql = "SELECT * FROM usuario";
		
		try {
			
			PreparedStatement preparedStatement = conx.prepareStatement(sql);
			
			ResultSet result = preparedStatement.executeQuery();
			
			
			LinkedList<User> users = new LinkedList<User>();
			
			while (result.next()) {
			
				User user = new User();
				user.setId(result.getInt("id"));
				user.setLogin(result.getString("login"));
				user.setName(result.getString("nome"));
				user.setPassword(result.getString("senha"));
				
				users.add(user);
			}
			
			preparedStatement.close();
			return users;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	
	}
	
	/**
	 * Get the user from the DB by login and password
	 * @param login
	 * @param password
	 * @return
	 */
	public User autheticateUser(String login, String password){
		
		String sql = "SELECT * FROM usuario WHERE login=? AND senha=md5(?)";
		
		try {
			
			PreparedStatement preparedStatement = conx.prepareStatement(sql);
			
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			
			ResultSet result = preparedStatement.executeQuery();
			
			
			if(result.next()){
				User user = new User();
				user.setId(result.getInt("id"));
				user.setLogin(result.getString("login"));
				user.setName(result.getString("nome"));
				user.setPassword(result.getString("senha"));
				
				preparedStatement.close();
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
