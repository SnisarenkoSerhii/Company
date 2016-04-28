package businesslogic;

import java.util.Collection;

/**
 * Created by Sergey on 19.04.2016.
 */
public class UserForm {
    private int id;
    private String gender;
    private String name;
    private String secondName;
    private Integer age;
    private String info;
    private String userLogin;
    private String userPassword;
    private Collection users;

    public void initFormUser(User st){
        this.id = st.getId();
        this.gender = st.getGender();
        this.name = st.getName();
        this.secondName = st.getSecondName();
        this.age = st.getAge();
        this.info = st.getInfo();
        this.userLogin = st.getUserLogin();
        this.userPassword = st.getUserPassword();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Collection getUsers() {
        return users;
    }

    public void setUsers(Collection users) {
        this.users = users;
    }
}
