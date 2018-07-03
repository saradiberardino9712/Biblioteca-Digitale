package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Business.Model.Opera;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OperaDAO implements DAOinterface{

	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		try{	
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca_digitale","root","");
			preparedStatement = connect.prepareStatement("INSERT INTO Biblioteca_digitale.opera(ID_categoria, titolo, anno, autore) VALUES (?,?,?,?)");
			preparedStatement.setInt (1, 1);
			preparedStatement.setString(2,(String)args.get(1));
			preparedStatement.setInt (3, 2);
			preparedStatement.setString(4,(String)args.get(3));
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
		Opera opera= null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca_digitale","root","");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM biblioteca_digitale.opera");
			while(resultSet.next()){
				int ID_categoria = resultSet.getInt("ID_categoria");
				String titolo = resultSet.getString("titolo");
				int anno =resultSet.getInt("anno");
				String autore = resultSet.getString("autore");
				opera= new Opera (ID_categoria, titolo, anno, autore);
				
					connect.close();
					Statement.close();
					resultSet.close();
					return opera;
				 
						
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
				return opera;
				
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
	
	
	