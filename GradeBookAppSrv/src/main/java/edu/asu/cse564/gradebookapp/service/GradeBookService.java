/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.service;

import edu.asu.cse564.gradebookapp.main.AppData;
import edu.asu.cse564.gradebookapp.model.Appeal;
import edu.asu.cse564.gradebookapp.model.AppealStatus;
import edu.asu.cse564.gradebookapp.model.GradeBook;
import edu.asu.cse564.gradebookapp.model.GradeItem;
import edu.asu.cse564.gradebookapp.model.StudentRecord;
import edu.asu.cse564.gradebookapp.representation.AppealRepresentation;
import edu.asu.cse564.gradebookapp.representation.GradeItemRepresentation;
import static edu.asu.cse564.gradebookapp.representation.Representation.GRADEBOOK_MEDIA_TYPE;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("gradebook")
public class GradeBookService {
    
    GradeBook classGradeBook;
    
    @Context
    private UriInfo uriInfo;

    public GradeBookService() {
        classGradeBook = AppData.getGradeBook();
    }
    
    @GET
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    public Response readGradeBook() {
        return Response.ok().entity(classGradeBook).build();
    }
 
    //Get all GradeItems
    
    @GET
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/gradeItems")
    public Response readGradeItems() {
        return Response.ok().entity(classGradeBook.getAssignedList()).build();
    }
    
    // GradeItem CRUD
    
    @POST
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/gradeItem")
    public Response createGradeItem(GradeItem gradeItem) throws URISyntaxException {
        GradeItem addedItem = classGradeBook.getAssignedList().addIfNotExists(gradeItem);
        if(addedItem == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        GradeItemRepresentation newGradeItemRepresentation = GradeItemRepresentation.
                createGradeItemRepresentation(gradeItem, uriInfo.getBaseUri().toString());

        return Response.created(newGradeItemRepresentation.selfURI()).entity(newGradeItemRepresentation).build();
    }
    
    @GET
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/gradeItem/{gradeItemId}")
    public Response readGradeItem(@PathParam("gradeItemId") String gradeItemId) {
        GradeItem item = classGradeBook.findGradeItemById(gradeItemId);
        if(item == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        GradeItemRepresentation gradeItemRepresentation = GradeItemRepresentation.
                createGradeItemRepresentation(item, uriInfo.getBaseUri().toString());
        return Response.ok().entity(gradeItemRepresentation).build();
    }
    
    @PUT
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/gradeItem/{gradeItemId}")
    public Response updateGradeItem(@PathParam("gradeItemId") String updateitemId, GradeItem updateItem) {
        GradeItem item = classGradeBook.findGradeItemById(updateitemId);
        if(item == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        item.update(updateItem);
        GradeItemRepresentation gradeItemRepresentation = GradeItemRepresentation.
                createGradeItemRepresentation(item, uriInfo.getBaseUri().toString());
        return Response.ok().entity(gradeItemRepresentation).build();
    }
    
    @DELETE
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/gradeItem/{gradeItemId}")
    public Response deleteGradeItem(@PathParam("gradeItemId") String deleteItemId) {
        GradeItem item = classGradeBook.findGradeItemById(deleteItemId);
        if(item == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        classGradeBook.getAssignedList().remove(item);
        return Response.ok().entity(item).build();
    }
    
    //CRUD Appeal
    
    @POST
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/appeal/")
    public Response createAppeals(Appeal newAppeal) throws URISyntaxException {
        System.out.print("baseURI" + uriInfo.getBaseUri().toString());
        if(!isValidAppeal(newAppeal))
            return Response.status(Response.Status.CONFLICT).build();
        
        classGradeBook.addAppeal(newAppeal);
        
        AppealRepresentation newAppealRepresentation = AppealRepresentation.
                createAppealRepresentation(newAppeal, uriInfo.getBaseUri().toString());
        URI location = new URI("gradebook/appeal/" + String.valueOf(newAppeal.getAppealId()));
        return Response.created(newAppealRepresentation.selfURI()).entity(newAppealRepresentation).build();
    }
    
    @GET
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/appeal/{appealId}")
    public Response getAppeal(@PathParam("username") String username, @PathParam("appealId")String appealId) {
        Appeal appeal = classGradeBook.getAppeal(appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }
        AppealRepresentation appealRepresentation = AppealRepresentation.
                createAppealRepresentation(appeal, uriInfo.getBaseUri().toString());
        return Response.ok().entity(appealRepresentation).build();
    }
    
    @PUT
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/appeal/{appealId}")
    public Response updateAppeal(@PathParam("appealId")String appealId, Appeal updateAppeal) {
        Appeal appeal = classGradeBook.getAppeal(appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }
        if(!isValidAppeal(updateAppeal))
            return Response.status(Response.Status.CONFLICT).build();
        appeal.update(updateAppeal);
        AppealRepresentation appealRepresentation = AppealRepresentation.
                createAppealRepresentation(appeal, uriInfo.getBaseUri().toString());
        return Response.ok().entity(appealRepresentation).build();
    }
    
    @DELETE
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/appeal/{appealId}")
    public Response deleteAppeal(@PathParam("appealId")String appealId) {
        Appeal appeal = classGradeBook.getAppeal(appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }

        classGradeBook.deleteAppeal(appeal);
        return Response.ok().entity(appeal).build();
    }
    
    @PUT
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/appeal/{appealId}/approve")
    public Response approveAppeal(@PathParam("appealId")String appealId) {
        Appeal appeal = classGradeBook.getAppeal(appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }
        
        if(appeal.getStatus() != AppealStatus.NEW)
            return Response.status(Response.Status.CONFLICT).build();

        classGradeBook.updateFromAppeal(appeal);
        appeal.setStatus(AppealStatus.APPOVED);
        AppealRepresentation appealRepresentation = AppealRepresentation.
                createAppealRepresentation(appeal, uriInfo.getBaseUri().toString());
        return Response.ok().entity(appealRepresentation).build();
    }
    
    @PUT
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/appeal/{appealId}/decline")
    public Response declineAppeal(@PathParam("appealId")String appealId) {
        Appeal appeal = classGradeBook.getAppeal(appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }
        
        if(appeal.getStatus() != AppealStatus.NEW)
            return Response.status(Response.Status.CONFLICT).build();
        appeal.setStatus(AppealStatus.DECLINED);
        AppealRepresentation appealRepresentation = AppealRepresentation.
                createAppealRepresentation(appeal, uriInfo.getBaseUri().toString());
        return Response.ok().entity(appealRepresentation).build();
    }
    
    @PUT
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/appeal/{appealId}/archive")
    public Response archiveAppeal(@PathParam("appealId")String appealId) {
        Appeal appeal = classGradeBook.getAppeal(appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }
        if(appeal.getStatus() == AppealStatus.ARCHIVE)
            return Response.status(Response.Status.CONFLICT).build();
        appeal.setStatus(AppealStatus.ARCHIVE);
        AppealRepresentation appealRepresentation = AppealRepresentation.
                createAppealRepresentation(appeal, uriInfo.getBaseUri().toString());
        return Response.ok().entity(appealRepresentation).build();
    }
    
    private boolean isValidAppeal(Appeal appeal) {
        StudentRecord record = classGradeBook.findStudentByUserName(appeal.getStudentUserName());
        if(record == null)
            return false;
        GradeItem item = classGradeBook.findGradeItemById(appeal.getTaskId());
        if(item == null)
            return false;
        if(item.getTotalMark() < appeal.getExpectedMark())
            return false;
        return true;
    }
}
