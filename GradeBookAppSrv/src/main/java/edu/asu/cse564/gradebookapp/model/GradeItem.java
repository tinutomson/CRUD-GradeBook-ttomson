/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.model;

import java.util.Objects;


public class GradeItem {
    private static int uniqueId = 0;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.taskId;
        hash = 79 * hash + Objects.hashCode(this.taskName);
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
        final GradeItem other = (GradeItem) obj;
        if (this.taskId != other.taskId) {
            return false;
        }
        if (!Objects.equals(this.taskName, other.taskName)) {
            return false;
        }
        return true;
    }
    
    
    
    public void generateId() {
        uniqueId++;
        this.taskId = uniqueId;
    }
    
    public void update(GradeItem item) {
        this.taskName = item.taskName;
        this.weightage = item.weightage;
        this.totalMark = item.totalMark;
    }
}
