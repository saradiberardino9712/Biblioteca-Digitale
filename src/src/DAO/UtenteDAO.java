package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UtenteDAO implements DAOinterface {

	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success = true;
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale",
					"root", "ciao");
			preparedStatement = connect.prepareStatement(
					"INSERT INTO bibliotecadigitale.utente(nome,cognome,indirizzo,password,data_nascita,email,titolo_studio,professione,ID_ruolo) VALUES (?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, (String) args.get(0));
			preparedStatement.setString(2, (String) args.get(1));
			preparedStatement.setString(3, (String) args.get(2));
			preparedStatement.setString(4, (String) args.get(3));
			preparedStatement.setDate(5, (java.sql.Date) args.get(4));
			preparedStatement.setString(6, (String) args.get(5));
			preparedStatement.setString(7, (String) args.get(6));
			preparedStatement.setString(8, (String) args.get(7));
			preparedStatement.setInt(9, (int)args.get(8));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			success = false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (Exception e) {
			success = false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Generico");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (connect != null)
					connect.close();
				if (preparedStatement != null)
					preparedStatement.close();
				return success;
			} catch (final SQLException e) {
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
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale",
					"root", "ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM bibliotecadigitale.utente");
			while (resultSet.next()) {
				int id=resultSet.getInt("ID");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				String indirizzo = resultSet.getString("indirizzo");
				String password = resultSet.getString("password");
				Date data_nascita = resultSet.getDate("data_nascita");
				String email = resultSet.getString("email");
				String titolo_studio = resultSet.getString("titolo_studio");
				String professione = resultSet.getString("professione");
				int ID_ruolo = resultSet.getInt("ID_ruolo");
				if (email.equals((String) args.get(0)) && password.equals((String) args.get(1))) {
					utente = Utente.setInstance(id, nome, cognome, indirizzo, password, data_nascita, email, titolo_studio, professione, ID_ruolo);
					connect.close();
					Statement.close();
					resultSet.close();
					return utente;
				} else 
					utente=null;
			}	
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Generico");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (connect != null)
					connect.close();
				if (Statement != null)
					Statement.close();
				if (resultSet != null)
					resultSet.close();
				return utente;
			} catch (final SQLException e) {
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
	public TreeMap<String,String> retrieveutentidomanda(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		String utente=null;
		TreeMap<String,String> listautenti = new TreeMap<>();
		@SuppressWarnings("unchecked")
		ArrayList<Integer> listaid= (ArrayList<Integer>) args.get(0);
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale",
					"root", "ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM bibliotecadigitale.utente");
			while (resultSet.next()) {
				int id=resultSet.getInt("ID");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				String email = resultSet.getString("email");
				String titolo_studio = resultSet.getString("titolo_studio");
				int ID_ruolo = resultSet.getInt("ID_ruolo");
				for(int i: listaid) {			
					if(id==i) {
						utente=": il signor/signora " + nome + " " + cognome + " con ruolo: " + ID_ruolo + " e titolo di studio: " + titolo_studio;
						listautenti.put(utente, email);
					}
				}	
			}	
				connect.close();
				Statement.close();
				resultSet.close();
				return listautenti;		
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Generico");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (connect != null)
					connect.close();
				if (Statement != null)
					Statement.close();
				if (resultSet != null)
					resultSet.close();
				return listautenti;
			} catch (final SQLException e) {
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
	public int retrieveid(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		int ID=0;
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale",
					"root", "ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM bibliotecadigitale.utente");
			while (resultSet.next()) {
				int id=resultSet.getInt("ID");
				String email = resultSet.getString("email");
				int idruolo=resultSet.getInt("ID_ruolo");
				if(email.equals(args.get(0))||idruolo==(int)args.get(1)) {
					ID=id;
				}
			}	
				connect.close();
				Statement.close();
				resultSet.close();
				return ID;		
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Generico");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (connect != null)
					connect.close();
				if (Statement != null)
					Statement.close();
				if (resultSet != null)
					resultSet.close();
				return ID;
			} catch (final SQLException e) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Errore");
				alert.setHeaderText("Errore Database");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				return 0;
			}
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updatelogin(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		boolean success=true; 
		Utente utente=(Utente)args.get(0);
		boolean valore=(boolean) args.get(1);
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			Statement = connect.createStatement(); 
			if(valore)
				Statement.executeUpdate("UPDATE bibliotecadigitale.utente SET attivo=true WHERE email='" + utente.getEmail() + " ' AND password='" + utente.getPassword() + " ' ");
			else
				Statement.executeUpdate("UPDATE bibliotecadigitale.utente SET attivo=false WHERE email='" + utente.getEmail() + " ' AND password='" + utente.getPassword() + " ' ");
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
	public boolean updatedati(ArrayList<Object> args){
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true; 
		String emaildata=(String) args.get(0);
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			preparedStatement = connect.prepareStatement("UPDATE bibliotecadigitale.utente SET nome=?,cognome=?,indirizzo=?,titolo_studio=?,professione=?,password=?,"
					+ "data_nascita=? WHERE email='"+emaildata+"'");
			preparedStatement.setString(1, (String) args.get(1));
			preparedStatement.setString(2, (String) args.get(2));
			preparedStatement.setString(3, (String) args.get(3));
			preparedStatement.setString(4, (String) args.get(6));
			preparedStatement.setString(5, (String) args.get(7));
			preparedStatement.setString(6, (String) args.get(4));
			preparedStatement.setDate(7, (java.sql.Date) args.get(5));
			preparedStatement.executeUpdate();
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
	public boolean updatestato(ArrayList<Object> args){
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true; 
		String emaildata=(String) args.get(0);
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			preparedStatement = connect.prepareStatement("UPDATE bibliotecadigitale.utente SET statodomanda=? WHERE email='"+emaildata+"'");
			preparedStatement.setString(1, (String) args.get(1));
			preparedStatement.executeUpdate();
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
	public boolean updateid(ArrayList<Object> args){
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true; 
		String emaildata=(String) args.get(0);
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			preparedStatement = connect.prepareStatement("UPDATE bibliotecadigitale.utente SET ID_ruolo=? WHERE email='"+emaildata+"'");
			preparedStatement.setInt(1, (int) args.get(1));
			preparedStatement.executeUpdate();
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
	public ArrayList<String> retrieveutenti(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		String utente = null;
		ArrayList<String> utenti=new ArrayList<>();
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale",
					"root", "ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM bibliotecadigitale.utente");
			while (resultSet.next()) {
				int id=resultSet.getInt("ID");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				int livello= resultSet.getInt("livello");
				int ID_ruolo = resultSet.getInt("ID_ruolo");
				if (ID_ruolo==(int) args.get(0)) {
					utente = id+ ". Nome: " + nome + ", Cognome: "+ cognome +", Livello: " + livello;
					utenti.add(utente);
				}
			}
			connect.close();
			Statement.close();
			resultSet.close();
			return utenti;
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Generico");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (connect != null)
					connect.close();
				if (Statement != null)
					Statement.close();
				if (resultSet != null)
					resultSet.close();
				return utenti;
			} catch (final SQLException e) {
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
	public boolean updatelivello(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true; 
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			preparedStatement = connect.prepareStatement("UPDATE bibliotecadigitale.utente SET livello=? WHERE ID='"+args.get(2)+"' and nome='" + args.get(0)
			+"' and cognome='" +args.get(1)+"' ");
			preparedStatement.setInt(1, (int) args.get(3));
			preparedStatement.executeUpdate();
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
	public ArrayList<String> retrieveutentiAll() {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		String utente = null;
		ArrayList<String> utenti=new ArrayList<>();
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale",
					"root", "ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM bibliotecadigitale.utente");
			while (resultSet.next()) {
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				String email= resultSet.getString("email");
				utente = "Nome: " + nome + ", Cognome: "+ cognome +", Email: " + email;
				utenti.add(utente);
			}
			connect.close();
			Statement.close();
			resultSet.close();
			return utenti;
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Database");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore Generico");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} finally {
			try {
				if (connect != null)
					connect.close();
				if (Statement != null)
					Statement.close();
				if (resultSet != null)
					resultSet.close();
				return utenti;
			} catch (final SQLException e) {
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
	public boolean remove(String email) {
		Connection connect = null;
		Statement Statement = null;
		boolean success=true;
		try{
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale","root", "ciao");
			Statement = connect.createStatement(); 
			Statement.executeUpdate("DELETE FROM bibliotecadigitale.utente WHERE email='"+email+"'" );
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

