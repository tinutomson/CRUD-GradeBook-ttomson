/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.representation;

import edu.asu.cse564.gradebookapp.model.Appeal;
import edu.asu.cse564.gradebookapp.model.GradeItem;
import static edu.asu.cse564.gradebookapp.representation.Representation.APPEAL;
import static edu.asu.cse564.gradebookapp.representation.Representation.GRADEBOOK;
import static edu.asu.cse564.gradebookapp.representation.Representation.RELATIONS_URI;
import static edu.asu.cse564.gradebookapp.representation.Representation.SELF_REL_VALUE;
import static edu.asu.cse564.gradebookapp.representation.Representation.STUDENT;
import static edu.asu.cse564.gradebookapp.representation.Representation.GRADEITEM;

/**
 *
 * @author tinutomson
 */
public class GradeItemRepresentation extends Representation {
    private static int uniqueId = 0;
    private int taskId;
    private String taskName;
    private double weightage;
    private double totalMark;

    public GradeItemRepresentation() {
    }

    public static int getUniqueId() {
        return uniqueId;
    }

    public static void setUniqueId(int uniqueId) {
        GradeItemRepresentation.uniqueId = uniqueId;
    }

    private GradeItemRepresentation(GradeItem gradeItem, Link... links) {
        taskId = gradeItem.getTaskId();
        taskName = gradeItem.getTaskName();
        weightage = gradeItem.getWeightage();
        totalMark = gradeItem.getTotalMark();
        this.links = java.util.Arrays.asList(links);
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
    
    public static GradeItemRepresentation createGradeItemRepresentation(GradeItem item, String baseURI) {
        GradeBookURI selfURI = new GradeBookURI(baseURI, GRADEBOOK, GRADEITEM, Integer.toString(item.getTaskId()));
        
        return new GradeItemRepresentation(item, new Link(SELF_REL_VALUE, selfURI),
            new Link(RELATIONS_URI + UPDATE, selfURI),
            new Link(RELATIONS_URI + DELETE, selfURI));
    }
}
