/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.representation;

import java.net.URI;
import java.util.List;

/**
 *
 * @author tinutomson
 */
public class Representation {
    public static final String RELATIONS_URI = "http://relations.gradebook.asu.edu/";

    public static final String DELETE = "delete";    
    public static final String UPDATE = "update";    
    
    public static final String STUDENT = "student";
    public static final String GRADEITEM = "gradeItem";
    public static final String GRADEITEMS = "gradeItems";
    public static final String MARKENTRY = "markEntry";
    public static final String APPEAL = "appeal";
    public static final String GRADEBOOK = "gradebook";
    public static final String APPROVE = "approve";
    public static final String DECLINE = "decline";
    public static final String ARCHIVE = "archive";
    public static final String UNKWOWN = "unknown";
    
    public static final String GRADEBOOK_MEDIA_TYPE = "application/vnd.gradebook+json";
    public static final String SELF_REL_VALUE = "self";
    
    
    protected List<Link> links;

    public Representation() {
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    protected Link getLinkByName(String uriName) {
        if (links == null) {
            return null;
        }

        for (Link l : links) {
            if (l.getRel().toLowerCase().equals(uriName.toLowerCase())) {
                return l;
            }
        }
        return null;
    }
    
    public URI selfURI() {
        return getLinkByName(SELF_REL_VALUE).getHref();
    }
    
    public static String getVerbforRel(String rel) {
        switch(rel) {
            case SELF_REL_VALUE:
                return SELF_REL_VALUE;
            case RELATIONS_URI+ UPDATE:
                return UPDATE;
            case RELATIONS_URI+ DELETE:
                return DELETE;
            case RELATIONS_URI+ STUDENT:
                return STUDENT;
            case RELATIONS_URI+ GRADEITEM:
                return GRADEITEM;
            case RELATIONS_URI+ GRADEITEMS:
                return GRADEITEMS;                
            case RELATIONS_URI+ MARKENTRY:
                return MARKENTRY;
            case RELATIONS_URI+ APPROVE:
                return APPROVE;
            case RELATIONS_URI+ DECLINE:
                return DECLINE;
            case RELATIONS_URI+ ARCHIVE:
                return ARCHIVE;
            case RELATIONS_URI+ APPEAL:
                return APPEAL;
            case RELATIONS_URI+ GRADEBOOK:
                return GRADEBOOK;                
        }
        return UNKWOWN;
    }
}
