package pt.showtracker.error;

import pt.showtracker.error.entity.ShowNotFound;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Handles show not found.
 */
public class ShowNotFoundException extends WebApplicationException {
    /**
     * Create a HTTP 404 (Not Found) exception.
     */
    public ShowNotFoundException() {
        super(Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ShowNotFound("Show not found.", Response.Status.NOT_FOUND.getStatusCode()))
                .type("application/json")
                .build());
    }

    /**
     * Create a HTTP 404 (Not Found) exception.
     * @param message the String that is the entity of the 404 response.
     */
    public ShowNotFoundException(String message) {
        super(Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ShowNotFound(message, Response.Status.NOT_FOUND.getStatusCode()))
                .type("application/json")
                .build());
    }
}
