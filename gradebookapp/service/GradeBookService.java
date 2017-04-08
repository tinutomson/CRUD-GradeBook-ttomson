/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.service;

import edu.asu.cse564.gradebookapp.AppData;
import edu.asu.cse564.gradebookapp.model.GradeBook;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("gradebook")
public class GradeBookService {
    
    GradeBook classGradeBook;

    public GradeBookService() {
        classGradeBook = AppData.getGradeBook();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGradeBook() {
        if(classGradeBook == null) 
            return Response.ok().entity("null").build();
        return Response.ok().entity(classGradeBook).build();
    }
 
    //Get all GradeItems
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItems/")
    public Response readGradeItems() {
        return null;
    }
    
    // GradeItem CRUD
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItem/create")
    public Response createGradeItem(String request) {
        return null;
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItem/{gradeItemId}")
    public Response readGradeItem() {
        return null;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItem/{gradeItemId}/update")
    public Response updateGradeItem(String request) {
        return null;
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItem/{gradeItemId}/delete")
    public Response deleteGradeItem(String request) {
        return null;
    }
    
    // GET all appeals
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/appeals")
    public Response getAllAppeals() {
        return null;
    }
}
