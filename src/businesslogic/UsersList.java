package businesslogic;

import businesslogic.Logic;
import businesslogic.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sergey on 14.04.2016.
 */
public class UsersList extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<B> Users List</B>");
        pw.println("<table border = 1>");
        try {
            List l = Logic.getInstance().getUsers();
            for (Iterator it = l.iterator(); it.hasNext();) {
                User user = (User) it.next();
                pw.println("<tr>");
                pw.println("<td>" + user.getId() + "</td>");
                pw.println("<td>" + user.getGender() + "</td>");
                pw.println("<td>" + user.getName() + "</td>");
                pw.println("<td>" + user.getSecondName() + "</td>");
                pw.println("<td>" + user.getAge() + "</td>");
                pw.println("<td>" + user.getInfo() + "</td>");
                pw.println("</tr >");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pw.println("</table>");
    }
}
