package tesis.articulo;

public class ArticuloComprado extends Articulo {
    Boolean seVende;
    Boolean afectaStock;
    Marca marca;
    Subrubro subrubro;

    @Override
    public String toString(){
        return nombre;
    }
}
