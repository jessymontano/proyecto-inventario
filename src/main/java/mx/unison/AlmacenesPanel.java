package mx.unison;

import mx.unison.Database;
import mx.unison.Almacen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AlmacenesPanel extends JPanel {
    private final Database db;
    private final Runnable onGoBack;
    private JTable table;
    private DefaultTableModel model;

    public AlmacenesPanel(Database db, Runnable onGoBack) {
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
                Almacen a = new Almacen();
                a.id = id;
                openForm(a);
            }
        });
        JButton del = new JButton("Eliminar");
        del.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r >= 0) {
                int id = (int) model.getValueAt(r, 0);
                int opt = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar el almacén?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    db.deleteAlmacen(id);
                    loadData();
                }
            }
        });
        top.add(back);
        top.add(add);
        top.add(edit);
        top.add(del);
        add(top, BorderLayout.NORTH);
    }

    private void initTable() {
        model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Ubicación", "Creado", "Últ.Mod", "Últ.Usuario"}, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadData() {
        model.setRowCount(0);
        List<Almacen> list = db.listAlmacenes();
        for (Almacen a : list) {
            model.addRow(new Object[]{a.id, a.nombre, a.ubicacion, a.fechaHoraCreacion, a.fechaHoraUltimaMod, a.ultimoUsuario});
        }
    }

    private void openForm(Almacen a) {
        JOptionPane.showMessageDialog(this, "Aquí se abriría el formulario de almacén (implemente FormAlmacen para edición detallada)");
        loadData();
    }
}
