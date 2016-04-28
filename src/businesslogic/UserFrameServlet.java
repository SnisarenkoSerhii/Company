package businesslogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;

/**
 * Created by Sergey on 19.04.2016.
 */
public class UserFrameServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Установка кодировки для принятия параметров
        req.setCharacterEncoding("UTF-8");
        String sId = req.getParameter("id");
        // Если пользователь нажал кнопку ОК – тогда мы обновляем данные (добавляем нового студента)
        if (sId != null && req.getParameter("OK") != null) {
            try {
                // Если ID студента больше 0, то мы редактируем его данные
                if (Integer.parseInt(sId) > 0) {
                    updateUser(req);
                } // Иначе это новый студент
                else {
                    insertUser(req);
                }
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
        }
        // А теперь опять получаем данные для отображения на главной форме
        String gs = req.getParameter("Id");
//        int groupId = -1;
//        if (gs != null) {
//            groupId = Integer.parseInt(gs);
//        }
        MainFrameForm form = new MainFrameForm();
        try {
            Collection user = Logic.getInstance().getUsers();
            User g = new User();
            Collection users = Logic.getInstance().getUsers();
            form.getUsers();
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void updateUser(HttpServletRequest req) throws SQLException, ParseException {
        User s = prepareUser(req);
        Logic.getInstance().updateUser(s);
    }

    private void insertUser(HttpServletRequest req) throws SQLException, ParseException {
        User s = prepareUser(req);
        Logic.getInstance().insertUser(s);
    }

    private User prepareUser(HttpServletRequest req) throws ParseException {
        User s = new User();
        s.setId(Integer.parseInt(req.getParameter("id")));
        s.setGender(req.getParameter("Gender").trim());
        s.setName(req.getParameter("Name").trim());
        s.setSecondName(req.getParameter("SecondName").trim());
        s.setAge(Integer.parseInt(req.getParameter("Age").trim()));
        s.setInfo(req.getParameter("Info").trim());
        return s;
    }
}

