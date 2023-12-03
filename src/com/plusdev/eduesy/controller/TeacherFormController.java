package com.plusdev.eduesy.controller;

import com.plusdev.eduesy.db.Database;
import com.plusdev.eduesy.model.Teacher;
import com.plusdev.eduesy.view.tm.TeacherTM;
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
import java.util.Optional;

public class TeacherFormController {

    public AnchorPane teacherManagementContext;
    public TextField txtTeacherID;
    public TextField txtTeacherAddress;
    public TextField txtTeacherContact;
    public TextField txtTeacherName;
    public TableView<TeacherTM> tblTeacher;
    public TableColumn colTeacherId;
    public TableColumn colTeacherName;
    public TableColumn colTeacherAddress;
    public TableColumn colTeacherContact;
    public TableColumn colOption;
    public TextField txtsearchTeacher;
    public Button btnSaveTeacher;
    private String searchValue ="";

    public void initialize(){
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeacherAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTeacherContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setTeacherId();
        setDataToTable(searchValue);

        tblTeacher.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null != newValue){
                setadata(newValue);
            }
        });

        txtsearchTeacher.textProperty().addListener((observable, oldValue, newValue) -> {
            searchValue=newValue;
            setDataToTable(searchValue);
        });
    }

    private void setadata(TeacherTM newValue) {
        txtTeacherName.setText(newValue.getName());
        txtTeacherContact.setText(newValue.getContact());
        txtTeacherAddress.setText(newValue.getAddress());
        txtTeacherID.setText(newValue.getCode());
        btnSaveTeacher.setText("Update Teacher");
    }

    public void newTeacherOnAction(ActionEvent actionEvent) {
        clear();
        btnSaveTeacher.setText("Save Teacher");
        setTeacherId();
    }

    public void setTeacherId(){
        if(!Database.teacherTable.isEmpty()){
            Teacher lastTeacher = Database.teacherTable.
                    get(Database.teacherTable.size() - 1);
            String code = lastTeacher.getCode();
            String[] splitArray = code.split("-");
            String lastTeacherStringId = splitArray[1];
            int lastTeacherIntegerId = Integer.parseInt(lastTeacherStringId);
            lastTeacherIntegerId++;
            String newIdValue = "T-"+lastTeacherIntegerId;
            txtTeacherID.setText(newIdValue);
        }else{
            txtTeacherID.setText("T-1");
        }
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");

    }


    public void saveTeacherOnAction(ActionEvent actionEvent) {
        if(btnSaveTeacher.getText().equalsIgnoreCase("Save Teacher")){
            Database.teacherTable.add(
                    new Teacher(
                            txtTeacherID.getText(),
                            txtTeacherName.getText(),
                            txtTeacherAddress.getText(),
                            txtTeacherContact.getText()
                    )
            );
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully added!").show();
            setDataToTable(searchValue);
            clear();
            setTeacherId();
        }else{
            for (Teacher th :
                    Database.teacherTable) {
                if (th.getCode().equals(txtTeacherID.getText())) {
                    txtTeacherName.setText(th.getName());
                    txtTeacherContact.setText(th.getContact());
                    txtTeacherAddress.setText(th.getAddress());

                    setTeacherId();
                    setDataToTable(searchValue);
                    clear();
                    return;
                }
            }
        }

    }
    public void setDataToTable(String searchValue){
        ObservableList<TeacherTM> obList = FXCollections.observableArrayList();
        for (Teacher th :
                Database.teacherTable) {
            if(th.getName().contains(searchValue)){
                Button btn = new Button("Delete");

                obList.add(
                        new TeacherTM(
                                th.getCode(),
                                th.getName(),
                                th.getAddress(),
                                th.getContact(),
                                btn
                        )
                );
                btn.setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "are you sure?",ButtonType.YES,ButtonType.NO);

                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if(buttonType.get().equals(ButtonType.YES)){
                        Database.teacherTable.remove(th);
                        new Alert(Alert.AlertType.INFORMATION,"Deleted!").show();
                        setDataToTable(searchValue);
                    }
                });
            }

        }
        tblTeacher.setItems(obList);
    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) teacherManagementContext.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
    public void clear(){
        txtTeacherID.clear();
        txtTeacherAddress.clear();
        txtTeacherName.clear();
        txtTeacherContact.clear();
    }
}
