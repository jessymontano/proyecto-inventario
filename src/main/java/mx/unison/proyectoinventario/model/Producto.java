package mx.unison.proyectoinventario.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa la entidad Producto en la base de datos
 */
public class Producto {
    public int id;
    public String nombre;
    public String descripcion;
    public int cantidad;
    public double precio;
    public String departamento;
    public int almacenId;
    public String almacenNombre;
    public String fechaCreacion;
    public String fechaModificacion;
    public String ultimoUsuario;

    public Producto() {}

    public Producto(int id, String nombre, String descripcion, int cantidad, double precio, String departamento, int almacenId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.departamento = departamento;
        this.almacenId = almacenId;
    }

    public Producto(String nombre, String descripcion, int cantidad, double precio, String departamento, int almacenId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.departamento = departamento;
        this.almacenId = almacenId;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getAlmacenId() {
        return almacenId;
    }

    public String getAlmacenNombre() {
        return almacenNombre;
    }

    public String getFechaCreacion() {
        return formatDate(this.fechaCreacion);
    }

    public String getFechaModificacion() {
        return formatDate(this.fechaModificacion);
    }

    public String getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setAlmacenId(int almacenId) {
        this.almacenId = almacenId;
    }

    public void setAlmacenNombre(String almacenNombre) {
        this.almacenNombre = almacenNombre;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }

    /**
     * Convierte fechas en formato ISO a formato dd/MM/yyyy hh:mm a
     *
     * @param isoDate Fecha en formato ISO String
     * @return Fecha en formato dd/MM/yyyy hh:mm a
     */
    private String formatDate(String isoDate) {
        if (isoDate == null  || isoDate.trim().isEmpty()) {
            return "";
        }
        try {
            LocalDateTime date = LocalDateTime.parse(isoDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

            return date.format(formatter);
        } catch (Exception e) {
            return isoDate;
        }
    }
}
