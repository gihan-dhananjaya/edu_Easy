package com.plusdev.eduesy.model;

import java.util.Arrays;

public class Program {

    private String programCode;
    private String name;
    private String[] technologies;
    private double cost;
    private String teacherId;

    public Program() {
    }

    public Program(String programCode, String name, String[] technologies, double cost, String teacherId) {
        this.programCode = programCode;
        this.name = name;
        this.technologies = technologies;
        this.cost = cost;
        this.teacherId = teacherId;
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

    public String[] getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String[] technologies) {
        this.technologies = technologies;
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

    @Override
    public String toString() {
        return "Program{" +
                "programCode='" + programCode + '\'' +
                ", name='" + name + '\'' +
                ", technologies=" + Arrays.toString(technologies) +
                ", cost=" + cost +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
