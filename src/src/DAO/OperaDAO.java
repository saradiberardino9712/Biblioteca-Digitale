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
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			if(args.get(4)==null) {
				preparedStatement = connect.prepareStatement("INSERT INTO bibliotecadigitale.opera(titolo, anno, autore, pagine_totali) VALUES (?,?,?,?)");
				preparedStatement.setString(1, (String)args.get(0));
				preparedStatement.setString(2,(String)args.get(1));
				preparedStatement.setString(3, (String)args.get(2));
				preparedStatement.setInt(4,(int)args.get(3));
				preparedStatement.executeUpdate();
			}
			else {
				preparedStatement = connect.prepareStatement("INSERT INTO bibliotecadigitale.opera(titolo, anno, autore, pagine_totali,ID_categoria) VALUES (?,?,?,?,?)");
				preparedStatement.setString(1, (String)args.get(0));
				preparedStatement.setString(2,(String)args.get(1));
				preparedStatement.setString(3, (String)args.get(2));
				preparedStatement.setInt(4,(int)args.get(3));
				preparedStatement.setInt(5,(int)args.get(4));
				preparedStatement.executeUpdate();
			}
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
	public ArrayList<Opera> retrieve(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		ArrayList<Opera> listaopere=new ArrayList<Opera>();
		Opera opera=null;
		try{
			connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root","ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM bibliotecadigitale.opera");
			while(resultSet.next()){
				int id= resultSet.getInt("ID");
				int ID_categoria = resultSet.getInt("ID_categoria");
				String titolo = resultSet.getString("titolo");
				String anno =resultSet.getString("anno");
				String autore = resultSet.getString("autore");
				int pagine=resultSet.getInt("pagine_totali");
				opera=new Opera(id,ID_categoria, titolo, anno, autore, pagine);
				listaopere.add(opera);
			}
			connect.close();
			Statement.close();
			resultSet.close();	
			return listaopere;
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
				return listaopere;
				
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
	
	
	