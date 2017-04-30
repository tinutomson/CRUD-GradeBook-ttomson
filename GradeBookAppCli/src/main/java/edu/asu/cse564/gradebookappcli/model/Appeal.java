/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.model;

/**
 *
 * @author tinutomson
 */
public class Appeal {
    private int appealId;
    private String studentUserName;
    private int taskId;
    private String description;
    private double expectedMark;
    private AppealStatus status;

    public Appeal() {
    }

    public Appeal(String studentUserName, int taskId, String description, double expectedMark) {
        this.studentUserName = studentUserName;
        this.taskId = taskId;
        this.description = description;
        this.expectedMark = expectedMark;
    }
    
    public int getAppealId() {
        return appealId;
    }

    public void setAppealId(int appealId) {
        this.appealId = appealId;
    }

    public String getStudentUserName() {
        return studentUserName;
    }

    public void setStudentUserName(String studentUserName) {
        this.studentUserName = studentUserName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getExpectedMark() {
        return expectedMark;
    }

    public void setExpectedMark(double expectedMark) {
        this.expectedMark = expectedMark;
    }

    public AppealStatus getStatus() {
        return status;
    }

    public void setStatus(AppealStatus status) {
        this.status = status;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.appealId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Appeal other = (Appeal) obj;
        if (this.appealId != other.appealId) {
            return false;
        }
        return true;
    }
}
