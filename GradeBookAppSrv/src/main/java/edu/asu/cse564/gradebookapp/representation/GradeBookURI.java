package edu.asu.cse564.gradebookapp.representation;

import java.net.URI;
import java.net.URISyntaxException;

public class GradeBookURI {
    private URI uri;
    
    public GradeBookURI(String baseUri, String... tokens) {
        StringBuilder sb= new StringBuilder(baseUri);
        try {
            for(String token: tokens) {
                sb.append(token).append("/");
            }
            uri = new URI(sb.toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
    public GradeBookURI(URI uri) {
        this(uri.toString());
    }

    public URI getFullUri() {
        return uri;
    }
    
    @Override
    public String toString() {
        return uri.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GradeBookURI) {
            return ((GradeBookURI)obj).uri.equals(uri);
        }
        return false;
    }
}
