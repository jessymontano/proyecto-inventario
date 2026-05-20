package mx.unison;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelProductos extends JPanel{
    private final Database db;
    private final Runnable onGoBack;
    private JTable table;
    private DefaultTableModel model;

    public PanelProductos(Database db, Runnable onGoBack) {
        this.db = db;
        this.onGoBack = onGoBack;
        setLayout(new BorderLayout());
        initTop();
        initTable();
        loadData();
    }
    private void initTop() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton back = new JButton("Regresar");
        back.addActionListener(e -> onGoBack.run());
        JButton add = new JButton("Agregar");
        add.addActionListener(e -> openForm(null));
        JButton edit = new JButton("Modificar");
        edit.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r >= 0) {
                int id = (int) model.getValueAt(r, 0);
                Producto p = new Producto(); p.id = id; // simplificado, en form volveremos a cargar
                openForm(p);
            }
        });
        JButton del = new JButton("Eliminar");
        del.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r >= 0) {
                int id = (int) model.getValueAt(r, 0);
                int opt = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar el producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    db.deleteProducto(id);
                    loadData();
                }
            }
        });
        top.add(back); top.add(add); top.add(edit); top.add(del);
        add(top, BorderLayout.NORTH);
    }

    private void initTable() {
        model = new DefaultTableModel(new Object[]{"ID","Nombre","Descripción","Cantidad","Precio","Almacén","Creado","Últ.Mod","Últ.Usuario"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    private void loadData() {
        model.setRowCount(0);
        List<Producto> productos = db.listProductos();
        for (Producto p : productos) {
            model.addRow(new Object[]{p.id, p.nombre, p.descripcion, p.cantidad, p.precio, p.almacenNombre, p.fechaCreacion, p.fechaModificacion, p.ultimoUsuario});
        }
    }
    private void openForm(Producto p) {
        JOptionPane.showMessageDialog(this, "Aquí se abriría el formulario de producto (implemente FormProducto para edición detallada)");
        loadData();
    }
}
