package com.plusdev.eduesy.controller;

import com.plusdev.eduesy.db.Database;
import com.plusdev.eduesy.model.Program;
import com.plusdev.eduesy.model.Teacher;
import com.plusdev.eduesy.view.tm.ProgramTM;
import com.plusdev.eduesy.view.tm.TeacherTM;
import com.plusdev.eduesy.view.tm.TechAddTM;
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
import java.util.ArrayList;
import java.util.Optional;

public class ProgramFormController {

    public TextField txtProgramID;
    public TextField txtCost;
    public TextField txtProgramName;
    public Button btnSaveProgram;
    public TextField txtSearchProgram;
    public ComboBox<String> combTeacher;
    public TableView<ProgramTM> tblProgram;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colCost;
    public TableColumn colTechnologies;
    public TableColumn colTeacher;
    public TableColumn colOption;
    public TableView<TechAddTM> tblTech;
    public TableColumn colTechId;
    public TableColumn colTech;
    public TableColumn colTechRemove;
    public TextField txtTechnology;
    public AnchorPane context;

    ArrayList<String> teacherArray = new ArrayList<>();

    public void initialize(){
        setProgramId();
        setTeachers();
        addDataToTable();

        colTechId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colTechRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colTech.setCellValueFactory(new PropertyValueFactory<>("name"));

        colCode.setCellValueFactory(new PropertyValueFactory<>("programCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTechnologies.setCellValueFactory(new PropertyValueFactory<>("btnTech"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null != newValue){
                backDataIntoField(newValue);
            }
        });
    }

    private void backDataIntoField(ProgramTM newValue) {
        txtProgramID.setText(newValue.getProgramCode());
        txtProgramName.setText(newValue.getName());
        txtCost.setText(String.valueOf(newValue.getCost()));
        btnSaveProgram.setText("Update Program");
    }

    private void setTeachers() {
        for (Teacher t :
                Database.teacherTable) {
            teacherArray.add(t.getCode()+". "+t.getName());
        }
        ObservableList<String> obList = FXCollections.observableArrayList(teacherArray);
        combTeacher.setItems(obList);
    }

    private void setProgramId() {
        if(!Database.programTable.isEmpty()){
            Program lastProgram = Database.programTable.
                    get(Database.programTable.size() - 1);
            String code = lastProgram.getProgramCode();
            String[] splitArray = code.split("-");
            String lastProgramStringId = splitArray[1];
            int lastProgramIntegerId = Integer.parseInt(lastProgramStringId);
            lastProgramIntegerId++;
            String newIdValue = "P-"+lastProgramIntegerId;
            txtProgramID.setText(newIdValue);
        }else{
            txtProgramID.setText("P-1");
        }
    }

    public void newProgramOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    ObservableList<TechAddTM> tmList = FXCollections.observableArrayList();
    public void addTechOnAction(ActionEvent actionEvent) {
        if(!isExist(txtTechnology.getText().trim())){

            Button btn = new Button("Remove");

            TechAddTM tm = new TechAddTM(
                    tmList.size()+1, txtTechnology.getText().trim(), btn);

            tmList.add(tm);
            tblTech.setItems(tmList);
            txtTechnology.clear();

            btn.setOnAction(e->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure?",ButtonType.YES,ButtonType.NO);

                Optional<ButtonType> buttonType = alert.showAndWait();

                if(buttonType.get().equals(ButtonType.YES)){
                    tmList.remove(tm);
                    new Alert(Alert.AlertType.INFORMATION,"Deleted!").show();
                    tblTech.setItems(tmList);
                }
            });
        }else{
            txtTechnology.selectAll();
            new Alert(Alert.AlertType.WARNING,"Technology already exist!").show();
        }
    }
    private boolean isExist(String tech){
        for (TechAddTM tm :
                tmList) {
            if (tm.getName().equals(tech)) {
                return true;
            }
        }
        return false;
    }
    public void saveProgramOnAction(ActionEvent actionEvent) {
        String[] selectedTech = new String[tmList.size()];

        int pointer = 0;
        for (TechAddTM tm :
                tmList) {
            selectedTech[pointer] = tm.getName();
            pointer++;
        }
        if(btnSaveProgram.getText().equalsIgnoreCase("Save Program")){
            Program program = new Program(
                    txtProgramID.getText(),
                    txtProgramName.getText(),
                    selectedTech,
                    Double.parseDouble(txtCost.getText()),
                    combTeacher.getValue().split("\\.")[1]

            );
            Database.programTable.add(program);
            new Alert(Alert.AlertType.INFORMATION,"Successful added!").show();
            addDataToTable();
            setProgramId();
            clear();
        }else {
            for (Program ptm :
                    Database.programTable) {
                if(txtProgramID.getText().equals(ptm.getProgramCode())){
                    txtCost.setText(String.valueOf(ptm.getCost()));
                    txtProgramName.setText(ptm.getName());

                    setProgramId();
                    addDataToTable();
                    return;
                }
            }
        }
    }

    private void addDataToTable(){
        ObservableList<ProgramTM> programObList = FXCollections.observableArrayList();
        for (Program pr :
                Database.programTable) {
            Button btn = new Button("Delete");
            Button btnTech = new Button("Show Tech");
            ProgramTM ptm = new ProgramTM(
                    pr.getProgramCode(),
                    pr.getName(),
                    btnTech,
                    pr.getCost(),
                    pr.getTeacherId(),
                    btn
            );
            programObList.add(ptm);

            btn.setOnAction(e->{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Are you sure?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();

                if(buttonType.get().equals(ButtonType.YES)){
                    programObList.remove(ptm);
                    new Alert(Alert.AlertType.INFORMATION,"Deleted!").show();

                }
            });

        }
        tblProgram.setItems(programObList);
    }
    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
    public void clear(){
        txtCost.clear();
        txtProgramName.clear();
    }
}
