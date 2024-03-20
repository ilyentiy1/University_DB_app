package com.example.myapp;
import javafx.collections.ObservableList;
import java.sql.*;

public class dbHandler extends configs{
    Connection dbConnection;
    ResultSet rs = null;
    public Connection getDbConnnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }

    //методы для считывания и записи в базу данных
    public void insertStudent(Student student, ObservableList<sgroup> list) {
        String insert = "INSERT INTO student(sgroupfk, fio, email, login, password) " +
                "VALUES (?, ?, ?, ?, ?)";
        String groupfk = null;
        int groupNum = Integer.parseInt(student.groupNumber);
        for(sgroup i : list) {
            if(i.getGroupNumber() == groupNum) {
                groupfk = i.getGrouppk();
            };
        }
        try {
            PreparedStatement prST = getDbConnnection().prepareStatement(insert);
            prST.setString(1, groupfk);
            prST.setString(2, student.getFio());
            prST.setString(3, student.getEmail());
            prST.setString(4, student.getLogin());
            prST.setString(5, student.getPassword());
            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insertTeacher(Teacher teacher, ObservableList<Department> list) {
        String depfk = null;
        for(Department i : list) {
            if(i.getName().equals(teacher.getDepartment())) {
                depfk = i.getDeppk();
            }
        }
        String insert = "INSERT INTO teacher(departmentfk, fio, post, degree, email, login, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prST = getDbConnnection().prepareStatement(insert);
            prST.setString(1, depfk);
            prST.setString(2, teacher.getFIO());
            prST.setString(3, teacher.getPost());
            prST.setString(4, teacher.getDegree());
            prST.setString(5, teacher.getEmail());
            prST.setString(6, teacher.getLogin());
            prST.setString(7, teacher.getPassword());
            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getItem(String command) {
        ResultSet resSet = null;
        try {
            PreparedStatement prST = getDbConnnection().prepareStatement(command);
            resSet = prST.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    //метод для авторизации - получение данных из
    public ResultSet getUser(User user, String type) {
        ResultSet resSet = null;
        String select;
        if(type.equals("Студент")) {
            select = "SELECT * FROM " + Const.STUDENT_TABLE + " WHERE " +
                    Const.STUDENT_LOGIN + "=? AND " + Const.STUDENT_PASSWORD + "=?";
        } else{
            select = "SELECT * FROM " + Const.TEACHER_TABLE + " WHERE " +
                    Const.TEACHER_LOGIN + "=? AND " + Const.TEACHER_PASSWORD + "=?";
        }
        try {
            PreparedStatement prST = getDbConnnection().prepareStatement(select);
            prST.setString(1, user.getLogin());
            prST.setString(2, user.getPassword());
            resSet = prST.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void editCell(String command) {
        try {
            PreparedStatement prST = getDbConnnection().prepareStatement(command);
            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
