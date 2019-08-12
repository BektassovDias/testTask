import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class addContact extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //соединение с бд с последующим добавлением данных
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook","root", "lbfc");
            //Statement stmt = conn.createStatement();
            String sql ="insert into contacts(name,phone) values (?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pw.println("<html>");
            pw.println("<table id='info-table' border='1'>");
            pw.println("<thead>");
            pw.println("<tr>");
            pw.println("<th>");
            pst.setString(1, name);
            pw.println("</th>");
            pw.println("<th>");
            pst.setString(2, phone);
            pw.println("</th>");
            pw.println("</tr>");
            pw.println("</thead>");
            pst.executeUpdate();
            pst.close();
            try {
                if (conn != null) conn.close();
            }
            catch (SQLException ignored){
                pw.println(ignored);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("error");

        }
        response.sendRedirect("/viewcontact");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<form method='post' action='/addcontact' >");
        pw.println("<input type='text' name='name' placeholder='name'>");
        pw.println("<input type='text' name='phone' placeholder='phone'>");
        pw.println("<input type='submit' name='button' value=' SEND '> ");
        pw.println("</form>");
        pw.println("<a href=\"viewcontact\">Go back</a>");
        pw.println("</html>");
    }
    //эта старница по адресу http://localhost:8080/addcontact
}
