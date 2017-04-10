/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

public class BaseCl {
    protected WebResource webResource;
    protected Client client;
    protected static final String BASE_URI = "http://localhost:8080/GradeBookAppSrv/api/";

    public BaseCl() {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(MOXyJsonProvider.class);
        client = Client.create(config);
    }
    
}
