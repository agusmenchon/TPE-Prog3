package TPE.collections;

public class Arco<T> {

    private Vertice<T> verticeOrigen;
    private Vertice<T> verticeDestino;
    private T etiqueta;

    public Arco(Vertice<T> verticeOrigen, Vertice<T> verticeDestino, T etiqueta) {
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

    public T getEtiqueta() {
        return etiqueta;
    }

    @Override
    public boolean equals(Object o) {
        Arco<T> arco = (Arco<T>) o;
        if (this.getVerticeOrigen().equals(arco.getVerticeOrigen()) && this.getVerticeDestino().equals(arco.getVerticeDestino()) && this.getEtiqueta().equals(arco.getEtiqueta())){
            return true;
        }
        return false;
    }

}