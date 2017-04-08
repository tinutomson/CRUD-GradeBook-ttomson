package edu.asu.cse564.gradebookapp.model;

import java.util.ArrayList;
import java.util.List;

public class GradeBook {
    List<StudentRecord> studentRecords;
    List<GradeItem> assignedTasks;

    public GradeBook() {
        studentRecords = new ArrayList<>();
        assignedTasks = new ArrayList<>();
    }

    public GradeBook(List<StudentRecord> studentRecords, List<GradeItem> assignedTasks) {
        this.studentRecords = studentRecords;
        this.assignedTasks = assignedTasks;
    }
    
    public List<StudentRecord> getStudentRecords() {
        return studentRecords;
    }

    public void setStudentRecords(List<StudentRecord> studentRecords) {
        this.studentRecords = studentRecords;
    }

    public List<GradeItem> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<GradeItem> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
}
