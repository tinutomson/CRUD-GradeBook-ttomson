
package edu.asu.cse564.gradebookappcli.representation;

import java.net.URI;
import java.net.URISyntaxException;

public class Link {
    private String rel;
    private String href;
    private String mediaType;

    Link() {
    }

    public Link(String name, GradeBookURI uri, String mediaType) {
        
        this.rel = name;
        this.href = uri.getFullUri().toString();
        this.mediaType = mediaType;
    }

    public Link(String name, GradeBookURI uri) {
        this(name, uri, Representation.GRADEBOOK_MEDIA_TYPE);
    }

    public String getRel() {
        return rel;
    }

    public URI getHref() {
        
        try {
            URI local_uri = new URI(href);
            return local_uri;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMediaType() {
        return mediaType;
    }
}
