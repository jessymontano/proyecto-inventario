package mx.unison.proyectoinventario;

import mx.unison.proyectoinventario.view.Vistas;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            Vistas vistas = new Vistas();
            vistas.setVisible(true);
        });
    }
}
