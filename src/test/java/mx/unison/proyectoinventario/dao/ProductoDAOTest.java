package mx.unison.proyectoinventario.dao;

import mx.unison.proyectoinventario.model.Producto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoDAOTest {
    @Test
    public void testFullCRUD_Producto() {
        ProductoDAO productoDAO = new ProductoDAO();
        String timestamp = String.valueOf(System.currentTimeMillis());

        Producto producto = new Producto();
        producto.nombre = "Producto Test " + timestamp;
        producto.descripcion = "Test";
        producto.cantidad = 10;
        producto.precio = 15000.50;
        producto.departamento = "Electrónica";
        producto.almacenId = 0;

        String user = "TEST";

        // tests create
        int id = productoDAO.insertProducto(producto, user);
        assertTrue(id > 0, "Al insertar se debe regresar el id generado automáticamente");

        // tests read
        List<Producto> productos = productoDAO.listProductos();
        Producto insertedProducto = null;
        for (Producto p : productos) {
            if (p.id == id) {
                insertedProducto = p;
                break;
            }
        }

        assertNotNull(insertedProducto, "El producto debe existir en la base de datos");
        assertEquals(producto.nombre, insertedProducto.nombre, "El nombre debe coincidir");
        assertEquals(producto.precio, insertedProducto.precio, "El precio debe guardarse correctamente con decimales");
        assertEquals(producto.cantidad, insertedProducto.cantidad, "El stock debe guardarse correctamente");

        // tests update
        insertedProducto.cantidad = 15;
        insertedProducto.precio = 14000.00;
        productoDAO.updateProducto(insertedProducto, user);

        List<Producto> updatedProductos = productoDAO.listProductos();
        Producto updatedProducto = null;
        for (Producto p : updatedProductos) {
            if (p.id == id) {
                updatedProducto = p;
                break;
            }
        }

        assertNotNull(updatedProducto);
        assertEquals(15, updatedProducto.cantidad, "La cantidad debió actualizarse a 15");
        assertEquals(14000.00, updatedProducto.precio, "El precio debió actualizarse a 14000.00");
        assertNotNull(updatedProducto.fechaModificacion, "La fecha de modificación debió registrarse");

        // tests delete
        productoDAO.deleteProducto(id);

        List<Producto> deletedProductos = productoDAO.listProductos();
        boolean exists = false;
        for (Producto p : deletedProductos) {
            if (p.id == id) {
                exists = true;
                break;
            }
        }
        assertFalse(exists, "El producto debió ser eliminado de la base de datos");
    }
}
