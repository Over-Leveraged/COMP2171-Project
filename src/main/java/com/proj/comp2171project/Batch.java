package com.proj.comp2171project;

import java.util.Date;

public class Batch {
    private String batchNumber;
    private String batchName;
    private String location;
    private Date trainingDate;

    public Batch(String batchNumber, String batchName, String location, Date trainingDate) {
        this.batchNumber = batchNumber;
        this.batchName = batchName;
        this.location = location;
        this.trainingDate = trainingDate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public String getBatchName() {
        return batchName;
    }

    public String getLocation() {
        return location;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }
}
