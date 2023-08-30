package imt.projetrentree.projet.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ApplicationPath("api") // Spécifiez votre chemin d'application ici
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        // Utilisez la servletConfig passée en argument

        // Assurez-vous de gérer correctement les erreurs de conversion
        // ça marche pas
        int jerseyPort = 8081; // Port par défaut si la valeur du port n'est pas valide

        // Définissez le port pour Jersey
        property("jersey.config.server.port", jerseyPort);

        packages("imt.projetrentree.projet.controllers");
    }
}