/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.client;

import com.sun.jersey.api.client.UniformInterfaceException;
import edu.asu.cse564.gradebookappcli.model.Appeal;
import edu.asu.cse564.gradebookappcli.representation.AppealRepresentation;
import static edu.asu.cse564.gradebookappcli.representation.Representation.GRADEBOOK_MEDIA_TYPE;

/**
 *
 * @author tinutomson
 */
public class AppealCl extends BaseCl{
    
    public AppealCl() {
        super();
        webResource = client.resource(BASE_URI + "gradebook/appeal");
    }

    public AppealRepresentation createAppeal (Appeal requestEntity) throws UniformInterfaceException {      
        return webResource
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .post(AppealRepresentation.class, requestEntity);
    }
    
    public AppealRepresentation readAppeal(String appealId) {
        return webResource
                .path("/" + appealId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .get(AppealRepresentation.class);
    }

    public AppealRepresentation updateAppeal(String appealId, Appeal updateRecord) {
        return webResource
                .path("/" + appealId)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .put(AppealRepresentation.class, updateRecord);
    }
            
    public Appeal deleteAppeal(String appealId, String approve) {
        return webResource
                .path("/" + appealId)
                .queryParam("approve", approve)
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .delete(Appeal.class);
    }

    public AppealRepresentation approve(String appealId) {
        return webResource
                .path("/" + appealId + "/approve")
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .put(AppealRepresentation.class);
    }

    public AppealRepresentation decline(String appealId) {
        return webResource
                .path("/" + appealId + "/decline")
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .put(AppealRepresentation.class);
    }

    public AppealRepresentation archive(String appealId) {
        return webResource
                .path("/" + appealId + "/archive")
                .accept(GRADEBOOK_MEDIA_TYPE)
                .type(GRADEBOOK_MEDIA_TYPE)
                .put(AppealRepresentation.class);        
    }
}
