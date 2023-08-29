package imt.projetrentree.projet.config;

import imt.projetrentree.projet.controllers.MainController;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import javax.servlet.ServletConfig;

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

        // Utilisez getResource pour obtenir le chemin relatif à votre classe JerseyConfig
        String basePackage = JerseyConfig.class.getPackage().getName();

        // Ajoutez le chemin relatif à votre package de contrôleurs
        packages(basePackage + ".controllers");
    }
}