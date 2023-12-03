package com.plusdev.eduesy.controller;

import com.plusdev.eduesy.util.tool.VerificationCodeGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class ForgotPasswordFormController {

    public AnchorPane context;
    public TextField txtEmail;

    public void backToLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void sendVerficationOnAction(ActionEvent actionEvent) {

        try {
            String verificationCode = new VerificationCodeGenerator().getCode(5);

            String fromEmail = "gihanbandaracs@gmail.com";
            String toEmail = txtEmail.getText();
            String host = "127.0.0.1";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host",host);

            Session session = Session.getDefaultInstance(properties);

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
            mimeMessage.setSubject("Verification Code");
            mimeMessage.setText("verification code: "+verificationCode);

            //Transport.send(mimeMessage);

            System.out.println("Verify Your Email!");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CodeVerificationForm.fxml"));
            Parent parent = fxmlLoader.load();
            CodeVerificationFormController controller = fxmlLoader.getController();
            controller.setUserData(verificationCode,txtEmail.getText());
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));

        }catch ( MessagingException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
}
