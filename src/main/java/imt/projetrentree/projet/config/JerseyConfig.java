package imt.projetrentree.projet.config;

import imt.projetrentree.projet.filters.AuthenticationFilter;
import imt.projetrentree.projet.filters.CORSFilter;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        int jerseyPort = 8081;

        property("jersey.config.server.port", jerseyPort);
        packages("imt.projetrentree.projet.controllers");

        register(CORSFilter.class);
        register(AuthenticationFilter.class);

        register(GlobalConfig.class);
    }
}