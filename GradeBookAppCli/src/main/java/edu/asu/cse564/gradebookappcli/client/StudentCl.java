/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.client;

import com.sun.jersey.api.client.UniformInterfaceException;
import edu.asu.cse564.gradebookappcli.model.StudentRecord;
import javax.ws.rs.core.MediaType;

public class StudentCl extends BaseCl {

    public StudentCl() {
        super();
        webResource = client.resource(BASE_URI + "student");
    }
    
    public StudentRecord createStudent(Object requestEntity) throws UniformInterfaceException {      
        return webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(StudentRecord.class, requestEntity);
    }
    
    public StudentRecord readStudent(String studentId) {
        return webResource
                .path("/" + studentId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(StudentRecord.class);
    }

    public StudentRecord updateStudent(String studentId, StudentRecord updateRecord) {
        return webResource
                .path("/" + studentId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(StudentRecord.class, updateRecord);
    }
            
    public StudentRecord deleteStudent(String studentId) {
        return webResource
                .path("/" + studentId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .delete(StudentRecord.class);
    }
}
