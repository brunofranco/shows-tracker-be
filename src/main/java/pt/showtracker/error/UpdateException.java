package pt.showtracker.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Handles show not found.
 */
public class UpdateException extends WebApplicationException {

    /**
     * Create a HTTP 404 (Not Found) exception.
     */
    public UpdateException() {
        super(Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .type("application/json")
                .build());
    }
}
