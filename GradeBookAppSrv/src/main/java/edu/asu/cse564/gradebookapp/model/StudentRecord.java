/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.model;

import java.util.HashMap;
import java.util.Objects;

public class StudentRecord {
    private String studentName;
    private String userName;
    
    private HashMap<Integer, MarkEntry> markList;

    public StudentRecord() {
        markList = new HashMap<>();
    }

    public StudentRecord(String studentName, String userName, HashMap<Integer, MarkEntry> markList) {
        this.studentName = studentName;
        this.userName = userName;
        this.markList = markList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.userName);
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
        final StudentRecord other = (StudentRecord) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

    
    

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashMap<Integer, MarkEntry> getMarkList() {
        return markList;
    }

    public void setMarkList(HashMap<Integer, MarkEntry> markList) {
        this.markList = markList;
    }

    public void update(StudentRecord updateRecord) {
        this.studentName = updateRecord.getStudentName();
    }

    public MarkEntry addMarkEntry(String gradeItemId, MarkEntry newEntry) {
        int intGradeItemId = Integer.parseInt(gradeItemId);
        if(markList.containsKey(intGradeItemId)) {
            return null;
        } else {
            markList.put(intGradeItemId, newEntry);
        }
        return newEntry;
    }

    public MarkEntry getMarkEntry(String gradeItemId) {
        int intGradeItemId = Integer.parseInt(gradeItemId);
        if(markList.containsKey(intGradeItemId)) {
            return markList.get(intGradeItemId);
        }
        return null;
    }

    public MarkEntry updateMarkEntry(String gradeItemId, MarkEntry updateEntry) {
        int intGradeItemId = Integer.parseInt(gradeItemId);
        if(markList.containsKey(intGradeItemId)) {
            markList.put(intGradeItemId, updateEntry);
            return updateEntry;
        }
        return null;
    }

    public MarkEntry deleteMarkEntry(String gradeItemId) {
        int intGradeItemId = Integer.parseInt(gradeItemId);
        if(markList.containsKey(intGradeItemId)) {
            MarkEntry entry = markList.get(intGradeItemId);
            markList.remove(intGradeItemId);
            return entry;
        }
        return null;
    }
}
