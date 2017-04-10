/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import edu.asu.cse564.gradebookappcli.model.MarkEntry;
import javax.ws.rs.core.MediaType;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

/**
 *
 * @author tinutomson
 */
public class StudentMarkCl {

    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/api/student";

    public StudentMarkCl() {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(MOXyJsonProvider.class);
        client = Client.create(config);
        webResource = client.resource(BASE_URI);
    }
    
    public MarkEntry createGradeItem(String userName, String taskId, MarkEntry requestEntity) throws UniformInterfaceException {
        return webResource
                .path("/" + userName + "/gradeItem/" + taskId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(MarkEntry.class, requestEntity);
    }
    
    public MarkEntry readGradeItem(String studentId, String taskId) {
        return webResource
                .path("/" + studentId + "/gradeItem/" + taskId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(MarkEntry.class);
    }

    public MarkEntry updateGradeItem(String studentId, String taskId, MarkEntry updateRecord) {
        return webResource
                .path(studentId + "/gradeItem/" + taskId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(MarkEntry.class, updateRecord);
    }
            
    public MarkEntry deleteGradeItem(String studentId, String taskId) {
        return webResource
                .path("/" + studentId + "/gradeItem/" + taskId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .delete(MarkEntry.class);
    }
}
