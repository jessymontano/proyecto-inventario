package mx.unison.proyectoinventario.dao;

import mx.unison.proyectoinventario.model.Producto;
import mx.unison.proyectoinventario.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public List<Producto> listProductos() {
        List<Producto> out = new ArrayList<>();
        String sql = "SELECT p.id, p.nombre, p.descripcion, p.cantidad, p.precio, p.departamento, p.almacen_id, a.nombre as almacen_nombre, p.fecha_hora_creacion, p.fecha_hora_ultima_modificacion, p.ultimo_usuario_en_modificar " +
                "FROM productos p LEFT JOIN almacenes a ON p.almacen_id = a.id";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.id = rs.getInt("id");
                p.nombre = rs.getString("nombre");
                p.descripcion = rs.getString("descripcion");
                p.cantidad = rs.getInt("cantidad");
                p.precio = rs.getDouble("precio");
                p.departamento = rs.getString("departamento");
                p.almacenId = rs.getInt("almacen_id");
                p.almacenNombre = rs.getString("almacen_nombre");
                p.fechaCreacion = rs.getString("fecha_hora_creacion");
                p.fechaModificacion = rs.getString("fecha_hora_ultima_modificacion");
                p.ultimoUsuario = rs.getString("ultimo_usuario_en_modificar");
                out.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public int insertProducto(Producto prod, String usuario) {
        String sql = "INSERT INTO productos(nombre, descripcion, cantidad, precio, departamento, almacen_id, fecha_hora_creacion, ultimo_usuario_en_modificar) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, prod.nombre);
            ps.setString(2, prod.descripcion);
            ps.setInt(3, prod.cantidad);
            ps.setDouble(4, prod.precio);
            ps.setString(5, prod.departamento);
            if (prod.almacenId > 0) ps.setInt(6, prod.almacenId);
            else ps.setNull(6, Types.INTEGER);
            ps.setString(7, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            ps.setString(8, usuario);
            ps.executeUpdate();
            ResultSet g = ps.getGeneratedKeys();
            if (g.next()) return g.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateProducto(Producto prod, String usuario) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, cantidad=?, precio=?, departamento=?, almacen_id=?, fecha_hora_ultima_modificacion=?, ultimo_usuario_en_modificar=? " +
                "WHERE id=?";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, prod.nombre);
            ps.setString(2, prod.descripcion);
            ps.setInt(3, prod.cantidad);
            ps.setDouble(4, prod.precio);
            ps.setString(5, prod.departamento);
            if (prod.almacenId > 0) ps.setInt(6, prod.almacenId);
            else ps.setNull(6, Types.INTEGER);
            ps.setString(7, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            ps.setString(8, usuario);
            ps.setInt(9, prod.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";
        try (Connection c = DatabaseConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
