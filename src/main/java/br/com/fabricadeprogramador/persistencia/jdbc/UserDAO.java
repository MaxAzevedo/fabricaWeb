package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fabricadeprogramador.entidade.User;

public class UserDAO {
	
	private Connection conx = ConnectionFactory.getConnection();
	
	public void register(User user){
	
		String sql = "INSERT INTO public.usuario( login, senha, nome) VALUES (?,?,?);";
		
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
		String sql = "UPDATE public.usuario SET login=? , senha=? , nome=? WHERE id=?;";
		
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
		String sql = "DELETE FROM public.usuario WHERE id=?;";
		
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

}
