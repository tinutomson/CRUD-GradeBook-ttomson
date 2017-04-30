/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.client;

import com.sun.jersey.api.client.UniformInterfaceException;
import edu.asu.cse564.gradebookappcli.model.StudentRecord;
import static edu.asu.cse564.gradebookappcli.representation.Representation.GRADEBOOK_MEDIA_TYPE;
import edu.asu.cse564.gradebookappcli.representation.StudentRepresentation;


public class StudentCl extends BaseCl {

    public StudentCl() {
        super();
        webResource = client.resource(BASE_URI + "student");
    }
    
    public StudentRepresentation createStudent(Object requestEntity) throws UniformInterfaceException {      
        return webResource
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .post(StudentRepresentation.class, requestEntity);
    }
    
    public StudentRepresentation readStudent(String studentId) {
        return webResource
                .path("/" + studentId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .get(StudentRepresentation.class);
    }

    public StudentRepresentation updateStudent(String studentId, StudentRecord updateRecord) {
        return webResource
                .path("/" + studentId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .put(StudentRepresentation.class, updateRecord);
    }
            
    public StudentRecord deleteStudent(String studentId) {
        return webResource
                .path("/" + studentId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .delete(StudentRecord.class);
    }
}
