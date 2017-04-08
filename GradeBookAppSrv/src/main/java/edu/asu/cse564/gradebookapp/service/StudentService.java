/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.service;

import edu.asu.cse564.gradebookapp.main.AppData;
import edu.asu.cse564.gradebookapp.model.Appeal;
import edu.asu.cse564.gradebookapp.model.GradeBook;
import edu.asu.cse564.gradebookapp.model.MarkEntry;
import edu.asu.cse564.gradebookapp.model.StudentRecord;
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

@Path("student")
public class StudentService {
    
    GradeBook classGradeBook;
    
    public StudentService() {
        classGradeBook = AppData.getGradeBook();
    }

    // CRUD student
    
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response createStudent(StudentRecord record) throws URISyntaxException {
        StudentRecord addedRecord = classGradeBook.getStudentRecordList().addIfNotExists(record);
        if(addedRecord == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        URI location = new URI("student/" + addedRecord.getUserName());
        return Response.created(location).entity(addedRecord).build();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    public Response getStudent(@PathParam("username") String username) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().entity(record).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    public Response updateStudent(@PathParam("username") String username, StudentRecord updateRecord) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        record.update(updateRecord);
        return Response.ok().entity(record).build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    public Response deleteStudent(@PathParam("username") String username) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        classGradeBook.getStudentRecordList().remove(record);
        return Response.ok().entity(record).build();
    }
    
    // CRUD student mark
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/gradeItem/{gradeItemId}")
    public Response createStudentMark(@PathParam("username") String username, @PathParam("gradeItemId") String gradeItemId, MarkEntry newEntry) throws URISyntaxException {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        MarkEntry addedEntry = record.addMarkEntry(gradeItemId, newEntry);
        if(addedEntry == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        URI location = new URI("student/"+ username + "/gradeItem/" + gradeItemId);
        return Response.created(location).entity(addedEntry).build();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/gradeItem/{gradeItemId}")
    public Response getStudentMark(@PathParam("username") String username, @PathParam("gradeItemId") String gradeItemId) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        MarkEntry entry = record.getMarkEntry(gradeItemId);
        if(entry == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(entry).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/gradeItem/{gradeItemId}")
    public Response updateStudentMark(@PathParam("username") String username, @PathParam("gradeItemId") String gradeItemId, MarkEntry updateEntry) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        MarkEntry updatedEntry = record.updateMarkEntry(gradeItemId, updateEntry);
        if(updatedEntry == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(updatedEntry).build();       
    }
    
        
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/gradeItem/{gradeItemId}")
    public Response deleteStudentMark(@PathParam("username") String username, @PathParam("gradeItemId") String gradeItemId) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.GONE).build();
        MarkEntry deletedEntry = record.deleteMarkEntry(gradeItemId);
        if(deletedEntry == null)
            return Response.status(Response.Status.GONE).build();
        return Response.ok().entity(deletedEntry).build();
    }
    
    // CRUD appeal
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/appeal/")
    public Response createAppeals(@PathParam("username") String username, Appeal newAppeal) throws URISyntaxException {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.CONFLICT).build();
        classGradeBook.addAppeal(record, newAppeal);
        URI location = new URI("student/" + username + "/appeal/");
        return Response.created(location).entity(newAppeal).build();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/appeals/")
    public Response readAppeals(@PathParam("username") String username) {
        return Response.ok().entity(classGradeBook.getAppealList(username)).build();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/appeal/{appealId}")
    public Response getAppeal(@PathParam("username") String username, @PathParam("appealId")String appealId) {
        Appeal appeal = classGradeBook.getAppeal(username, appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }
        return Response.ok().entity(appeal).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/appeal/{appealId}")
    public Response updateAppeal(@PathParam("username") String username, @PathParam("appealId")String appealId, Appeal updateAppeal) {
        Appeal appeal = classGradeBook.getAppeal(username, appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }
        appeal.update(updateAppeal);
        return Response.ok().entity(appeal).build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/appeal/{appealId}")
    public Response deleteAppeal(@PathParam("username") String username, @PathParam("appealId")String appealId) {
        Appeal appeal = classGradeBook.getAppeal(username, appealId);
        if(appeal == null) {
            return Response.status(Response.Status.GONE).build();
        }
        classGradeBook.deleteAppeal(appeal);
        return Response.ok().entity(appeal).build();
    }
}
