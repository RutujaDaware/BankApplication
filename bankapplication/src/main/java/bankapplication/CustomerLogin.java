package bankapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankapplication.Helper;
import com.mysql.cj.xdevapi.PreparableStatement;
@WebServlet("/loginvalidation")
public class CustomerLogin extends HttpServlet{
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException, ServletException
	{
		String accountnumber=req.getParameter("accountnumber");
		String pin=req.getParameter("pin");
		
		Long accno=Long.parseLong(accountnumber);
		int pin1=Integer.parseInt(pin);
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
	        Connection con=Helper.getConnection();
	        
	        PreparedStatement ps=con.prepareStatement("select* from account where accountnumber=? and pin=?");
	        ps.setLong(1, accno);
	        ps.setInt(2, pin1);
	        ResultSet rs= ps.executeQuery();
	        if (rs.next()) 
	        {
	        	PrintWriter pw=res.getWriter();
	        	pw.println("<h1>Login Successfully!!!</h1>");
	        	
	        	RequestDispatcher rd=req.getRequestDispatcher("customeroptions.html");
	        	rd.include(req, res);
				
			}
	        else
	        {
	        	PrintWriter pw=res.getWriter();
	        	pw.println("<h1 align='center' style ='color:red'>Invalid Credentials</h1>");
	        	
	        	RequestDispatcher rd=req.getRequestDispatcher("newlogin.html");
	        	rd.include(req, res);
				
	        }
	    
		
		}
		catch(ClassNotFoundException e)
		{
		       e.printStackTrace();
	    }
	    catch(SQLException e)
	     {
		    e.printStackTrace();
	     }
	
		
		
		
	}

}
