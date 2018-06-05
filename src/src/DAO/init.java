package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class init {
	 public static void main (String [] args) {
		 connection conn= null;
		 
		 try {
			 connection conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca_digitale", "root", "");
			 System.out.println("Connection success");
		 
		 } catch (Exception e) {
			 System.err.println(e);
		 }
		 
	 }
}
		 

     
