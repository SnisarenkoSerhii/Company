package businesslogic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Comparable {
    private int id;
    private String gender;
    private String name;
    private String secondName;
    private Integer age;
    private String info;
    private String userLogin;
    private String userPassword;

    public User(ResultSet rs) throws SQLException{
        setId(rs.getInt(1));
        setGender(rs.getString(2));
        setName(rs.getString(3));
        setSecondName(rs.getString(4));
        setAge(rs.getInt(5));
        setInfo(rs.getString(6));
        setUserLogin(rs.getString(7));
        setUserPassword(rs.getString(8));
    }

    public User(){
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }
}