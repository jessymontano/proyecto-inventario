package mx.unison;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel{
    public Home(Runnable onOpenProductos, Runnable onOpenAlmacenes) {
        setLayout(new BorderLayout());
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        JLabel title = new JLabel("Sistema de Inventario");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnProd = new JButton("Productos");
        btnProd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnProd.addActionListener(e -> onOpenProductos.run());

        JButton btnAlm = new JButton("Almacenes");
        btnAlm.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAlm.addActionListener(e -> onOpenAlmacenes.run());

        center.add(title);
        center.add(Box.createRigidArea(new Dimension(0,20)));
        center.add(btnProd);
        center.add(Box.createRigidArea(new Dimension(0,10)));
        center.add(btnAlm);

        add(center, BorderLayout.CENTER);
    }
}
