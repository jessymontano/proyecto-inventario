package mx.unison.proyectoinventario.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:Inventario.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initDatabase() {
        try (Connection c = connect(); Statement st = c.createStatement()) {
            // Tabla usuarios
            st.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "password TEXT NOT NULL," +
                    "fecha_hora_ultimo_inicio TEXT," +
                    "rol TEXT NOT NULL)");

            // Tabla almacenes
            st.execute("CREATE TABLE IF NOT EXISTS almacenes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "ubicacion TEXT," +
                    "fecha_hora_creacion TEXT," +
                    "fecha_hora_ultima_modificacion TEXT," +
                    "ultimo_usuario_en_modificar TEXT)");

            // Tabla productos
            st.execute("CREATE TABLE IF NOT EXISTS productos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "precio REAL DEFAULT 0.0," +
                    "cantidad INTEGER NOT NULL," +
                    "departamento TEXT NOT NULL," +
                    "almacen_id INTEGER," +
                    "descripcion TEXT," +
                    "fecha_hora_creacion INTEGER DEFAULT 0," +
                    "fecha_hora_ultima_modificacion TEXT," +
                    "ultimo_usuario_en_modificar TEXT)");

//            // Insertar usuarios base si no existen
//            insertDefaultUser("ADMIN", "admin23", "ADMIN");
//            insertDefaultUser("PRODUCTOS", "productos19", "PRODUCTOS");
//            insertDefaultUser("ALMACENES", "almacenes11", "ALMACENES");
//            // Establecer fecha de creación para productos/almacenes existentes si están vacíos
//            setDefaultFechaCreacionIfEmpty("productos");
//            setDefaultFechaCreacionIfEmpty("almacenes");
        } catch (SQLException e) {
            System.err.println("Error inicializando la base de datos: "  + e.getMessage());
        }
    }
}
