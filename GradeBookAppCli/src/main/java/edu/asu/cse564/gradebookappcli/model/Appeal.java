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
    private String Description;
    private double expectedMark;

    public Appeal() {
    }

    public Appeal(String studentUserName, int taskId, String Description, double expectedMark) {
        this.studentUserName = studentUserName;
        this.taskId = taskId;
        this.Description = Description;
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
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getExpectedMark() {
        return expectedMark;
    }

    public void setExpectedMark(double expectedMark) {
        this.expectedMark = expectedMark;
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
