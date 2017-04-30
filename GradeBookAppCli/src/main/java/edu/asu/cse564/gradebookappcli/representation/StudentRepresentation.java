/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.representation;

import edu.asu.cse564.gradebookappcli.model.MarkEntry;
import edu.asu.cse564.gradebookappcli.model.StudentRecord;
import java.util.HashMap;

/**
 *
 * @author tinutomson
 */
public class StudentRepresentation extends Representation {

    private String studentName;
    private String userName;
    
    private HashMap<Integer, MarkEntry> markList;

    public StudentRepresentation() {
    }
    
    private StudentRepresentation(StudentRecord record, Link... links) {
        studentName = record.getStudentName();
        userName = record.getUserName();
        markList = record.getMarkList();
        this.links = java.util.Arrays.asList(links);
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
    

    public static StudentRepresentation createStudentRepresentation(StudentRecord record, String baseURI) {
        
        GradeBookURI selfURI = new GradeBookURI(baseURI, STUDENT, record.getUserName());

        //TODO: More Links
       
        System.out.print(selfURI.toString());
        
        return new StudentRepresentation(record, new Link(SELF_REL_VALUE, selfURI),
            new Link(RELATIONS_URI + UPDATE, selfURI),
            new Link(RELATIONS_URI + DELETE, selfURI));
    }
}
