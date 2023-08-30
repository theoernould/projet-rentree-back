package imt.projetrentree.projet.config;

import imt.projetrentree.projet.services.UserService;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.lang.reflect.Method;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        if (method.isAnnotationPresent(NeedToBeAuthenticated.class)) {
            NeedToBeAuthenticated annotation = method.getAnnotation(NeedToBeAuthenticated.class);
            String tokenParamName = annotation.tokenParamName();
            String token = requestContext.getUriInfo().getQueryParameters().getFirst(tokenParamName);

            if (token == null || !isValidToken(token)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build());
                return;
            }

            TokenContext.setToken(token);
        }
    }

    private boolean isValidToken(String token) {
        return UserService.users.containsKey(token);
    }
}