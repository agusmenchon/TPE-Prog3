package TPE.collections;

//import TPE.collections.Vertice;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, HashSet<Arco<T>>> vertices;
    private int cantVertices;
    private int cantArcos;

    public GrafoDirigido() {
        this.vertices = new HashMap<Integer, HashSet<Arco<T>>>();
        cantVertices = 0;
        cantArcos = 0;
    }

    /* Autores:
    * Menchon, Agustin - amenchon@alumnos.exa.unicen.edu.ar
    * Viviant, Joel - joviviant@alumnos.exa.unicen.edu.ar */

    /**
     * Complejidad: O(1) en promedio, donde la operación de inserción en el HashMap tiene una complejidad constante.
     * Sin embargo, en el peor caso, la complejidad puede ser O(N), donde N es el número total de vértices existentes en el grafo.
     * Esto se debe a que la inserción en el HashMap puede requerir la reorganización de la estructura subyacente del HashMap si alcanza su capacidad máxima.
     */
    @Override
    public void agregarVertice(int verticeId) {
        if (!this.contieneVertice(verticeId)) {
            this.vertices.put(verticeId, new HashSet<>());
            cantVertices++;
        }
    }

    /**
     * Complejidad: O(n)
     * Este método elimina un vértice del grafo implementado con un HashMap.
     * La complejidad lineal se debe a que se debe recorrer la lista de arcos del vértice a eliminar y llamar al método
     * borrarArco para cada arco. Además, se realiza la eliminación del vértice del HashMap, lo cual también tiene una
     * complejidad lineal en el peor de los casos.
     */
    @Override
    public void borrarVertice(int verticeId) {
        /** Creo una lista para guardar los arcos y eliminarlos (ya que no encontre forma de realizarlo con el iterator) **/

        ArrayList<Arco<T>> arcos = new ArrayList<>();
        //Borrar los arcos ENTRANTES al vertice borrado (verticeId)
        //Borrar los arcos salientes del vertice a borrar
        //borrar el vertice pasado por parametro

        if(this.contieneVertice(verticeId)){
//            for(Arco<T> arco : this.vertices.get(verticeId)){
//                System.out.println(arco.toString());
//                arcos.add(arco);
//            }
            for(Iterator<Arco<T>> it2 = this.vertices.get(verticeId).iterator(); it2.hasNext();){
                Arco<T> arco = (Arco<T>) it2.next();
                //this.borrarArco(verticeId, arco.getVerticeDestino());
                arcos.add(arco);
            }

            /** itero todos los vertices del grafo buscando que alguno sea adyacente pero que tenga como DESTINO a "verticeID" **/
            for (Iterator<Integer> v = this.obtenerVertices(); v.hasNext();) {
                Integer verticeOrigen = (Integer) v.next();
                //si el vertice iterado es == a vertice a borrar sigo con otra iteracion.
                if(verticeOrigen.equals(verticeId)){
                    continue;
                }
                /** si el arco obtenido es real quiere decir que es distinto de null
                en ese caso daria afirmativo de que es un arco entrante al vertice a borrar. **/
                Arco<T> arco = this.obtenerArco(verticeOrigen,verticeId);
                if(arco!=null){
                    arcos.add(arco);
                }
            }

            for(Arco<T> arco : arcos){
                System.out.println(arco.toString());
                this.borrarArco(arco.getVerticeOrigen(), arco.getVerticeDestino());
            }
            this.vertices.remove(verticeId);
            cantVertices--;
        }
    }

    /**
     * Complejidad: O(log N), donde N es el número promedio de arcos adyacentes a un vértice.
     * Utiliza un HashMap para almacenar los arcos adyacentes a cada vértice, lo que proporciona un acceso eficiente y una complejidad de tiempo logarítmica en promedio.
     * En el peor caso, si el HashMap tuviera una colisión importante, la complejidad podría aumentar a O(N), donde N es la cantidad total de arcos almacenados.
     * Sin embargo, en promedio, la complejidad se mantiene en Oa(log N) debido la estructura de datos subyacente utilizada.
     * Por lo tanto, la complejidad de tiempo del método es logarítmica en promedio, O(log N). */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)) {
            if (!this.existeArco(verticeId1, verticeId2) && verticeId1 != verticeId2) {
                Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
                vertices.get(verticeId1).add(arco);
                cantArcos++;
            }
        }
    }

    /**
     * Complejidad: O(log n)
     * La complejidad logarítmica se debe a que se utiliza el método obtenerArco, que realiza una búsqueda
     * correspondiente al vérticeId1, cuya complejidad es logarítmica. Luego, se elimina el arco del conjunto
     * de arcos asociados al vérticeId1,lo cual tiene una complejidad constante */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
        if(arco!=null){
            this.vertices.get(verticeId1).remove(arco);
            cantArcos--;
        }
    }

    /**
     * Complejidad: O(V + A)
     * La complejidad depende de la cantidad de vértices (V) y arcos (A) en el grafo.
     * En el peor de los casos, se recorren todos los vértices y se accede a todos sus adyacentes.
     * Por lo tanto, la complejidad total es proporcional a la suma de V y A. */
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

    /**
     * Complejidad: O(1)
     * La complejidad constante se debe a que se utiliza el método containsKey del HashMap,
     * el cual realiza una búsqueda utilizando la función hash y devuelve un resultado en tiempo constante. */
    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    /**
     * Complejidad: O(log n)
     * La complejidad logarítmica se debe a que se utiliza el método obtenerArco, que realiza una búsqueda
     * correspondiente al vérticeId1, cuya complejidad es logarítmica. */
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
        if(arco != null) {
            return true;
        }
        return false;
    }

    /**
     * Complejidad: O(n)
     * La complejidad lineal se debe a que se recorre la lista de arcos asociados al verticeId1
     * y se verifica si el vértice destino del arco coincide con verticeId2.
     * En el peor de los casos, se recorren todos los arcos asociados al vérticeId1 antes de encontrar el arco buscado. */
    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        for(Iterator<Arco<T>> it = vertices.get(verticeId1).iterator(); it.hasNext();){
            Arco<T> arco = (Arco<T>) it.next();
            if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)){
                /** se cambio el "==" por el equals para que de verdadero (al ser un Integer no funcionaba) y devuelva el Arco **/
                return arco;
            }
        }
        return null;
    }


    /**
     * Complejidad: O(1)
     * La complejidad es constante, ya que simplemente se devuelve el valor almacenado en la variable cantVertices. */
    @Override
    public int cantidadVertices() {
        return cantVertices;
    }

    /**
     * Complejidad: O(1)
     * La complejidad es constante, ya que simplemente se devuelve el valor almacenado en la variable cantArcos. */
    @Override
    public int cantidadArcos() {
        return cantArcos;
    }


    /**
     * Complejidad: O(1)
     * La complejidad es constante, ya que simplemente se obtiene el conjunto de claves (vértices) del HashMap
     * y se devuelve un iterador sobre ese conjunto. */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    /**
     * Complejidad: O(n)
     * La complejidad lineal se debe a que se recorre la lista de arcos asociados al vérticeId y se obtiene el vértice destino de cada arco.
     * En el peor de los casos, se recorren todos los arcos asociados al vérticeId para obtener sus vértices destino. */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        //TODO REVISAR!
        ArrayList<Integer> adyacentes = new ArrayList<>();
        for(Iterator<Arco<T>> it = vertices.get(verticeId).iterator(); it.hasNext();){
            Arco<T> arco = (Arco<T>) it.next();
            adyacentes.add(arco.getVerticeDestino());
        }
        return adyacentes.iterator();
    }

    /**
     * Complejidad: O(V + A)
     * La complejidad depende de la cantidad de vértices (V) y arcos (A) en el grafo.
     * En el peor de los casos, se recorren todos los vértices y se accede a todos sus arcos para agregarlos a la lista.
     * Luego, se crea un iterador sobre la lista de arcos y se devuelve.
     * Por lo tanto, la complejidad total es proporcional a la suma de V y A. */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>();
        for(Iterator<Integer> it = this.obtenerVertices(); it.hasNext();){
            Integer vertice = (Integer) it.next();
            arcos.addAll(vertices.get(vertice));
        }
        return arcos.iterator();
    }

    /**
     * Complejidad: O(1) ya que la verificacion si el grafo contiene el vertice es O(1) y la obtencion del conjunto tambien
     * se realiza en tiempo constante por las propiedades de HashMap*/
    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        if(this.contieneVertice(verticeId)){
           return vertices.get(verticeId).iterator();
        }
        return null;
    }

}
