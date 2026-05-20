package mx.unison;

import javax.swing.*;
import java.awt.*;

public class Vistas extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel container = new JPanel(cardLayout);
    private final Database db = new Database();

    public Vistas() {
        setTitle("Sistema de Inventario - Cliente");
        setSize(1000, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Login login = new Login(user -> showHome(user));
        Home home = new Home(() -> showPanel("PRODUCTOS"), () -> showPanel("ALMACENES"));
        PanelProductos productos = new PanelProductos(db, () -> showPanel("INICIO"));
        AlmacenesPanel almacenes = new AlmacenesPanel(db, () -> showPanel("INICIO"));

        container.add(login, "LOGIN");
        container.add(home, "INICIO");
        container.add(productos, "PRODUCTOS");
        container.add(almacenes, "ALMACENES");

        add(container);
        cardLayout.show(container, "LOGIN");
    }
    private void showHome(String usuarioNombre) {
        cardLayout.show(container, "INICIO");
    }
    private void showPanel(String name) {
        cardLayout.show(container, name);
    }
}
