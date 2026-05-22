package mx.unison.proyectoinventario.model;

public class Producto {
    public int id;
    public String nombre;
    public String descripcion;
    public int cantidad;
    public double precio;
    public int almacenId;
    public String almacenNombre;
    public String fechaCreacion;
    public String fechaModificacion;
    public String ultimoUsuario;

    public Producto() {}

    public Producto(int id, String nombre, String descripcion, int cantidad, double precio, int almacenId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.almacenId = almacenId;
    }

    public Producto(String nombre, String descripcion, int cantidad, double precio, int almacenId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
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

    public int getAlmacenId() {
        return almacenId;
    }

    public String getAlmacenNombre() {
        return almacenNombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
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
}
