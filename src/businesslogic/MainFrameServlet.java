package businesslogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Sergey on 19.04.2016.
 */
public class MainFrameServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // ”становка кодировки дл€ прин€ти€ параметров
        req.setCharacterEncoding("UTF-8");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
        if (answer == 1) {
            // “ут надо сделать вызов другой формы, котора€ перенаправит сервлет
            // на другую JSP дл€ ввода данных о новом студенте
            try {
                User s = new User();
                s.setId(0);
                s.setName("Name");
                Collection users = Logic.getInstance().getUsers();
                UserForm sForm = new UserForm();
                sForm.initFormUser(s);
                sForm.setUsers(users);
                req.setAttribute("user", sForm);
                getServletContext().getRequestDispatcher("/UserFrame.jsp").forward(req, resp);
                return;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        if (answer == 2) {
            // “ут надо сделать вызов другой формы, котора€ перенаправит сервлет
            // на другую JSP дл€ ввода данных о студенте
            try {
                if (req.getParameter("Id") != null) {
                    int stId = Integer.parseInt(req.getParameter("id"));
                    User s = Logic.getInstance().getUserById(stId);
                    Collection users = Logic.getInstance().getUsers();
                    UserForm sForm = new UserForm();
                    sForm.initFormUser(s);
                    sForm.setUsers(users);
                    req.setAttribute("user", sForm);
                    getServletContext().getRequestDispatcher("/UserFrame.jsp").forward(req, resp);
                    return;
                }
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        MainFrameForm form = new MainFrameForm();
//        try {
//            Collection user = Logic.getInstance().getUsers();
//            User g = new User();
//            Collection users = Logic.getInstance().getUsers();
//            form.getUsers();
//        } catch (SQLException sql_e) {
//            throw new IOException(sql_e.getMessage());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    // «десь мы провер€м какое действие нам надо сделать Ц и возвращаем ответ
    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("Add") != null) {
            return 1;
        }
        if (req.getParameter("Edit") != null) {
            return 2;
        }
        if (req.getParameter("Delete") != null) {
            if (req.getParameter("Id") != null) {
                User s = new User();
                s.setId(Integer.parseInt(req.getParameter("id")));
                Logic.getInstance().deleteUser(s);
            }
            return 0;
        }
        return 0;
    }

    // ѕереопределим стандартные методы
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


}
