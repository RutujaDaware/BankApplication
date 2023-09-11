package bankapplication;


	import java.io.IOException;
    import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
    import java.util.Random;
    import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import com.bankapplication.Helper;
	
	@WebServlet("/data")
public class bankapp extends HttpServlet
{
		protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException, ServletException
		{
			String id=req.getParameter("id");
			String name=req.getParameter("name");
			String age=req.getParameter("age");
			String pin=req.getParameter("pin");
			String address=req.getParameter("address");
			
			
			 
			int id1=Integer.parseInt(id);
			int age1=Integer.parseInt(age);
			int pin1=Integer.parseInt(pin);
			
		
			
			Random r=new Random();
			
			long accountnumber=r.nextLong(1000000001);
			
			double balance=50000;
			
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=Helper.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into account(id,name,age,pin,balance,address,accountnumber)"
						+"values(?,?,?,?,?,?,?)");
				
				ps.setInt(1, id1);
				ps.setString(2,name);
				ps.setInt(3, age1);
				ps.setInt(4, pin1);
				ps.setDouble(5, balance);
				ps.setString(6, address);
				ps.setLong(7, accountnumber);
				ps.execute();
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			
		PrintWriter pw=res.getWriter();
		pw.print("<h1> Account Created Succesfully </h1>");
				
		RequestDispatcher rd= req.getRequestDispatcher("newlogin.html");
		rd.include(req, res);
				
		
			
		
	


		}

}
