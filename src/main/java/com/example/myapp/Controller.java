package com.example.myapp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.myapp.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
public class Controller {
    public static Student student = new Student();
    public static Teacher teacher = new Teacher();

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> userType;



    @FXML
    void initialize() {

        userType.getItems().removeAll(userType.getItems());
        userType.getItems().addAll("Преподаватель", "Студент", "Администратор");
        loginButton.setOnAction(actionEvent -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            if(!loginText.equals("")&&!loginPassword.equals("")) {
                try {
                    loginUser(loginText, loginPassword, userType.getValue());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                Shake shake = new Shake(loginButton);
                shake.playAnim();
            }
        });
    }


    private void loginUser(String loginText, String loginPassword, String type) throws SQLException {
        dbHandler handler = new dbHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = handler.getUser(user, type);

        int counter = 0;
        try {
            while(result.next()) {
                counter++;
                if(userType.getValue().equals("Студент")) {
                    student.setStudentpk(result.getString(1));
                    student.setSgroupfk(result.getString(2));
                }
                if(userType.getValue().equals("Преподаватель")) {
                    teacher.setTeacherpk(result.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter >= 1 && userType.getValue().equals("Студент")) { //здесь добавить получение значения из комбобокса

            openNewScene("studentPage.fxml");
        }
        else if(counter >=1 && userType.getValue().equals("Преподаватель")) {
            openNewScene("teacherPage.fxml");
        }
        else if(loginText.equals("root") && loginPassword.equals("1812ayka")) {
            openNewScene("adminPage.fxml");
        }
        else {
            Shake shake = new Shake(loginButton);
            shake.playAnim();
        }
    }

    public void openNewScene(String window) {
        loginButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
