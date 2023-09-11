package com.bankapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper 
{
	public static Connection getConnection( ) throws SQLException
	{
		
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Rutuja@123");
		
		return con;
		
	}

}
