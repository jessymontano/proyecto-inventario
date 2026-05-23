package mx.unison.proyectoinventario.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CryptoUtilsTest {
    @Test
    public void testHashPassword_isNotPlainText() {
        String password = "contrasena";
        String hash = CryptoUtils.hashPassword(password);

        assertNotNull(hash, "El hash generado no debe ser nulo");
        assertNotEquals(password, hash, "El hash no debe ser igual a la contraseña original");
        assertTrue(hash.startsWith("$2a$") || hash.startsWith("$2b$"), "El hash debe tener el formato estándar de BCrypt");
    }

    @Test
    public void testVerifyPassword_CorrectPassword_ReturnsTrue() {
        String password = "contrasena";
        String hash = CryptoUtils.hashPassword(password);

        boolean isValid = CryptoUtils.verifyPassword(password, hash);

        assertTrue(isValid, "La verificación debe ser exitosa con la contraseña correcta");
    }

    @Test
    public void testVerifyPassword_IncorrectPassword_ReturnsFalse() {
        String password = "contrasena";
        String hash = CryptoUtils.hashPassword(password);

        assertFalse(CryptoUtils.verifyPassword("Contrasena", hash), "La verificación debe ser sensible a mayúsculas y minúsculas");
        assertFalse(CryptoUtils.verifyPassword("contrasena1", hash), "La verificación debe fallar si se escriben caracteres extra");
        assertFalse(CryptoUtils.verifyPassword("incorrecta", hash), "La verificación debe fallar si las contraseñas son diferentes");
    }
}
