package com.plusdev.eduesy.view.tm;

import javafx.scene.control.Button;

public class StudentTM {
    private String studentId;
    private String fullName;
    private String dob;
    private String address;
    private Button button;

    public StudentTM() {

    }

    public StudentTM(String studentId, String fullName, String dob, String address, Button button) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.button = button;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "studentId='" + studentId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", button=" + button +
                '}';
    }
}
