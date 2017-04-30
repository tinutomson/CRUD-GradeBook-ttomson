/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author tinutomson
 */

@Provider
public class JacksonProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper objectMapper;

    public JacksonProvider() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }
    
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
    
}
