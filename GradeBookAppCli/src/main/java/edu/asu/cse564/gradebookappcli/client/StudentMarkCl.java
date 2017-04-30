/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.client;

import com.sun.jersey.api.client.UniformInterfaceException;
import edu.asu.cse564.gradebookappcli.model.MarkEntry;
import edu.asu.cse564.gradebookappcli.representation.MarkEntryRepresentation;
import static edu.asu.cse564.gradebookappcli.representation.Representation.GRADEBOOK_MEDIA_TYPE;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tinutomson
 */
public class StudentMarkCl extends BaseCl {

    public StudentMarkCl() {
        super();
        webResource = client.resource(BASE_URI + "student");
    }
    
    public MarkEntryRepresentation createGradeItem(String userName, String taskId, MarkEntry requestEntity) throws UniformInterfaceException {
        return webResource
                .path("/" + userName + "/gradeItem/" + taskId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .post(MarkEntryRepresentation.class, requestEntity);
    }
    
    public MarkEntryRepresentation readGradeItem(String studentId, String taskId) {
        return webResource
                .path("/" + studentId + "/gradeItem/" + taskId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .get(MarkEntryRepresentation.class);
    }

    public MarkEntryRepresentation updateGradeItem(String studentId, String taskId, MarkEntry updateRecord) {
        return webResource
                .path(studentId + "/gradeItem/" + taskId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .put(MarkEntryRepresentation.class, updateRecord);
    }
            
    public MarkEntry deleteGradeItem(String studentId, String taskId) {
        return webResource
                .path("/" + studentId + "/gradeItem/" + taskId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .delete(MarkEntry.class);
    }
}
