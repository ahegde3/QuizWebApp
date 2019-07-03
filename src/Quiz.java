

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Quiz
 */
@WebServlet("/Quiz")
public class Quiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ans=request.getQueryString();
		
	//	System.out.println(ans);
		
			                                           
		try{Connection conn=Database.get();
		Statement smt=conn.createStatement();
		String sql="select * from question";
			HttpSession sesi=request.getSession(false);
			if(sesi!=null && ans!=null ) { if(ans.charAt(4)==sesi.getAttribute("ans").toString().charAt(0)) 
			System.out.println("correct answer" );
			ResultSet rs=smt.executeQuery("select * from question where id="+sesi.getAttribute("id").toString());
			if(rs.next()){
				int id=rs.getInt(1);
				String ques=rs.getString(2),optiona= rs.getString(3),optionb=rs.getString(4),optionc=rs.getString(5),optiond=rs.getString(6),answer=rs.getString(7);
			smt.executeUpdate("delete from question where id="+rs.getString(1));
	        PreparedStatement pstmt = conn.prepareStatement("insert into question values(?,?,?,?,?,?,?)");
	        pstmt.setInt(1,id);
	        pstmt.setString(2,ques );
	        pstmt.setString(3,optiona);
	        pstmt.setString(4,optionb );
	        pstmt.setString(5,optionc );
	        pstmt.setString(6,optiond );
	        pstmt.setString(7,answer);
	        pstmt.executeUpdate();
	        System.out.println("inserted");}
			
			
			}
		
		ResultSet rs=smt.executeQuery(sql);
		if(rs.next()){
		PrintWriter out=response.getWriter();
		out.write("<strong>"+rs.getString(2)+"</strong><br>");
		out.write("<ol type='a'><li id='a'onclick='next(this)'>"+rs.getString(3)+"</li>");
		out.write("<li id='b'onclick='next(this)'>"+rs.getString(4)+"</li>");
		out.write("<li id='c'onclick='next(this)'>"+rs.getString(5)+"</li>");
		out.write("<li id='d'onclick='next(this)'>"+rs.getString(6)+"</li></ol>");
		HttpSession ses=request.getSession();
		ses.setAttribute("id", rs.getString(1));
		ses.setAttribute("ans", rs.getString(7));
        
       // smt.executeQuery("insert into question values("+rs.getString(1)+",'"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','" +rs.getString(5)+"','" +rs.getString(6)+"','" +rs.getString(7)+")");
		
		out.close();}
		rs.close();
		  
		} catch(Exception e){e.printStackTrace();}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
