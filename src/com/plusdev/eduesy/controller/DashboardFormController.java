package com.plusdev.eduesy.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashboardFormController {

    public AnchorPane contextDashBoard;
    public Label lblDate;
    public Label lblTime;


    public void initialize(){
        setData();
    }

    private void setData() {
        /*Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = simpleDateFormat.format(date);

        lblDate.setText(newDate);*/

        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e->{
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
                            lblTime.setText(LocalTime.now().format(dateTimeFormatter));
                        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    public void studentOnAction(ActionEvent actionEvent) throws IOException {

        setUi("StudentForm");
    }

    public void teacherOnAction(ActionEvent actionEvent) throws IOException {
        setUi("TeacherForm");
    }

    public void programsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ProgramForm");
    }
    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) contextDashBoard.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void intakeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("IntakeForm");
    }

    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RegistrationForm");
    }
}
