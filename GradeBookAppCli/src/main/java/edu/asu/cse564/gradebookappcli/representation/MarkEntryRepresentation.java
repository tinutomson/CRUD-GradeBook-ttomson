/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.representation;

import edu.asu.cse564.gradebookappcli.model.MarkEntry;
import static edu.asu.cse564.gradebookappcli.representation.Representation.DELETE;
import static edu.asu.cse564.gradebookappcli.representation.Representation.RELATIONS_URI;
import static edu.asu.cse564.gradebookappcli.representation.Representation.SELF_REL_VALUE;
import static edu.asu.cse564.gradebookappcli.representation.Representation.STUDENT;
import static edu.asu.cse564.gradebookappcli.representation.Representation.UPDATE;

/**
 *
 * @author tinutomson
 */
public class MarkEntryRepresentation extends Representation {
    private double mark;
    private String feedBack;

    private MarkEntryRepresentation(MarkEntry entry, Link... links) {
        mark = entry.getMark();
        feedBack = entry.getFeedBack();
        this.links = java.util.Arrays.asList(links);
    }

    public MarkEntryRepresentation() {
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
    
    public static MarkEntryRepresentation createMarkEntryRepresentation(MarkEntry entry, String baseURI, 
            String username, String gradeItemId) {
        
        GradeBookURI studentURI = new GradeBookURI(baseURI, STUDENT, username);
        GradeBookURI gradeURI = new GradeBookURI(baseURI, GRADEBOOK, GRADEITEM, gradeItemId);
        GradeBookURI selfURI = new GradeBookURI(baseURI, STUDENT, username, GRADEITEM, gradeItemId); 
       
        System.out.print(selfURI.toString());
        
        return new MarkEntryRepresentation(entry, new Link(SELF_REL_VALUE, selfURI),
            new Link(RELATIONS_URI + UPDATE, selfURI),
            new Link(RELATIONS_URI + DELETE, selfURI),
            new Link(RELATIONS_URI + GRADEITEM, gradeURI),
            new Link(RELATIONS_URI + STUDENT, studentURI));
    }
    
}
