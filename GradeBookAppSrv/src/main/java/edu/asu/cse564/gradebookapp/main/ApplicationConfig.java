/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.main;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author tinutomson
 */
public class ApplicationConfig  extends ResourceConfig{

    public ApplicationConfig() {
        packages("edu.asu.cse564.gradebookapp.service");
    }
    
}
