package com.plusdev.eduesy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CodeVerificationFormController {

    public AnchorPane context;
    public Label txtSampleEmail;
    public TextField txtVerifyCode;

    String code="";
    String selectedEmail="";
    public void setUserData(String verificationCode, String email){
        System.out.println(verificationCode);
       txtSampleEmail.setText(email);
       code=verificationCode;
       selectedEmail=email;
    }
    public void changeEmailOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ForgotPasswordForm");
    }

    public void verifyCodeOnAction(ActionEvent actionEvent) throws IOException {
        if(code.equals(txtVerifyCode.getText())){

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ResetPasswordForm.fxml"));
            Parent parent = fxmlLoader.load();
            ResetPasswordFormController controller = fxmlLoader.getController();
            controller.setUserData(selectedEmail);
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));
        }else{
            new Alert(Alert.AlertType.WARNING,"Wrong Verification code!");
        }
    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
}
