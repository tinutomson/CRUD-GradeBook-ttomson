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
import edu.asu.cse564.gradebookappcli.model.Appeal;
import javax.ws.rs.core.MediaType;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

/**
 *
 * @author tinutomson
 */
public class AppealCl {

    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/GradeBookAppSrv/api/gradebook/appeal";
    
    public AppealCl() {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(MOXyJsonProvider.class);
        client = Client.create(config);
        webResource = client.resource(BASE_URI);
    }

    public Appeal createAppeal (Appeal requestEntity) throws UniformInterfaceException {      
        return webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(Appeal.class, requestEntity);
    }
    
    public Appeal readAppeal(String appealId) {
        return webResource
                .path("/" + appealId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(Appeal.class);
    }

    public Appeal updateAppeal(String appealId, Appeal updateRecord) {
        return webResource
                .path("/" + appealId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(Appeal.class, updateRecord);
    }
            
    public Appeal deleteAppeal(String appealId, String approve) {
        return webResource
                .path("/" + appealId)
                .queryParam("approve", approve)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .delete(Appeal.class);
    }
}
