package imt.projetrentree.projet.config;

public class TokenContext {

    private static final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    public static void setToken(String token) {
        tokenThreadLocal.set(token);
    }

    public static String getToken() {
        return tokenThreadLocal.get();
    }

    // Cette méthode est utile pour éviter les fuites de mémoire, à appeler une fois la requête terminée.
    public static void clear() {
        tokenThreadLocal.remove();
    }
}
