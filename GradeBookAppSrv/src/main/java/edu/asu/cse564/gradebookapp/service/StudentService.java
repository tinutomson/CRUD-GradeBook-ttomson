/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.service;

import edu.asu.cse564.gradebookapp.main.AppData;
import edu.asu.cse564.gradebookapp.model.GradeBook;
import edu.asu.cse564.gradebookapp.model.GradeItem;
import edu.asu.cse564.gradebookapp.model.MarkEntry;
import edu.asu.cse564.gradebookapp.model.StudentRecord;
import edu.asu.cse564.gradebookapp.representation.MarkEntryRepresentation;
import static edu.asu.cse564.gradebookapp.representation.Representation.GRADEBOOK_MEDIA_TYPE;
import edu.asu.cse564.gradebookapp.representation.StudentRepresentation;
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

@Path("student")
public class StudentService {
    
    GradeBook classGradeBook;
    
    @Context
    private UriInfo uriInfo;
    
    public StudentService() {
        classGradeBook = AppData.getGradeBook();
    }

    // CRUD student
    
    @POST
    @Consumes(value = GRADEBOOK_MEDIA_TYPE)
    @Produces(value = GRADEBOOK_MEDIA_TYPE)
    public Response createStudent(StudentRecord record) throws URISyntaxException {
        StudentRecord addedRecord = classGradeBook.getStudentRecordList().addIfNotExists(record);
        if(addedRecord == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        StudentRepresentation newStudentRepresentation = StudentRepresentation.
                createStudentRepresentation(record, uriInfo.getBaseUri().toString());
        return Response.created(newStudentRepresentation.selfURI()).entity(newStudentRepresentation).build();
    }
    
    @GET
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/{username}")
    public Response getStudent(@PathParam("username") String username) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        StudentRepresentation studentRepresentation = StudentRepresentation.
                createStudentRepresentation(record, uriInfo.getBaseUri().toString());
        return Response.ok().entity(studentRepresentation).build();
    }
    
    @PUT
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/{username}")
    public Response updateStudent(@PathParam("username") String username, StudentRecord updateRecord) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        record.update(updateRecord);
        StudentRepresentation studentRepresentation = StudentRepresentation.
                createStudentRepresentation(record, uriInfo.getBaseUri().toString());
        return Response.ok().entity(studentRepresentation).build();
    }
    
    @DELETE
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
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
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/{username}/gradeItem/{gradeItemId}")
    public Response createStudentMark(@PathParam("username") String username, @PathParam("gradeItemId") String gradeItemId, MarkEntry newEntry) throws URISyntaxException {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.CONFLICT).build();
        
        GradeItem item = classGradeBook.findGradeItemById(gradeItemId);
        if(item == null)
            return Response.status(Response.Status.CONFLICT).build();
        
        if(item.getTotalMark() < newEntry.getMark())
            return Response.status(Response.Status.CONFLICT).build();
        
        MarkEntry addedEntry = record.addMarkEntry(gradeItemId, newEntry);
        if(addedEntry == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        MarkEntryRepresentation markEntryRepresentation = MarkEntryRepresentation.
                createMarkEntryRepresentation(addedEntry, uriInfo.getBaseUri().toString(), username, gradeItemId);
        
        return Response.created(markEntryRepresentation.selfURI()).entity(markEntryRepresentation).build();
    }
    
    @GET
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/{username}/gradeItem/{gradeItemId}")
    public Response getStudentMark(@PathParam("username") String username, @PathParam("gradeItemId") String gradeItemId) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        
        GradeItem item = classGradeBook.findGradeItemById(gradeItemId);
        if(item == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        
        MarkEntry entry = record.getMarkEntry(gradeItemId);      
        if(entry == null) {
            entry = new MarkEntry(0, "Assignment not yet graded for the student. Check back later.");
            return Response.ok().entity(entry).build();
        }
        
        MarkEntryRepresentation markEntryRepresentation = MarkEntryRepresentation.
                createMarkEntryRepresentation(entry, uriInfo.getBaseUri().toString(), username, gradeItemId);
        
        return Response.ok().entity(markEntryRepresentation).build();
    }
    
    @PUT
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/{username}/gradeItem/{gradeItemId}")
    public Response updateStudentMark(@PathParam("username") String username, @PathParam("gradeItemId") String gradeItemId, MarkEntry updateEntry) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        
        GradeItem item = classGradeBook.findGradeItemById(gradeItemId);
        if(item == null)
            return Response.status(Response.Status.CONFLICT).build();
        
        if(item.getTotalMark() < updateEntry.getMark())
            return Response.status(Response.Status.CONFLICT).build();
        
        MarkEntry updatedEntry = record.updateMarkEntry(gradeItemId, updateEntry);
        if(updatedEntry == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        MarkEntryRepresentation markEntryRepresentation = MarkEntryRepresentation.
            createMarkEntryRepresentation(updatedEntry, uriInfo.getBaseUri().toString(), username, gradeItemId);
        
        return Response.ok().entity(markEntryRepresentation).build();     
    }
    
        
    @DELETE
    @Consumes(GRADEBOOK_MEDIA_TYPE)
    @Produces(GRADEBOOK_MEDIA_TYPE)
    @Path("/{username}/gradeItem/{gradeItemId}")
    public Response deleteStudentMark(@PathParam("username") String username, @PathParam("gradeItemId") String gradeItemId) {
        StudentRecord record = classGradeBook.findStudentByUserName(username);
        if(record == null)
            return Response.status(Response.Status.GONE).build();
        
        GradeItem item = classGradeBook.findGradeItemById(gradeItemId);
        if(item == null)
            return Response.status(Response.Status.CONFLICT).build();
        
        MarkEntry deletedEntry = record.deleteMarkEntry(gradeItemId);
        if(deletedEntry == null)
            return Response.status(Response.Status.GONE).build();
        return Response.ok().entity(deletedEntry).build();
    }
    
}
