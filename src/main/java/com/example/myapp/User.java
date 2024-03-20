package com.example.myapp;

public class User {
    private String fio;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) { this.fio = fio; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    private String email;
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() { }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
