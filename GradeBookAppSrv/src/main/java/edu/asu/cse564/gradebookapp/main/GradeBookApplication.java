/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.main;

import edu.asu.cse564.gradebookapp.service.GradeBookService;
import edu.asu.cse564.gradebookapp.service.StudentService;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author tinutomson
 */

@ApplicationPath("/api")
public class GradeBookApplication extends ResourceConfig {

    public GradeBookApplication() {
        super(JacksonProvider.class,
                JacksonFeature.class,
                GradeBookService.class,
                StudentService.class);
    }
    
}
