/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.model;

import java.util.HashMap;

public class StudentRecord {
    private String studentName;
    private String userName;
    
    private HashMap<Integer, Double> markList;

    public StudentRecord() {
        markList = new HashMap<>();
    }

    public StudentRecord(String studentName, String userName, HashMap<Integer, Double> markList) {
        this.studentName = studentName;
        this.userName = userName;
        this.markList = markList;
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

    public HashMap<Integer, Double> getMarkList() {
        return markList;
    }

    public void setMarkList(HashMap<Integer, Double> markList) {
        this.markList = markList;
    }
    
}
