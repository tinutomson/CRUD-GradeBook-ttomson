/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.client;

import com.sun.jersey.api.client.UniformInterfaceException;
import edu.asu.cse564.gradebookappcli.model.GradeItem;
import edu.asu.cse564.gradebookappcli.representation.GradeItemRepresentation;
import static edu.asu.cse564.gradebookappcli.representation.Representation.GRADEBOOK_MEDIA_TYPE;



/**
 *
 * @author tinutomson
 */
public class TaskCl extends BaseCl{

    public TaskCl() {
        super();
        webResource = client.resource(BASE_URI + "gradebook/gradeItem/");
    }
    
    public GradeItemRepresentation createGradeItem(Object requestEntity) throws UniformInterfaceException {      
        return webResource
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .post(GradeItemRepresentation.class, requestEntity);
    }
    
    public GradeItemRepresentation readGradeItem(String taskId) {
        return webResource
                .path("/" + taskId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .get(GradeItemRepresentation.class);
    }

    public GradeItemRepresentation updateGradeItem(String taskId, GradeItem updateRecord) {
        return webResource
                .path("/" + taskId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .put(GradeItemRepresentation.class, updateRecord);
    }
            
    public GradeItem deleteGradeItem(String taskId) {
        return webResource
                .path("/" + taskId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .delete(GradeItem.class);
    }
}
