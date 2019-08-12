import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class viewContact extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //бд MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook","root", "lbfc");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from contacts");
            pw.println("<html><table id='info-table' onkeyup='tableSearch()' border='1'>");
            pw.println("<thead><tr>");
            pw.println("<th>" + "Id"+"</th>");
            pw.println("<th>" + "Name"+"</th>");
            pw.println("<th>" + "Phone"+"</th></tr>");
            while(rs.next())
            {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");


                pw.println("<tr><td>"+ id +"</td>" + "<td>" + name+"</td>" + "<td>"+ phone+"</td> </tr>");

            }
            pw.println("</thead>");
                stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("error");
        }
        pw.println("<script type='text/javascript'>");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/javascript/functions.js");
        dispatcher.include(request, response);
        pw.println("</script>");
        pw.println("<a href=\"addcontact\">Add phones</a> <br>");
        pw.println("<input type='text'id='search-text' onkeyup='tableSearch()' placeholder='search'>");

//эта старница по адресу http://localhost:8080/viewcontact
    }
}