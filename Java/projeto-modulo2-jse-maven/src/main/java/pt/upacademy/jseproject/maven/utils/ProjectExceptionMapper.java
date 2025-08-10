package pt.upacademy.jseproject.maven.utils;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProjectExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(ProjectExceptionMapper.class.getName());

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Exception exception) {
        int status = determineStatusCode(exception);

        Map<String, Object> error = new LinkedHashMap<>();
        error.put("timestamp", Instant.now().toString());
        error.put("status", status);
        error.put("error", reasonPhrase(status));
        error.put("message", (exception.getMessage() == null || exception.getMessage().trim().isEmpty())
                ? reasonPhrase(status)
                : exception.getMessage());
        if (uriInfo != null) {
            error.put("path", uriInfo.getPath());
        }

        // Logging
        if (status >= 500) {
            LOGGER.log(Level.SEVERE, "Unhandled exception caught by ProjectExceptionMapper", exception);
        } else {
            LOGGER.log(Level.WARNING, "Handled client error: {0}", exception.getMessage());
        }

        return Response.status(status)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private int determineStatusCode(Exception exception) {
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse().getStatus();
        }
        if (exception instanceof IllegalArgumentException || exception instanceof ConstraintViolationException) {
            return Response.Status.BAD_REQUEST.getStatusCode();
        }
        if (exception instanceof NoSuchElementException) {
            return Response.Status.NOT_FOUND.getStatusCode();
        }
        if (exception instanceof IllegalStateException) {
            return Response.Status.CONFLICT.getStatusCode();
        }
        return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }

    private String reasonPhrase(int status) {
        Response.Status rs = Response.Status.fromStatusCode(status);
        return (rs != null) ? rs.getReasonPhrase() : "Unknown";
    }
}


