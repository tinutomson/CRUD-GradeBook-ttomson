package edu.asu.cse564.gradebookapp.model;

import java.util.ArrayList;
import java.util.List;

public class GradeBook {
    
    private StudentRecordList studentRecordList;
    private GradeItemsList assignedList;
    private AppealList appealList;

    public GradeBook() {
         studentRecordList = new StudentRecordList();
         assignedList = new GradeItemsList();
         appealList = new AppealList();
    }

    public StudentRecordList getStudentRecordList() {
        return studentRecordList;
    }

    public void setStudentRecordList(StudentRecordList studentRecordList) {
        this.studentRecordList = studentRecordList;
    }

    public GradeItemsList getAssignedList() {
        return assignedList;
    }

    public void setAssignedList(GradeItemsList assignedList) {
        this.assignedList = assignedList;
    }

    public AppealList getAppealList() {
        return appealList;
    }

    public void setAppealList(AppealList appealList) {
        this.appealList = appealList;
    }
    
    public GradeItem findGradeItemById(String id) {
        return assignedList.findGradeItemById(id);
    }

    public StudentRecord findStudentByUserName(String username) {
        return studentRecordList.findStudentByUserName(username);
    }

    public Appeal addAppeal(StudentRecord record, Appeal newAppeal) {
        return appealList.addAppeal(record, newAppeal); 
    }

    public AppealList getAppealList(String username) {
        return new AppealList(appealList.getAppealList(username));
    }

    public Appeal getAppeal(String username, String appealId) {
        return appealList.getAppeal(username, appealId);
    }

    public void deleteAppeal(Appeal appeal) {
        appealList.deleteAppeal(appeal);
    }

}
