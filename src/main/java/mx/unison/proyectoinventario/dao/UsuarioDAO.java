package mx.unison.proyectoinventario.dao;

import mx.unison.proyectoinventario.model.Usuario;
import mx.unison.proyectoinventario.util.CryptoUtils;
import mx.unison.proyectoinventario.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsuarioDAO {
    /**
     * Inserta un nuevo usuario a la base de datos.
     * Encripta la contraseña antes de insertarse usando BCrypt.
     *
     * @param nombre El nombre del nuevo usuario
     * @param passPlain La contraseña del usuario en texto planp
     * @param rol El rol del usuario, puede ser "ADMIN", "ALMACENES", o "PRODUCTOS"
     */
    public void insertDefaultUser(String nombre, String passPlain, String rol) {
        String check = "SELECT nombre FROM usuarios WHERE nombre=?";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(check)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                String ins = "INSERT INTO usuarios(nombre, password, rol) VALUES(?, ?, ?)";
                try (PreparedStatement ps2 = c.prepareStatement(ins)) {
                    ps2.setString(1, nombre);
                    ps2.setString(2, CryptoUtils.hashPassword(passPlain));
                    ps2.setString(3, rol);
                    ps2.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Autentica un usuario con su usuario y contraseña y regresa los datos para la sesión.
     *
     * @param nombre El nombre del usuario
     * @param passwordPlain La contraseña en texto plano
     * @return Objeto Usuario con los datos del usuario autenticado si fue exitoso, si no, null
     */
    public Usuario authenticate(String nombre, String passwordPlain) {
        String sql = "SELECT nombre, password, rol FROM usuarios WHERE nombre=?";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String hash = rs.getString("password");

                // verificar que las contraseñas sean iguales
                if (CryptoUtils.verifyPassword(passwordPlain, hash)) {
                    Usuario u = new Usuario();
                    u.nombre = rs.getString("nombre");
                    u.rol = rs.getString("rol");

                    String upd = "UPDATE usuarios SET fecha_hora_ultimo_inicio=? WHERE nombre=?";
                    try (PreparedStatement pu = c.prepareStatement(upd)) {
                        pu.setString(1, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                        pu.setString(2, nombre);
                        pu.executeUpdate();
                    }
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
