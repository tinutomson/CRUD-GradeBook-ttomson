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
import edu.asu.cse564.gradebookappcli.model.GradeItem;
import javax.ws.rs.core.MediaType;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

/**
 *
 * @author tinutomson
 */
public class TaskCl {

    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/api/gradebook/gradeItem/";

    public TaskCl() {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(MOXyJsonProvider.class);
        client = Client.create(config);
        webResource = client.resource(BASE_URI);
    }
    
    public GradeItem createGradeItem(Object requestEntity) throws UniformInterfaceException {      
        return webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(GradeItem.class, requestEntity);
    }
    
    public GradeItem readGradeItem(String taskId) {
        return webResource
                .path("/" + taskId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(GradeItem.class);
    }

    public GradeItem updateGradeItem(String taskId, GradeItem updateRecord) {
        return webResource
                .path("/" + taskId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(GradeItem.class, updateRecord);
    }
            
    public GradeItem deleteGradeItem(String taskId) {
        return webResource
                .path("/" + taskId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .delete(GradeItem.class);
    }
}
