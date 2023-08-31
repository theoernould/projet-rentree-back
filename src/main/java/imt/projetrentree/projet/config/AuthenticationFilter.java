package imt.projetrentree.projet.config;

import imt.projetrentree.projet.services.UserService;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.lang.reflect.Method;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private HttpHeaders headers;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        if (method.isAnnotationPresent(NeedToBeAuthenticated.class)) {
            NeedToBeAuthenticated annotation = method.getAnnotation(NeedToBeAuthenticated.class);
            String tokenHeaderName = annotation.tokenHeaderName();
            String token = headers.getHeaderString(tokenHeaderName);

            if (token == null || !isValidToken(token)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build());
                return;
            }

            AuthContext.setToken(token);
        }
    }

    private boolean isValidToken(String token) {
        return UserService.usersIds.containsKey(token);
    }
}