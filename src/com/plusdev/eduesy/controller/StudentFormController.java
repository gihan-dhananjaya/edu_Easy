package com.plusdev.eduesy.controller;

import com.plusdev.eduesy.db.Database;
import com.plusdev.eduesy.model.Student;
import com.plusdev.eduesy.view.tm.StudentTM;
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
import java.util.Optional;

public class StudentFormController {

    public AnchorPane studentContext;
    public TextField txtSearch;
    public TextField txtAddress;
    public DatePicker txtDOB;
    public TextField txtName;
    public TextField txtID;
    public TableView<StudentTM> tblStudent;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colDob;
    public TableColumn colAddress;
    public TableColumn colOption;
    public Button btn;
    String searchText = "";

    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        setStudentId();
        setDataToTable(searchText);

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null != newValue){
                setData(newValue);
            }

        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setDataToTable(searchText);
        });
    }

    private void setData(StudentTM newValue) {
        txtID.setText(newValue.getStudentId());
        txtName.setText(newValue.getFullName());
        txtDOB.setValue(LocalDate.parse(newValue.getDob(),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        txtAddress.setText(newValue.getAddress());
        btn.setText("Update Student");
    }

    private void setDataToTable(String searchValue) {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        for (Student st:
             Database.studentTable) {
            if(st.getFullName().contains(searchValue)){
                Button btn = new Button("Delete");
                obList.add(
                        new StudentTM(
                                st.getStudentId(),
                                st.getFullName(),
                                st.getDateOfBirth(),
                                st.getAddress(),
                                btn
                        )
                );
                btn.setOnAction(e->{
                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,"Are you sure?",
                            ButtonType.YES,ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if(buttonType.get().equals(ButtonType.YES)){

                        Database.studentTable.remove(st);
                        new Alert(Alert.AlertType.INFORMATION,"Deleted!").show();
                        setDataToTable(searchValue);
                        setStudentId();
                    }
                });
            }
        }

        tblStudent.setItems(obList);
    }

    private void setStudentId() {
        if(!Database.studentTable.isEmpty()){
            Student lastStudent  = Database.studentTable.get(
                    Database.studentTable.size()-1
            );
            String lastStudentId = lastStudent.getStudentId();
            String[] arr = lastStudentId.split("-");
            String lastIntegerNumberAsString = arr[1];
            int lastIdInteger = Integer.parseInt(lastIntegerNumberAsString);
            lastIdInteger++;
            String newIntegerasString = "S-"+lastIdInteger;
            txtID.setText(newIntegerasString);
        }else{
            txtID.setText("S-1");
        }
    }

    public void backtoHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void setUi(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) studentContext.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void saveOnAction(ActionEvent actionEvent) {
        if(btn.getText().equalsIgnoreCase("Save Student")){

            String id = txtID.getText();
            String fullName = txtName.getText();
            String dob = txtDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String address = txtAddress.getText();

            Database.studentTable.add(
                    new Student(id,fullName,dob,address)
            );
            setStudentId();
            clear();
            setDataToTable(searchText);
            new Alert(Alert.AlertType.INFORMATION,"Successfully added!").show();
        }else{
            for (Student st:Database.studentTable
                 ) {
                if(st.getStudentId().equals(txtID.getText())){

                    st.setFullName(txtName.getText());
                    st.setAddress(txtAddress.getText());
                    st.setDateOfBirth(txtDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                    setStudentId();
                    clear();
                    setDataToTable(searchText);
                    return;
                }
            }
            new Alert(Alert.AlertType.WARNING, "Student not found!");
        }

    }

    public void newStudentOnAction(ActionEvent actionEvent) {

        clear();
        setStudentId();
        btn.setText("Save Student");
    }
    public void clear(){
        txtName.clear();
        txtAddress.clear();
        txtDOB.setValue(null);
    }
}
