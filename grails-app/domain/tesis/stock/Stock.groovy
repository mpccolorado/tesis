package tesis.stock;

import tesis.articulo.ArticuloComprado;

public class Stock {

    //Integer existenciaActual;
    Integer existenciaMin;
    Integer existenciaMax;
    ArticuloComprado articuloComprado;
    static hasMany = [lotes:Lote]

    public Integer getExistenciaActual(){
        Integer total = 0;
        for(Lote lote : lotes){
            total += lote.getCantidad();
        }
        return total;
    }
}
