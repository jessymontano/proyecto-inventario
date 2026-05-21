package mx.unison.proyectoinventario;

import mx.unison.proyectoinventario.dao.UsuarioDAO;
import mx.unison.proyectoinventario.util.DatabaseConnection;
import mx.unison.proyectoinventario.view.Vistas;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection.initDatabase();

        // crear usuarios default
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insertDefaultUser("ADMIN", "admin23", "ADMIN");
        usuarioDAO.insertDefaultUser("PRODUCTOS", "productos19", "PRODUCTOS");
        usuarioDAO.insertDefaultUser("ALMACENES", "almacenes11", "ALMACENES");

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            Vistas vistas = new Vistas();
            vistas.setVisible(true);
        });
    }
}
