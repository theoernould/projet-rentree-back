package imt.projetrentree.projet.config;

import org.springframework.stereotype.Component;

@Component
public class AuthContext {

    private static String currentToken;

    public static String getToken() {
        return currentToken;
    }

    public static void setToken(String token) {
        currentToken = token;
    }

}
