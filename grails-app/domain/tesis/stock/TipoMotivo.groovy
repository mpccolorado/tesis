package tesis.stock;

public class TipoMotivo {
    String nombre;
    String descripcion;
    static hasMany = [subtipos:SubtipoMotivo]
}
