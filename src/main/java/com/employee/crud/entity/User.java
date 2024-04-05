package com.employee.crud.entity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name = "user_id",length=40)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(name="UserName",length = 255)
    private String UserName;

    @Column(name = "email",length = 255)
    private String email;

    @Column(name="password",length = 255)
    private String password;



    public User(int user_id, String UserName, String email, String password){
        this.user_id = user_id;
        this.UserName = UserName;
        this.email = email;
        this.password = password;
    }

    public User(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
