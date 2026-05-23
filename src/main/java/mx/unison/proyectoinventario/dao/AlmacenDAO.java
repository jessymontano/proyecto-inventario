package mx.unison.proyectoinventario.dao;

import mx.unison.proyectoinventario.model.Almacen;
import mx.unison.proyectoinventario.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlmacenDAO {
    /**
     * Obtiene la lista de almacenes de la base de datos.
     *
     * @return Lista de almacenes
     */
    public List<Almacen> listAlmacenes() {
        List<Almacen> out = new ArrayList<>();
        String sql = "SELECT id, nombre, ubicacion, fecha_hora_creacion, fecha_hora_ultima_modificacion, ultimo_usuario_en_modificar FROM almacenes";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Almacen a = new Almacen();
                a.id = rs.getInt("id");
                a.nombre = rs.getString("nombre");
                a.ubicacion = rs.getString("ubicacion");
                a.fechaHoraCreacion = rs.getString("fecha_hora_creacion");
                a.fechaHoraUltimaMod = rs.getString("fecha_hora_ultima_modificacion");
                a.ultimoUsuario = rs.getString("ultimo_usuario_en_modificar");
                out.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * Inserta un nuevo almacén en la base de datos con su fecha de creación.
     *
     * @param nombre Nombre del almacén
     * @param ubicacion Ubicación del almacén
     * @param usuario Nombre del usuario que realizó la acción
     * @return El id generado automáticamente por la base de datos si se inserta exitosamente, si no, -1
     */
    public int insertAlmacen(String nombre, String ubicacion, String usuario) {
        String sql = "INSERT INTO almacenes(nombre, ubicacion, fecha_hora_creacion, ultimo_usuario_en_modificar) VALUES(?,?,?,?)";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setString(2, ubicacion);
            ps.setString(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            ps.setString(4, usuario);
            ps.executeUpdate();
            ResultSet g = ps.getGeneratedKeys();
            if (g.next()) return g.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Modifica los datos de un almacén existente, genera la fecha de modificación e inserta el nombre del usuario que realizó la acción.
     *
     * @param id El id del almacén a modificar
     * @param nombre El nuevo nombre (o el nombre anterior si no se modificó)
     * @param ubicacion La nueva ubicación (o la ubicación anterior si no se modificó)
     * @param usuario El nombre del usuario que realizó la acción
     */
    public void updateAlmacen(int id, String nombre, String ubicacion, String usuario) {
        String sql = "UPDATE almacenes SET nombre=?, ubicacion=?, fecha_hora_ultima_modificacion=?, ultimo_usuario_en_modificar=? WHERE id=?";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, ubicacion);
            ps.setString(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            ps.setString(4, usuario);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un almacén de la base de datos.
     *
     * @param id El id del almacén a eliminar
     */
    public void deleteAlmacen(int id) {
        String sql = "DELETE FROM almacenes WHERE id=?";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
