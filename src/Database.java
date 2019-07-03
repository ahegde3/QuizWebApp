

import java.sql.*;


public class Database{
	 
	  static final String JDBC_DRIVER="org.postgresql.Driver";
	   static final String DB_URL="jdbc:postgresql://localhost:5432/test";
	   static final String user="TestDB";
	   static final String pass="pass,123";
	 
	   public static Connection get() 
	 { 
		 Connection conn=null;
		   try{
		   Class.forName("org.postgresql.Driver");

		      System.out.println("Connecting to database....");
		      conn=DriverManager.getConnection(DB_URL,user,pass);
		      return conn;
		   } catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		    return conn;

	 }
}