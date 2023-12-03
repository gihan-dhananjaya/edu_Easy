package com.plusdev.eduesy.view.tm;

import javafx.scene.control.Button;

public class IntakeTM {
    private String intakeId;
    private String startDate;
    private String intakeName;
    private String intakeCompleteness;
    private String programId;
    private Button btn;

    public IntakeTM() {
    }

    public IntakeTM(String intakeId, String startDate, String intakeName, String intakeCompleteness, String programId, Button btn) {
        this.intakeId = intakeId;
        this.startDate = startDate;
        this.intakeName = intakeName;
        this.intakeCompleteness = intakeCompleteness;
        this.programId = programId;
        this.btn = btn;
    }

    public String getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(String intakeId) {
        this.intakeId = intakeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getIntakeName() {
        return intakeName;
    }

    public void setIntakeName(String intakeName) {
        this.intakeName = intakeName;
    }

    public String isIntakeCompleteness() {
        return intakeCompleteness;
    }

    public void setIntakeCompleteness(String intakeCompleteness) {
        this.intakeCompleteness = intakeCompleteness;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "IntakeTM{" +
                "intakeId='" + intakeId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", intakeName='" + intakeName + '\'' +
                ", intakeCompleteness=" + intakeCompleteness +
                ", programId='" + programId + '\'' +
                ", btn=" + btn +
                '}';
    }
}
