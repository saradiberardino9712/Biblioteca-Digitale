package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Business.Model.Immagine;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ImmagineDAO implements DAOinterface{

	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		try{	
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			preparedStatement = connect.prepareStatement("INSERT INTO bibliotecadigitale.immagine(numero_pagina, statoI, url, ID_utente, ID_opera) VALUES (?,?,?,?,?)");
			preparedStatement.setInt(1, (int)args.get(0));
			preparedStatement.setString(2, (String)args.get(1));
			preparedStatement.setString(3, (String)args.get(2));
			preparedStatement.setInt(4, (int)args.get(3));
			preparedStatement.setInt(5, (int)args.get(4));
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
	
	@SuppressWarnings({"finally"})
	public ArrayList<Immagine> retrieve(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		ArrayList<Immagine> immagine = new ArrayList<>();
		Immagine img=null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT i.*,o.titolo FROM bibliotecadigitale.opera as o join bibliotecadigitale.immagine as i on (o.ID=i.ID_opera) where i.statoI=\"in caricamento\"");
			while(resultSet.next()){
				int numero_pagina = resultSet.getInt("numero_pagina");
				String url= resultSet.getString("url");
				int ID_utente =resultSet.getInt("ID_utente");
				String titolo=resultSet.getString("titolo");
				if(ID_utente==(int)args.get(0)) {
					img=new Immagine(numero_pagina,url,titolo);
					immagine.add(img);
				}	
			}
			connect.close();
			Statement.close();
			resultSet.close();
			return immagine;	
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
				return immagine;
				
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
	
	@SuppressWarnings({ "finally"})
	public ArrayList<Immagine> retrieveacquisite(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		ArrayList<Immagine> immagine = new ArrayList<>();
		Immagine img=null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT o.titolo, o.pagine_totali, i.statoI, i.numero_pagina from immagine i join opera o on (i.ID_opera=o.ID)");
			while(resultSet.next()){
				int numero_pagina = resultSet.getInt("numero_pagina");
				String titolo=resultSet.getString("titolo");
				String stato=resultSet.getString("statoI");
				int pagtot=resultSet.getInt("pagine_totali");
				if(!stato.contains("eliminata")) {
					img=new Immagine(titolo,numero_pagina,pagtot);
					immagine.add(img);
				}
			}
			connect.close();
			Statement.close();
			resultSet.close();
			return immagine;	
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
				return immagine;
				
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
	
	@SuppressWarnings({ "finally"})
	public ArrayList<Immagine> retrieveverifica(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		ArrayList<Immagine> immagine = new ArrayList<>();
		Immagine img=null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT o.titolo, i.numero_pagina, i.url, i.ID from immagine i join opera o on (i.ID_opera=o.ID) where statoI='" + args.get(0)+"'");
			while(resultSet.next()){
				int id=resultSet.getInt("ID");
				int numero_pagina = resultSet.getInt("numero_pagina");
				String titolo=resultSet.getString("titolo");
				String url=resultSet.getString("url");
				img=new Immagine(id,titolo,numero_pagina,url);
				immagine.add(img);
			}
			connect.close();
			Statement.close();
			resultSet.close();
			return immagine;	
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
				return immagine;
				
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
	public boolean updatestato(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		boolean success=true; 
		String stato=(String) args.get(0);
		String titolo=(String) args.get(1);
		int npag=(int) args.get(2);
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			Statement = connect.createStatement(); 
			Statement.executeUpdate("UPDATE bibliotecadigitale.immagine SET statoI= '"+ stato +"' WHERE ID_opera=(select ID from opera where titolo='"+titolo+
					"') and numero_pagina= '"+npag+"'");
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
	
	@SuppressWarnings({ "finally", "unchecked" })
	public boolean delete(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		boolean success=true;
		ArrayList<Immagine> lista=(ArrayList<Immagine>) args.get(0);
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			Statement = connect.createStatement(); 
			for(Immagine i:lista) {
				Statement.executeUpdate("DELETE FROM bibliotecadigitale.immagine WHERE url='" + i.getUrl() + " ' ");
			}
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
	public ArrayList<Immagine> retrieveimgopera(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		ArrayList<Immagine> immagine = new ArrayList<>();
		Immagine img=null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT i.ID,i.numero_pagina, i.url from immagine i join opera o on (i.ID_opera=o.ID) where o.titolo='" + args.get(0)+"'order by i.numero_pagina");
			while(resultSet.next()){
				int numero_pagina = resultSet.getInt("numero_pagina");
				int id=resultSet.getInt("ID");
				String url=resultSet.getString("url");
				img=new Immagine(id,numero_pagina,url);
				immagine.add(img);
			}
			connect.close();
			Statement.close();
			resultSet.close();
			return immagine;	
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
				return immagine;
				
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
	
	
	