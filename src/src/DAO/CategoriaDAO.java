package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Business.Model.Categoria;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CategoriaDAO implements DAOinterface{

	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		try{	
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			preparedStatement = connect.prepareStatement("INSERT INTO bibliotecadigitale.categoria(nome) VALUES (?)");
			preparedStatement.setString(1,(String)args.get(0));
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			success=false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}catch(Exception e){
			success=false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Generico");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}finally{
				try{
						if(connect!=null) connect.close();
						if(preparedStatement!=null) preparedStatement.close();
						return success;
						}
					catch(final SQLException e){
						final Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Errore");
						alert.setHeaderText("Errore Database");
						alert.setContentText(e.getMessage());
						alert.showAndWait();
						return false;
						}
					}
		}	
	
	
	@SuppressWarnings("finally")
	public Object retrieve(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		Categoria categoria = null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM bibliotecadigitale.categoria");
			while(resultSet.next()){
				String nome = resultSet.getString("nome");
				
					categoria = new Categoria(nome);
					connect.close();
					Statement.close();
					resultSet.close();
					return categoria; 
				
				
			}	
		}
		catch(SQLException e){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		catch(Exception e){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Generico");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		finally{
			try{
				if(connect!=null) connect.close();
				if(Statement!=null) Statement.close();
				if(resultSet!=null) resultSet.close();
				return categoria;
			}
			catch(final SQLException e){		
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Errore");
				alert.setHeaderText("Errore Database");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				return null;
			}
						
		}
	}
}
