package businesslogic;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Logic {

    private static Connection con;
    private static DataSource dataSource;
    private static Logic instance;
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/UsersDB";


    static final String USER = "postgres";
    static final String PASS = "XAvier";

    private Logic() {
    }


    public static synchronized Logic getInstance() {
        if (instance == null) {
            try {
                instance = new Logic();
                 Class.forName(JDBC_DRIVER);
                con = DriverManager.getConnection(DB_URL,USER,PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
//        if (instance == null) {
//            try {
//                instance = new Logic();
//                //Class.forName(JDBC_DRIVER);
//
//                Context ctx = new InitialContext();
//                instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/UsersDB");
//                con = DriverManager.getConnection(DB_URL,USER,PASS);
//            } catch (NamingException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return instance;
    }

    public List getUsers() throws SQLException, ClassNotFoundException {
        List users = new ArrayList();
//        Statement stmt = null;
//        Class.forName(JDBC_DRIVER);
//        con = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT \n" +
                "  \"Users\".id, \n" +
                "  \"Users\".\"Gender\", \n" +
                "  \"Users\".\"Name\", \n" +
                "  \"Users\".\"SecondName\", \n" +
                "  \"Users\".\"Age\", \n" +
                "  \"Users\".\"Info\"\n" +
                "FROM \n" +
                "  public.\"Users\";\n");
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setGender(rs.getString(2));
            user.setName(rs.getString(3));
            user.setSecondName(rs.getString(4));
            user.setAge(rs.getInt(5));
            user.setInfo(rs.getString(6));
            users.add(user);
        }
        rs.close();
        stmt.close();
        return users;
    }

    public User getUserById(int id) throws SQLException {
        User user = null;
        PreparedStatement stmt = con.prepareStatement("SELECT \n" +
                "  \"Users\".id, \n" +
                "  \"Users\".\"Gender\", \n" +
                "  \"Users\".\"Name\", \n" +
                "  \"Users\".\"SecondName\", \n" +
                "  \"Users\".\"Age\", \n" +
                "  \"Users\".\"Info\"\n" +
                "FROM \n" +
                "  public.\"Users\"\n" +
                "WHERE \n" +
                "id = ?;");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            user = new User(rs);
        }
        rs.close();
        stmt.close();
        return user;
    }

    public void insertUser(User user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO students "
                + "(firstName, patronymic, surName, sex, dateOfBirth, group_id, educationYear)"
                + "VALUES( ?,  ?,  ?,  ?,  ?,  ?,  ?, ?)");
        stmt.setInt(1, user.getId());
        stmt.setString(2, user.getGender());
        stmt.setString(3, user.getName());
        stmt.setString(4, user.getSecondName());
        stmt.setInt(5, user.getAge());
        stmt.setString(6, user.getInfo());
        stmt.execute();
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE students "
                + "SET firstName=?, patronymic=?, surName=?, sex=?, dateOfBirth=?, group_id=?,"
                + "educationYear=? WHERE student_id=?");
        stmt.setInt(1, user.getId());
        stmt.setString(2, user.getGender());
        stmt.setString(3, user.getName());
        stmt.setString(4, user.getSecondName());
        stmt.setInt(5, user.getAge());
        stmt.setString(6, user.getInfo());
        stmt.execute();
    }

    public void deleteUser(User user) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM students WHERE student_id =  ?");
        stmt.setInt(1, user.getId());
        stmt.execute();
    }


}