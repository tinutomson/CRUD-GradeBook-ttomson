/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.client;

import com.sun.jersey.api.client.UniformInterfaceException;
import edu.asu.cse564.gradebookappcli.model.Appeal;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tinutomson
 */
public class AppealCl extends BaseCl{
    
    public AppealCl() {
        super();
        webResource = client.resource(BASE_URI + "gradebook/appeal");
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
