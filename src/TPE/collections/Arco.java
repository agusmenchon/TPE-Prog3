package TPE.collections;

import java.util.Comparator;

public class Arco<T> {

    private Vertice<T> verticeOrigen;
    private Vertice<T> verticeDestino;
    private int etiqueta;

    public Arco(Vertice<T> verticeOrigen, Vertice<T> verticeDestino, int etiqueta) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.etiqueta = etiqueta;
    }

    public Vertice<T> getVerticeOrigen() {
        return verticeOrigen;
    }

    public Vertice<T>  getVerticeDestino() {
        return verticeDestino;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

//    @Override
//    public int compare(T o1, T o2) {
//        Arco arco1 = (Arco) o1;
//        Arco arco2 = (Arco) o2;
//        if(arco1.getEtiqueta() > arco2.getEtiqueta()) return 1;
//        if(arco1.getEtiqueta() < arco2.getEtiqueta()) return -1;
//        return 0;
//    }

    @Override
    public boolean equals(Object o) {
        Arco<T> arco = (Arco<T>) o;
        if (this.getVerticeOrigen().equals(arco.getVerticeOrigen()) && this.getVerticeDestino().equals(arco.getVerticeDestino()) && this.getEtiqueta()==arco.getEtiqueta()){
            return true;
        }
        return false;
    }
}