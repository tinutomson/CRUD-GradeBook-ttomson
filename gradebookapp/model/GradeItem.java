/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.model;


public class GradeItem {
    private int taskId;
    private String taskName;
    private double weightage;
    private double totalMark;

    public GradeItem() {
    }

    public GradeItem(int taskId, String taskName, double weightage, double totalMark) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.weightage = weightage;
        this.totalMark = totalMark;
    }
    
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public double getWeightage() {
        return weightage;
    }

    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }
}
