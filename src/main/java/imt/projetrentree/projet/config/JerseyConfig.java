package imt.projetrentree.projet.config;

import imt.projetrentree.projet.controllers.MainController;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("imt.projetrentree.projet.controllers");
    }
}

