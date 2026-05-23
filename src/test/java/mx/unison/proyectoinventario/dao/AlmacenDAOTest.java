package mx.unison.proyectoinventario.dao;

import mx.unison.proyectoinventario.model.Almacen;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlmacenDAOTest {
    @Test
    public void testFullCRUD_Almacen() {
        AlmacenDAO almacenDAO = new AlmacenDAO();

        // agregar tiempo para que cada almacén sea único
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nombre = "Almacen " + timestamp;
        String ubicacion = "Calle " + timestamp;
        String user = "TEST";

        // tests create
        int id = almacenDAO.insertAlmacen(nombre, ubicacion, user);
        assertTrue(id > 0, "Al insertar se debe regresar el id generado automáticamente");

        // tests read
        List<Almacen> almacenes = almacenDAO.listAlmacenes();
        Almacen insertedAlmacen = null;

        for (Almacen a : almacenes) {
            if (a.id == id) {
                insertedAlmacen = a;
                break;
            }
        }

        assertNotNull(insertedAlmacen, "El almacén recién creado debe aparecer en la lista");
        assertEquals(nombre, insertedAlmacen.nombre, "El nombre debe coincidir con el insertado");
        assertEquals(ubicacion, insertedAlmacen.ubicacion, "La ubicación debe coincidir");
        assertNotNull(insertedAlmacen.fechaHoraCreacion, "La fecha de creación no debe ser nula");

        // tests update
        String updatedNombre = "Almacén modificado " + timestamp;
        almacenDAO.updateAlmacen(id, updatedNombre, ubicacion, user);

        List<Almacen> updatedAlmacenes = almacenDAO.listAlmacenes();
        Almacen updatedAlmacen = null;
        for (Almacen a : updatedAlmacenes) {
            if (a.id == id) {
                updatedAlmacen = a;
                break;
            }
        }

        assertNotNull(updatedAlmacen, "El almacén debe seguir existiendo en la lista");
        assertEquals(updatedNombre, updatedAlmacen.nombre, "El nombre debió actualizarse en la base de datos");
        assertNotNull(updatedAlmacen.fechaHoraUltimaMod, "La fecha de última modificación se debió registrar");

        // tests delete
        almacenDAO.deleteAlmacen(id);

        List<Almacen> deletedAlmacenes = almacenDAO.listAlmacenes();
        boolean exists = false;
        for (Almacen a : deletedAlmacenes) {
            if (a.id == id) {
                exists = true;
                break;
            }
        }

        assertFalse(exists, "El almacén debió desaparecer de la lista tras ser eliminado");
    }

    @Test
    public void testDeleteAlmacen_AlmacenDoesntExist_DoesntThrowException() {
        AlmacenDAO almacenDAO = new AlmacenDAO();

        assertDoesNotThrow(() -> {
            almacenDAO.deleteAlmacen(-5);
        }, "Borrar un id inexistente no lanza excepción");
    }
}
