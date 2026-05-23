package mx.unison.proyectoinventario.util;

import mx.unison.proyectoinventario.model.Usuario;

/**
 * Gestiona el estado global de la sesión del usuario autenticado actualmente en el sistema.
 */
public class Session {
    private static Usuario currentUser;

    /**
     * Almacena en memoria al usuario autenticado.
     *
     * @param usuario Objeto Usuario con los datos del usuario que inició sesión
     */
    public static void setCurrentUser(Usuario usuario) {
        currentUser = usuario;
    }

    /**
     * Obtiene la información del usuario autenticado actualmente en el sistema.
     *
     * @return Objeto Usuario con los datos de la sesión
     */
    public static Usuario getCurrentUser() {
        return currentUser;
    }

    /**
     * Elimina el usuario almacenado en memoria cuando cierra sesión
     */
    public static void logout() {
        currentUser = null;
    }
}
