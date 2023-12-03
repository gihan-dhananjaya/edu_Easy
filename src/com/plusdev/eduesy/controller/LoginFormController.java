package com.plusdev.eduesy.controller;

import com.plusdev.eduesy.db.Database;
import com.plusdev.eduesy.model.User;
import com.plusdev.eduesy.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginFormController {
    public AnchorPane loginContext;
    public TextField txtEmail;
    public TextField txtPassword;

    public void createAccountOnAction(ActionEvent actionEvent) throws Exception {
        setUi("SignupForm");
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ForgotPasswordForm");
    }

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText().toLowerCase();
        String password = txtPassword.getText().trim();

       /* for(User user : Database.userTable){
            if(user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    System.out.println(user.toString());
                    return;
                }else{
                    new Alert(Alert.AlertType.ERROR, "Wrong Password").show();
                    return;
                }
            }
        }*/


        Optional<User> selectedUser = Database.userTable.stream().filter(e->e.getEmail().equals(email)).findFirst();
        if(selectedUser.isPresent()){
            if(new PasswordManager().checkPassword(password,selectedUser.get().getPassword())){
                setUi("DashboardForm");
            }else{
                new Alert(Alert.AlertType.ERROR, "Wrong Password").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,String.format("user not found! with email(%s)",
                    email)).show();
        }

    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(parent));
        //stage.setFullScreen(true);
    }
}
