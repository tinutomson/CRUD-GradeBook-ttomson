/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.service;

import edu.asu.cse564.gradebookapp.main.AppData;
import edu.asu.cse564.gradebookapp.model.GradeBook;
import edu.asu.cse564.gradebookapp.model.GradeItem;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
        return Response.ok().entity(classGradeBook).build();
    }
 
    //Get all GradeItems
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItems")
    public Response readGradeItems() {
        return Response.ok().entity(classGradeBook.getAssignedList()).build();
    }
    
    // GradeItem CRUD
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItem")
    public Response createGradeItem(GradeItem gradeItem) throws URISyntaxException {
        GradeItem addedItem = classGradeBook.getAssignedList().addIfNotExists(gradeItem);
        if(addedItem == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        URI location = new URI("gradeItem/" + addedItem.getTaskId());
        return Response.created(location).entity(addedItem).build();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItem/{gradeItemId}")
    public Response readGradeItem(@PathParam("gradeItemId") String gradeItemId) {
        GradeItem item = classGradeBook.findGradeItemById(gradeItemId);
        if(item == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().entity(item).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItem/{gradeItemId}")
    public Response updateGradeItem(@PathParam("gradeItemId") String updateitemId, GradeItem updateItem) {
        GradeItem item = classGradeBook.findGradeItemById(updateitemId);
        if(item == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        item.update(updateItem);
        return Response.ok().entity(item).build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gradeItem/{gradeItemId}")
    public Response deleteGradeItem(@PathParam("gradeItemId") String deleteItemId) {
        GradeItem item = classGradeBook.findGradeItemById(deleteItemId);
        if(item == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        classGradeBook.getAssignedList().remove(item);
        return Response.ok().entity(item).build();
    }
    
    // GET all appeals
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/appeals")
    public Response getAllAppeals() {
        return Response.ok().entity(classGradeBook.getAppealList()).build();
    }
}
