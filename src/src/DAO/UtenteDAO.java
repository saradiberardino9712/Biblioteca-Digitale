package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UtenteDAO implements DAOinterface{

	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		try{	
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca_digitale","root","ciao");
			preparedStatement = connect.prepareStatement("INSERT INTO Biblioteca_digitale.utente(nome,cognome,indirizzo,password,data_nascita,email,titolo_studio,professione,ID_ruolo) VALUES (?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1,(String)args.get(0));
			preparedStatement.setString(2,(String)args.get(1));
			preparedStatement.setString(3,(String)args.get(2));
			preparedStatement.setString(4,(String)args.get(3));
			preparedStatement.setDate(5, (java.sql.Date)args.get(4));
			preparedStatement.setString(6,(String)args.get(5));
			preparedStatement.setString(7,(String)args.get(6));
			preparedStatement.setString(8,(String)args.get(7));
			preparedStatement.setInt(9, 1);
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
		Utente utente = null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca_digitale","root","ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM Biblioteca_digitale.utente");
			while(resultSet.next()){
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				String indirizzo=resultSet.getString("indirizzo");
				String password = resultSet.getString("password");
				Date data_nascita=resultSet.getDate("data_nascita");
				String email = resultSet.getString("email");
				String titolo_studio=resultSet.getString("titolo_studio");
				String professione=resultSet.getString("professione");
				int ID_ruolo=resultSet.getInt("ID_ruolo");
				if(email.equals((String)args.get(0)) && password.equals((String)args.get(1))){	
					utente = new Utente(nome,cognome,indirizzo,password,data_nascita,email,titolo_studio,professione,ID_ruolo);
					connect.close();
					Statement.close();
					resultSet.close();
					return utente; 
				}
				else
					utente = null;
					
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
				return utente;
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
