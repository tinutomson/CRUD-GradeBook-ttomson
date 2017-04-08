/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tinutomson
 */
public class GradeItemsList {
    private List<GradeItem> assignedTasks;

    public GradeItemsList() {
        this.assignedTasks = new ArrayList<>();
    }
    
    public GradeItemsList(List<GradeItem> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<GradeItem> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<GradeItem> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
    
    public GradeItem findGradeItemById(String id) {
        int intId = Integer.parseInt(id);
        for(GradeItem it: assignedTasks) {
            if(it.getTaskId() == intId)
                return it;
        }
        return null;
    }
    
    public GradeItem addIfNotExists(GradeItem newItem) {
        for(GradeItem item: assignedTasks) {
            if(newItem.getTaskName().equals(item.getTaskName()) ) {
                return null;
            }
        }
        newItem.generateId();
        assignedTasks.add(newItem);
        return newItem;
    }
    
    public void remove(GradeItem item) {
        assignedTasks.remove(item);
    }
    
}
