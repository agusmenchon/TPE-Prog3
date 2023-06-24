package TPE.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GrafoNoDirigido<T> extends GrafoDirigido<T> {

    public GrafoNoDirigido() {
        super();
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        super.agregarArco(verticeId1,verticeId2,etiqueta);
        super.agregarArco(verticeId2,verticeId1,etiqueta);
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        super.borrarArco(verticeId1, verticeId2);
        super.borrarArco(verticeId2, verticeId1);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
        if(arco!=null){
            Arco<T> arco2 = this.obtenerArco(verticeId2,verticeId1);
            if(arco2!=null){
                return true;
            }
        }
        return false;
    }

    @Override
    public int cantidadArcos() {
        return super.cantArcos / 2;
    }
}
