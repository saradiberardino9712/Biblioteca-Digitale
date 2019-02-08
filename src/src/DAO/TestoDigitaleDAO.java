package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Business.Model.TestoDigitale;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TestoDigitaleDAO implements DAOinterface{

	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		try{	
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			preparedStatement = connect.prepareStatement("INSERT INTO bibliotecadigitale.testo_digitale(testo,statoT,ID_utente,ID_immagine) VALUES (?,?,?,?)");
			preparedStatement.setString(1,(String)args.get(0));
			preparedStatement.setString(2,(String)args.get(1));
			preparedStatement.setInt(3,(int)args.get(2));
			preparedStatement.setInt(4,(int)args.get(3));
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

	@SuppressWarnings({ "finally"})
	public ArrayList<TestoDigitale> retrieve(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		ArrayList<TestoDigitale> trascrizioni = new ArrayList<>();
		TestoDigitale text=null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			Statement = connect.createStatement();
			if(args.get(0)==null) {
				resultSet = Statement.executeQuery("SELECT o.titolo, i.numero_pagina, t.testo,i.ID,t.ID_utente from immagine i join opera o on (i.ID_opera=o.ID) "
						+ "join testo_digitale t on (i.ID=t.ID_immagine)");
				while(resultSet.next()){
					int numero_pagina = resultSet.getInt("numero_pagina");
					String titolo=resultSet.getString("titolo");
					String testo=resultSet.getString("testo");
					int idimg=resultSet.getInt("ID");
					int idutente=resultSet.getInt("ID_utente");
					text=new TestoDigitale(idutente,idimg,testo,titolo,numero_pagina);
					trascrizioni.add(text);
				}
			}else {
				resultSet = Statement.executeQuery("SELECT o.titolo, i.numero_pagina, t.testo,i.ID,t.ID_utente from immagine i join opera o on (i.ID_opera=o.ID) "
						+ "join testo_digitale t on (i.ID=t.ID_immagine) where statoT='" + args.get(0)+"'");
				while(resultSet.next()){
					int numero_pagina = resultSet.getInt("numero_pagina");
					String titolo=resultSet.getString("titolo");
					String testo=resultSet.getString("testo");
					int idimg=resultSet.getInt("ID");
					int idutente=resultSet.getInt("ID_utente");
					text=new TestoDigitale(idutente,idimg,testo,titolo,numero_pagina);
					trascrizioni.add(text);
				}
			}
			connect.close();
			Statement.close();
			resultSet.close();
			return trascrizioni;	
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
				return trascrizioni;
				
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

	@SuppressWarnings("finally")
	public boolean updatestato(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		boolean success=true; 
		String stato=(String) args.get(0);
		int idimg=(int) args.get(1);
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			Statement = connect.createStatement(); 
			Statement.executeUpdate("UPDATE bibliotecadigitale.testo_digitale SET statoT= '"+ stato +"' WHERE ID_immagine='"+idimg+"'");
		}catch(SQLException e){
			success=false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			}
			catch(Exception e){
			success=false;
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
	public boolean remove(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		boolean success=true;
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			Statement = connect.createStatement(); 
			Statement.executeUpdate("DELETE FROM bibliotecadigitale.testo_digitale WHERE testo='" + args.get(0)+ "' and ID_immagine='" + args.get(1) +"'" );
	}catch(SQLException e){
		success=false;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Errore");
		alert.setHeaderText("Errore Database");
		alert.setContentText(e.getMessage());
		alert.showAndWait();
		}
		catch(Exception e){
		success=false;
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
}
