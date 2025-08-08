package pt.upacademy.jseproject.maven.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProjectExceptionMapper implements ExceptionMapper<Throwable> {
    
    public Response toResponse(Throwable exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Exception", exception.getMessage());

        return Response.status(getStatusCode(exception))
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private int getStatusCode(Throwable exception) {
        if (exception instanceof IllegalArgumentException) {
            return Response.Status.BAD_REQUEST.getStatusCode(); // 400
        }
        if (exception instanceof NoSuchElementException) {
            return Response.Status.NOT_FOUND.getStatusCode(); // 404
        }
        if (exception instanceof IllegalStateException) {
        	return Response.Status.CONFLICT.getStatusCode(); // 409
        }
        return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(); // 500
    }
}

