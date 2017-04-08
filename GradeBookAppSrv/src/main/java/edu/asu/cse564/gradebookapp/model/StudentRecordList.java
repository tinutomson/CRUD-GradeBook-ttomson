/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.model;

import java.util.ArrayList;
import java.util.List;

public class StudentRecordList {
    private List<StudentRecord> studentRecords;

    public StudentRecordList() {
        this.studentRecords = new ArrayList<>();
    }

    public StudentRecordList(List<StudentRecord> studentRecords) {
        this.studentRecords = studentRecords;
    }

    public List<StudentRecord> getStudentRecords() {
        return studentRecords;
    }

    public void setStudentRecords(List<StudentRecord> studentRecords) {
        this.studentRecords = studentRecords;
    }
    
    public void remove(StudentRecord item) {
        studentRecords.remove(item);
    }

    public StudentRecord addIfNotExists(StudentRecord newRecord) {
        for(StudentRecord record: studentRecords) {
            if(newRecord.equals(record) ) {
                return null;
            }
        }
        studentRecords.add(newRecord);
        return newRecord;
    }

    StudentRecord findStudentByUserName(String username) {
        for(StudentRecord record: studentRecords) {
            if(record.getUserName().equals(username))
                return record;
        }
        return null;
    }
    
}
