package mx.unison.proyectoinventario.dao;

import mx.unison.proyectoinventario.model.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {
    @Test
    public void testInsertAndAuthenticate_Success() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // agregar tiempo para que cada usuario sea único
        String username = "testAuthenticate_" + System.currentTimeMillis();
        String password = "contrasena";
        String role = "ADMIN";

        usuarioDAO.insertDefaultUser(username, password, role);
        Usuario authenticatedUser = usuarioDAO.authenticate(username, password);

        assertNotNull(authenticatedUser, "El método authenticate no debería regresar null con credenciales correctas");
        assertEquals(username, authenticatedUser.nombre, "El nombre del usuario debe coincidir");
        assertEquals(role, authenticatedUser.rol, "El rol del usuario debe coincidir");
    }

    @Test
    public void testAuthenticate_IncorrectPassword_ReturnsNull() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // agregar tiempo para que cada usuario sea único
        String username = "testIncorrectPass_" + System.currentTimeMillis();

        usuarioDAO.insertDefaultUser(username, "contrasena", "ADMIN");
        Usuario user = usuarioDAO.authenticate(username, "incorrecta");

        assertNull(user, "Debería regresar null si la contraseña es incorrecta");
    }

    @Test
    public void testAuthenticate_UserDoesntExist_ReturnsNull() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario user = usuarioDAO.authenticate("usuario", "contrasena");

        assertNull(user, "Debería regresar null si el usuario no existe en la base de datos");
    }

    @Test
    public void testInsertDefaultUser_IgnoresDuplicates() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // agregar tiempo para que cada usuario sea único
        String username = "testDuplicates_" + System.currentTimeMillis();

        usuarioDAO.insertDefaultUser(username, "contrasena", "ADMIN");

        assertDoesNotThrow(() -> {
            usuarioDAO.insertDefaultUser(username, "contrasena", "ADMIN");
        }, "No debería lanzar error al intentar insertar un usuario que ya existe");
    }
}
