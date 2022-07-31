package com.redhat;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Provider
public class ExceptionHandler implements ExceptionMapper<CustomException> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Response toResponse(CustomException exception) {
        ObjectNode serializedException = OBJECT_MAPPER.createObjectNode()
                        .put("error", exception.getMessage());
        return Response.status(Response.Status.fromStatusCode(exception.status()))
            .entity(serializedException).build();
    }
}
