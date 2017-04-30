/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.representation;

import edu.asu.cse564.gradebookapp.model.Appeal;
import edu.asu.cse564.gradebookapp.model.AppealStatus;

/**
 *
 * @author tinutomson
 */
public class AppealRepresentation extends Representation {
    private int appealId;
    private String studentUserName;
    private int taskId;
    private String description;
    private double expectedMark;
    private AppealStatus status;

    public AppealRepresentation(Appeal appeal, Link... links) {
        appealId = appeal.getAppealId();
        studentUserName = appeal.getStudentUserName();
        taskId = appeal.getTaskId();
        description = appeal.getDescription();
        expectedMark = appeal.getExpectedMark();   
        status = appeal.getStatus();
        this.links = java.util.Arrays.asList(links);
    }

    public AppealRepresentation() {
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
    
    public static AppealRepresentation createAppealRepresentation(Appeal appeal, String baseURI) {
        GradeBookURI selfURI = new GradeBookURI(baseURI, GRADEBOOK, APPEAL, 
                Integer.toString(appeal.getAppealId()));
        GradeBookURI approveURI = new GradeBookURI(baseURI, GRADEBOOK, APPEAL,
                Integer.toString(appeal.getAppealId()), APPROVE);
        GradeBookURI declineURI = new GradeBookURI(baseURI, GRADEBOOK, APPEAL,
                Integer.toString(appeal.getAppealId()), DECLINE);
        GradeBookURI archiveURI = new GradeBookURI(baseURI, GRADEBOOK, APPEAL,
                Integer.toString(appeal.getAppealId()), ARCHIVE);
        GradeBookURI studentURI = new GradeBookURI(baseURI, STUDENT, appeal.getStudentUserName());
        GradeBookURI gradeItemURI = new GradeBookURI(baseURI, GRADEBOOK, GRADEITEM,
                Integer.toString(appeal.getTaskId()));
        
        if(appeal.getStatus() == AppealStatus.NEW) {
            return new AppealRepresentation(appeal, new Link(SELF_REL_VALUE, selfURI),
                new Link(RELATIONS_URI + UPDATE, selfURI),
                new Link(RELATIONS_URI + DELETE, selfURI),
                new Link(RELATIONS_URI + APPROVE, approveURI),
                new Link(RELATIONS_URI + DECLINE, declineURI),
                new Link(RELATIONS_URI + ARCHIVE, archiveURI),                  
                new Link(RELATIONS_URI + STUDENT, studentURI),
                new Link(RELATIONS_URI + GRADEITEM, gradeItemURI));
        }
        
        if(appeal.getStatus() == AppealStatus.APPOVED || appeal.getStatus() == AppealStatus.DECLINED) {
            return new AppealRepresentation(appeal, new Link(SELF_REL_VALUE, selfURI),
                new Link(RELATIONS_URI + UPDATE, selfURI),
                new Link(RELATIONS_URI + DELETE, selfURI),
                new Link(RELATIONS_URI + ARCHIVE, archiveURI),                  
                new Link(RELATIONS_URI + STUDENT, studentURI),
                new Link(RELATIONS_URI + GRADEITEM, gradeItemURI));            
        }
        
        return new AppealRepresentation(appeal, new Link(SELF_REL_VALUE, selfURI),
            new Link(RELATIONS_URI + UPDATE, selfURI),
            new Link(RELATIONS_URI + DELETE, selfURI),
            new Link(RELATIONS_URI + STUDENT, studentURI),
            new Link(RELATIONS_URI + GRADEITEM, gradeItemURI));
    }
}
