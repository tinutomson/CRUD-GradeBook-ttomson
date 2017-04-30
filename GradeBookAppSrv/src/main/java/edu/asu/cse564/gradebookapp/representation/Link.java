
package edu.asu.cse564.gradebookapp.representation;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

public class Link {
    private String rel;
    private String uri;
    private String mediaType;

    Link() {
    }

    public Link(String name, GradeBookURI uri, String mediaType) {
        
        this.rel = name;
        this.uri = uri.getFullUri().toString();
        this.mediaType = mediaType;
    }

    public Link(String name, GradeBookURI uri) {
        this(name, uri, Representation.GRADEBOOK_MEDIA_TYPE);
    }

    public String getRel() {
        return rel;
    }

    public URI getUri() {
        
        try {
            URI local_uri = new URI(uri);
            return local_uri;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMediaType() {
        return mediaType;
    }
}
