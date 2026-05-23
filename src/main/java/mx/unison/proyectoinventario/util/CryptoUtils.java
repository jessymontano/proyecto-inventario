package mx.unison.proyectoinventario.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Clase de utilidad encargada de encriptar y validar contraseñas utilizando BCrypt.
 */
public class CryptoUtils {
    /**
     * Encripta una contraseña usando BCrypt.
     *
     * @param password Contraseña en texto plano
     * @return Contraseña encriptada
     */
    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    /**
     * Verifica si una contraseña en texto plano corresponde a un hash (contraseña encriptada).
     *
     * @param password La contraseña a validar en texto plano
     * @param hash El hash con el que se comparará la contraseña en texto plano
     * @return true si las contraseñas coinciden, false si no coinciden
     */
    public static boolean verifyPassword(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }
}
