package com.plusdev.eduesy.view.tm;

import javafx.scene.control.Button;

import java.util.Arrays;

public class ProgramTM {
    private String programCode;
    private String name;
    private Button btnTech;
    private double cost;
    private String teacherId;
    private Button btn;

    public ProgramTM() {
    }

    public ProgramTM(String programCode, String name, Button btnTech, double cost, String teacherId, Button btn) {
        this.programCode = programCode;
        this.name = name;
        this.btnTech = btnTech;
        this.cost = cost;
        this.teacherId = teacherId;
        this.btn = btn;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getBtnTech() {
        return btnTech;
    }

    public void setBtnTech(Button btnTech) {
        this.btnTech = btnTech;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ProgramTM{" +
                "programCode='" + programCode + '\'' +
                ", name='" + name + '\'' +
                ", btnTech=" + btnTech +
                ", cost=" + cost +
                ", teacherId='" + teacherId + '\'' +
                ", btn=" + btn +
                '}';
    }
}
