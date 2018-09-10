package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import Business.Model.Notifica;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NotificaDAO {
	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success = true;
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale",
					"root", "ciao");
			preparedStatement = connect.prepareStatement(
					"INSERT INTO bibliotecadigitale.notifica(orario,descrizione,idutentenot,ID_utente) VALUES (?,?,?,?)");
			preparedStatement.setTimestamp(1, (Timestamp) args.get(0));
			preparedStatement.setString(2,(String) args.get(1));
			preparedStatement.setInt(3, (int) args.get(2));
			preparedStatement.setInt(4, (int) args.get(3));
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
	public ArrayList<Notifica> retrieve(int id) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		Notifica notifica=null;
		ArrayList<Notifica> out=new ArrayList<>();
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecadigitale",
					"root", "ciao");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM bibliotecadigitale.notifica");
				while (resultSet.next()) {
					Timestamp orario=resultSet.getTimestamp("orario");
					String descrizione=resultSet.getString("descrizione");
					int idutentenot=resultSet.getInt("IDutentenot");
					if(idutentenot==id) {
						notifica=new Notifica(orario,descrizione);
						out.add(notifica);
					}
				}
						connect.close();
						Statement.close();
						resultSet.close();
						return out;
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
				return out;
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
}