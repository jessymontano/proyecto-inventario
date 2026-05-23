package mx.unison.proyectoinventario.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gestiona la conexión principal a la base de datos SQLite.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:Inventario.db";

    /**
     * Genera una conexión a la base de datos SQLite
     *
     * @return Objeto Connection para utilizar la base de datos o null si falla
     * @throws SQLException Si ocurre un error al intentar abrir la conexión
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Crea las tablas de la base de datos si no han sido creadas
     */
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
        } catch (SQLException e) {
            System.err.println("Error inicializando la base de datos: "  + e.getMessage());
        }
    }
}
