package com.plusdev.eduesy.controller;

import com.plusdev.eduesy.db.Database;
import com.plusdev.eduesy.model.User;
import com.plusdev.eduesy.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupFormController {
    public Button signupContext;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtFirstName;
    public TextField txtLastName;

    public void haveAccountOnAction(ActionEvent actionEvent) throws IOException {
       setUi("LoginForm");
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {

        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        String email = txtEmail.getText();
        String password = new PasswordManager().encode(txtPassword.getText().trim());

        Database.userTable.add(
                new User(fName,lName,email,password)
        );
        new Alert(Alert.AlertType.INFORMATION,"Welcome!").show();

        setUi("LoginForm");

    }
    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) signupContext.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
}
