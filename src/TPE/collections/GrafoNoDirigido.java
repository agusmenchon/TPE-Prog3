package TPE.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GrafoNoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, HashSet<Arco<T>>> vertices;
    private int cantVertices;
    private int cantArcos;

    public GrafoNoDirigido() {
        this.vertices = new HashMap<Integer, HashSet<Arco<T>>>();
        this.cantVertices = cantVertices;
        this.cantArcos = cantArcos;
    }

    @Override
    public void agregarVertice(int verticeId) {
        if (!this.contieneVertice(verticeId)) {
            this.vertices.put(verticeId, new HashSet<>());
            cantVertices++;
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        if(!this.contieneVertice(verticeId)){
            //itero todos los arcos del verticeId y borro todos los arcos que tienen como origen VerticeID
            for(Iterator<Arco<T>> it = vertices.get(verticeId).iterator(); it.hasNext();){
                Arco<T> arco = (Arco<T>) it.next();
                this.borrarArco(verticeId, arco.getVerticeDestino().getId());
            }
            //itero todos los vertices adyacentes a "verticeId"
            for(Iterator<Integer> adyacentes = this.obtenerAdyacentes(verticeId); adyacentes.hasNext();) {
                Integer ady = (Integer) adyacentes.next();
                //itero todos los arcos del vertice adyacente en la iteracion.
                for (Iterator<Arco<T>> it = vertices.get(ady).iterator(); it.hasNext(); ) {
                    Arco<T> arco = (Arco<T>) it.next();
                    //si el arco tiene como destino a verticeID
                    if(arco.getVerticeDestino().equals(verticeId)){
                        //se elimina el arco.
                        this.borrarArco(ady,verticeId);
                    }
                }
            }
            this.vertices.remove(verticeId);
            cantVertices--;
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, int etiqueta) {
        if(!this.existeArco(verticeId1, verticeId2) && verticeId1!=verticeId2){
            Vertice<T> v1 = new Vertice<>(verticeId1);
            Vertice<T> v2 = new Vertice<>(verticeId2);
            Arco<T> arco = new Arco<>(v1, v2, etiqueta);
            Arco<T> arco2 = new Arco<>(v2,v1,etiqueta);
            vertices.get(verticeId1).add(arco);
            cantArcos++;
            vertices.get(verticeId2).add(arco2);
            //cantArcos++;
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
        Arco<T> arco2 = this.obtenerArco(verticeId2, verticeId1);
        if(arco!=null && arco2!= null){
            this.vertices.get(verticeId1).remove(arco);
            this.vertices.get(verticeId2).remove(arco2);
            cantArcos--;
            //cantArcos--;
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
        Arco<T> arco2 = this.obtenerArco(verticeId2,verticeId1);
        if(arco != null || arco2!=  null) {
            return true;
        }
        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        for(Iterator<Arco<T>> it = vertices.get(verticeId1).iterator(); it.hasNext();){
            Arco<T> arco = (Arco<T>) it.next();
            if(arco.getVerticeDestino().getId() == verticeId1){
                return arco;
            }
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return cantVertices;
    }

    @Override
    public int cantidadArcos() {
        return cantArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Integer> adyacentes = new ArrayList<>();
        for(Iterator<Arco<T>> it = vertices.get(verticeId).iterator(); it.hasNext();){
            Arco<T> arco = (Arco<T>) it.next();
            adyacentes.add(arco.getVerticeDestino().getId());
        }
        return adyacentes.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>();
        for(Iterator<Integer> it = this.obtenerVertices(); it.hasNext();){
            Integer vertice = (Integer) it.next();
            arcos.addAll(vertices.get(vertice));
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        if(this.contieneVertice(verticeId)){
            return vertices.get(verticeId).iterator();
        }
        return null;
    }

    @Override
    public void imprimir() {
        for (Iterator<Integer> iteratorV = this.obtenerVertices(); iteratorV.hasNext();) {
            Integer vertice = (Integer) iteratorV.next();
            System.out.print(vertice + ": ");
            for (Iterator<Integer> iteratorA = this.obtenerAdyacentes(vertice); iteratorA.hasNext();) {
                Integer ady = (Integer) iteratorA.next();
                System.out.print(ady + " ");
            }
            System.out.println();
        }
    }
}
