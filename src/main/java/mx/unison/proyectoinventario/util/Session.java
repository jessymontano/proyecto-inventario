package mx.unison.proyectoinventario.util;

import mx.unison.proyectoinventario.model.Usuario;

public class Session {
    private static Usuario currentUser;

    public static void setCurrentUser(Usuario usuario) {
        currentUser = usuario;
    }

    public static Usuario getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }
}
