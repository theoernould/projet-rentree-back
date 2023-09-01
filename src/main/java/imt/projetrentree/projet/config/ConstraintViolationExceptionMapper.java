package imt.projetrentree.projet.config;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        StringBuilder message = new StringBuilder();
        exception.getConstraintViolations().forEach(constraintViolation -> message.append(constraintViolation.getMessage()).append("; "));

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(message.toString())
                .build();
    }
}