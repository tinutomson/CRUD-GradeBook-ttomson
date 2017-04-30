/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.representation;

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
        return getLinkByName(SELF_REL_VALUE).getUri();
    }
}
