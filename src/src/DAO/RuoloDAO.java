package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RuoloDAO implements DAOinterface{

	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		try{	
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca_digitale","root","");
			preparedStatement = connect.prepareStatement("INSERT INTO Biblioteca_digitale.ruolo(nome_ruolo) VALUES (?)");
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
		Ruolo ruolo= null;
		Utente utente=null;
		if(args.get(0)!=null)
			utente= (Utente)args.get(0);
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca_digitale","root","");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM Biblioteca_digitale.ruolo");
			while(resultSet.next()){
				int ID= resultSet.getInt("ID");
				String nome_ruolo = resultSet.getString("nome_ruolo");
				if (utente==null) {
					if (nome_ruolo.equals((String)args.get(1))) {
						ruolo= new Ruolo (ID,nome_ruolo);
						connect.close();
						Statement.close();
						resultSet.close();
						return ruolo;
					} else
						ruolo = null;
				}else {
				if (utente.getIDruolo()==ID ) {
					ruolo= new Ruolo (ID,nome_ruolo);
					connect.close();
					Statement.close();
					resultSet.close();
					return ruolo;
				} else
					ruolo = null;
				}	
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
				return ruolo;	
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
	
	
	