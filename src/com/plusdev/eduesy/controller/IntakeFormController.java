package com.plusdev.eduesy.controller;

import com.plusdev.eduesy.db.Database;
import com.plusdev.eduesy.model.Intake;
import com.plusdev.eduesy.model.Program;
import com.plusdev.eduesy.view.tm.IntakeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class IntakeFormController {

    public AnchorPane context;
    public TextField txtIntakeID;
    public DatePicker dpStartDate;
    public TextField txtIntakeName;
    public ComboBox<String> cmbProgram;
    public TextField txtSearchHere;
    public TableView<IntakeTM> tblIntake;
    public TableColumn colID;
    public TableColumn colStartDate;
    public TableColumn colName;
    public TableColumn colProgram;
    public TableColumn colCompleteState;
    public TableColumn colOption;
    private String searchValue="";

    public ArrayList<String> programList = new ArrayList<>();
    public Button btnSaveIntake;

    public void initialize(){

        colID.setCellValueFactory(new PropertyValueFactory<>("intakeId"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colName.setCellValueFactory(new PropertyValueFactory<>("intakeName"));
        colCompleteState.setCellValueFactory(new PropertyValueFactory<>("intakeCompleteness"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        loadProgramIntoCombo();
        setIntakeId();
        loadDataIntoTb(searchValue);

        tblIntake.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null != newValue){
                setBackFields(newValue);
            }
        });

        txtSearchHere.textProperty().addListener((observable, oldValue, newValue) -> {
            searchValue=newValue;
            loadDataIntoTb(searchValue);
        });
    }

    private void setBackFields(IntakeTM newValue) {
        txtIntakeID.setText(newValue.getIntakeId());
        txtIntakeName.setText(newValue.getIntakeName());
        dpStartDate.setValue(LocalDate.parse(newValue.getStartDate()));
        btnSaveIntake.setText("Update Intake");
    }


    private void setIntakeId() {
        if(!Database.intakeTable.isEmpty()){
            for (Intake ink :
                    Database.intakeTable) {
                Intake lastIntake = Database.intakeTable.get(Database.intakeTable.size() - 1);
                String lastIntakeId = lastIntake.getIntakeId();
                String[] split = lastIntakeId.split("-");
                String numberAsString = split[1];
                int numberAsInt = Integer.parseInt(numberAsString);
                numberAsInt++;
                String newId = "I-"+numberAsInt;
                txtIntakeID.setText(newId);
            }
        }else{
            txtIntakeID.setText("I-1");
        }

    }

    private void loadProgramIntoCombo() {

        for (Program pr :
                Database.programTable) {
            programList.add(pr.getProgramCode()+". "+pr.getName());
        }
        ObservableList<String> obList = FXCollections.observableArrayList(programList);
        cmbProgram.setItems(obList);
    }

    public void newIntakeOnAction(ActionEvent actionEvent) {
        clear();
        setIntakeId();
        txtIntakeID.setText("Save Intake");
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void saveIntakeOnAction(ActionEvent actionEvent) {
        if(btnSaveIntake.getText().equalsIgnoreCase("Save Intake")){
            LocalDate start = dpStartDate.getValue();
            LocalDate now = LocalDate.now();
            long diffInDays = ChronoUnit.DAYS.between(start, now);
            Database.intakeTable.add(
                    new Intake(
                            txtIntakeID.getText(),
                            dpStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            txtIntakeName.getText(),
                            diffInDays > 0,
                            cmbProgram.getValue().split("\\.")[1]
                    )
            );
            new Alert(Alert.AlertType.INFORMATION,"Saved!").show();
            clear();
            setIntakeId();
            loadDataIntoTb(searchValue);
        }else{
            for (Intake ink :
                    Database.intakeTable) {
                if (ink.getIntakeId().equals(txtIntakeID.getText())) {
                    txtIntakeName.setText(ink.getIntakeName());
                    dpStartDate.setValue(LocalDate.parse(ink.getStartDate()));
                    clear();
                    setIntakeId();
                    loadDataIntoTb(searchValue);
                    return;
                }
            }

        }

    }

    private void loadDataIntoTb(String searchValue) {
        ObservableList<IntakeTM> obList = FXCollections.observableArrayList();
        for (Intake ink :
                Database.intakeTable) {
            Button btn = new Button("Delete");
            if(ink.getIntakeName().contains(searchValue)){
                obList.add(new IntakeTM(
                        ink.getIntakeId(),
                        ink.getStartDate(),
                        ink.getIntakeName(),
                        ink.isIntakeCompleteness() ?"Complete":"On going",
                        ink.getProgramId(),
                        btn

                ));
                tblIntake.setItems(obList);
            }


        }
    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
    public void clear(){
        txtIntakeName.clear();
        dpStartDate.setValue(null);
        btnSaveIntake.setText("Save Intake");
    }
}
