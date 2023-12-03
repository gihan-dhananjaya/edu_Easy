package com.plusdev.eduesy.controller;

import com.plusdev.eduesy.db.Database;
import com.plusdev.eduesy.model.Intake;
import com.plusdev.eduesy.model.Program;
import com.plusdev.eduesy.model.Registration;
import com.plusdev.eduesy.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class RegistrationFormController {

    public AnchorPane context;
    public TextField txtRegId;
    public ComboBox<String> cmbPrograms;
    public RadioButton rbtnPayed;
    public TextField txtSearchStudent;
    private String searchValue="";
    public static ArrayList<String> programList = new ArrayList<>();

    private AutoCompletionBinding<String> autoCompletionBinding;
    public static ArrayList<String> arrayList = new ArrayList<>();

    public void initialize(){
        loadProgramIntoCombo();
        setRegId();

        for (Student student:
                Database.studentTable) {
            arrayList.add(student.getFullName());
        }
        TextFields.bindAutoCompletion(txtSearchStudent,arrayList);

    }
    private void setRegId() {
        if(!Database.registrationsTable.isEmpty()){
            for (Registration reg :
                    Database.registrationsTable) {
                Registration lastReg = Database.registrationsTable.get(Database.registrationsTable.size() - 1);
                String lastRegId = lastReg.getRegId();
                String[] split = lastRegId.split("-");
                String numberAsString = split[1];
                int numberAsInt = Integer.parseInt(numberAsString);
                numberAsInt++;
                String newId = "R-"+numberAsInt;
                txtRegId.setText(newId);
            }
        }else{
            txtRegId.setText("R-1");
        }
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

    public void registerOnAction(ActionEvent actionEvent) {
        Registration registration = new Registration(
                txtRegId.getText(),
                new Date(),
                txtSearchStudent.getText(),
                cmbPrograms.getValue(),
                rbtnPayed.isSelected()
        );
        Database.registrationsTable.add(registration);
        new Alert(Alert.AlertType.INFORMATION,"Registration Success! ").show();
        txtSearchStudent.clear();
        cmbPrograms.setItems(null);
        setRegistrationId();
    }
    private void loadProgramIntoCombo() {

        for (Program pr :
                Database.programTable) {
            programList.add(pr.getProgramCode()+". "+pr.getName());
        }
        ObservableList<String> obList = FXCollections.observableArrayList(programList);
        cmbPrograms.setItems(obList);
    }

    private void setRegistrationId() {
        if(!Database.registrationsTable.isEmpty()){
            for (Registration reg :
                    Database.registrationsTable) {
                Registration lastReg = Database.registrationsTable.get(Database.registrationsTable.size() - 1);
                String lastRegId = lastReg.getRegId();
                String[] split = lastRegId.split("-");
                String numberAsString = split[1];
                int numberAsInt = Integer.parseInt(numberAsString);
                numberAsInt++;
                String newId = "R-"+numberAsInt;
                txtRegId.setText(newId);
            }
        }else{
            txtRegId.setText("R-1");
        }

    }
}
