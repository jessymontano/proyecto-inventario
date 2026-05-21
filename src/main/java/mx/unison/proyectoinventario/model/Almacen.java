package mx.unison.proyectoinventario.model;

public class Almacen {
    public int id;
    public String nombre;
    public String ubicacion;
    public String fechaHoraCreacion;
    public String fechaHoraUltimaMod;
    public String ultimoUsuario;

    public Almacen() {}

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public String getFechaHoraUltimaMod() {
        return fechaHoraUltimaMod;
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

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setFechaHoraCreacion(String fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public void setFechaHoraUltimaMod(String fechaHoraUltimaMod) {
        this.fechaHoraUltimaMod = fechaHoraUltimaMod;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }
}
