package com.plusdev.eduesy.model;

import java.util.Date;

public class Intake {

    private String intakeId;
    private String startDate;
    private String intakeName;
    private boolean intakeCompleteness;
    private String programId;
    public Intake() {
    }

    public Intake(String intakeId, String startDate, String intakeName, boolean intakeCompleteness, String programId) {
        this.intakeId = intakeId;
        this.startDate = startDate;
        this.intakeName = intakeName;
        this.intakeCompleteness = intakeCompleteness;
        this.programId = programId;
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

    public boolean isIntakeCompleteness() {
        return intakeCompleteness;
    }

    public void setIntakeCompleteness(boolean intakeCompleteness) {
        this.intakeCompleteness = intakeCompleteness;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    @Override
    public String toString() {
        return "Intake{" +
                "intakeId='" + intakeId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", intakeName='" + intakeName + '\'' +
                ", intakeCompleteness=" + intakeCompleteness +
                ", programId='" + programId + '\'' +
                '}';
    }
}
