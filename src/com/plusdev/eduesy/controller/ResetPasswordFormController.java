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

public class ResetPasswordFormController {

    public AnchorPane context;
    public TextField txtPassword;

    String selectedEmail="";
    public void setUserData(String email){
        selectedEmail=email;
    }
    public void changePasswordOnAction(ActionEvent actionEvent) throws IOException {
        Optional<User> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(selectedEmail)).findFirst();

        if(selectedUser.isPresent()){
            selectedUser.get().setPassword(new PasswordManager().encode(txtPassword.getText()));
            setUi("LoginForm");
        }else{
            new Alert(Alert.AlertType.WARNING,"Not Found!").show();
        }
    }
    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
}
