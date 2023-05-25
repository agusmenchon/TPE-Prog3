package TPE.collections;

//import TPE.collections.Vertice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, HashSet<Arco<T>>> vertices;
    private int cantVertices;
    private int cantArcos;

    public GrafoDirigido() {
        this.vertices = new HashMap<Integer, HashSet<Arco<T>>>();
        cantVertices = 0;
        cantArcos = 0;
    }

   
    @Override
    public void agregarVertice(int verticeId) {
        if (!this.contieneVertice(verticeId)) {
            this.vertices.put(verticeId, new HashSet<>());
            cantVertices++;
        }
    }

    /**
    * Complejidad: O(1) en promedio, donde la operación de inserción en el HashMap tiene una complejidad constante.
    * Sin embargo, en el peor caso, la complejidad puede ser O(N), donde N es el número total de vértices existentes en el grafo.
    * Esto se debe a que la inserción en el HashMap puede requerir la reorganización de la estructura subyacente del HashMap si alcanza su capacidad máxima.
    */

   
    @Override
    public void borrarVertice(int verticeId) {
        if(!this.contieneVertice(verticeId)){
            for(Iterator<Arco<T>> it = vertices.get(verticeId).iterator(); it.hasNext();){
                Arco<T> arco = (Arco<T>) it.next();
                this.borrarArco(verticeId, arco.getVerticeDestino().getId());
            }
            this.vertices.remove(verticeId);
            cantVertices--;
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
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(!this.existeArco(verticeId1, verticeId2) && verticeId1!=verticeId2){
            Vertice<T> v1 = new Vertice<>(verticeId1);
            Vertice<T> v2 = new Vertice<>(verticeId2);
            Arco<T> arco = new Arco<>(v1, v2, etiqueta);
            vertices.get(verticeId1).add(arco);
            cantArcos++;
        }
    }

    /**
    * Complejidad: O(log N), donde N es el número promedio de arcos adyacentes a un vértice.
    * El método agrega un arco entre dos vértices, siempre y cuando no exista un arco entre ellos y los vértices sean distintos.
    * Utiliza un HashMap para almacenar los arcos adyacentes a cada vértice, lo que proporciona un acceso eficiente y una complejidad de tiempo logarítmica en promedio.
    * En el peor caso, si el HashMap tuviera una colisión importante, la complejidad podría aumentar a O(N), donde N es la cantidad total de arcos almacenados.
    * Sin embargo, en promedio, la complejidad se mantiene en O(log N) debido a la estructura de datos subyacente utilizada.
    * Por lo tanto, la complejidad de tiempo del método es logarítmica en promedio, O(log N).
    */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
        if(arco!=null){
            this.vertices.get(verticeId1).remove(arco);
            cantArcos--;
        }
    }

    /**
    * Complejidad: O(log n)
    * Este método borra un arco entre dos vértices en el grafo implementado con un HashMap.
    * La complejidad logarítmica se debe a que se utiliza el método obtenerArco, que realiza una búsqueda
    * en el árbol binario de búsqueda correspondiente al vérticeId1, cuya complejidad es logarítmica.
    * Luego, se elimina el arco del conjunto de arcos asociados al vérticeId1, lo cual tiene una complejidad constante
    * (asumiendo que el conjunto de arcos es implementado eficientemente, por ejemplo, utilizando HashSet).
    */
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
     * Complejidad: O(V + A)
     * Este método imprime los vértices y sus adyacentes en el grafo implementado con un HashMap.
     * La complejidad depende de la cantidad de vértices (V) y arcos (A) en el grafo.
     * En el peor de los casos, se recorren todos los vértices y se accede a todos sus adyacentes.
     * Por lo tanto, la complejidad total es proporcional a la suma de V y A.
     */

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    /**
    * Complejidad: O(1)
    * Este método verifica si el grafo contiene un vértice específico utilizando un HashMap.
    * La complejidad constante se debe a que se utiliza el método containsKey del HashMap,
    * el cual realiza una búsqueda utilizando la función hash y devuelve un resultado en tiempo constante.
    */

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
        if(arco != null) {
            return true;
        }
        return false;
    }

      /**
     * Complejidad: O(log n)
     * Este método verifica si existe un arco entre dos vértices en el grafo implementado con un HashMap.
     * La complejidad logarítmica se debe a que se utiliza el método obtenerArco, que realiza una búsqueda
     * en el árbol binario de búsqueda correspondiente al vérticeId1, cuya complejidad es logarítmica.
     * Si el arco es encontrado, se devuelve true; de lo contrario, se devuelve false.
     */

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
    /**
    * Complejidad: O(n)
    * Este método obtiene un arco entre dos vértices en el grafo implementado con un HashMap.
    * La complejidad lineal se debe a que se recorre la lista de arcos asociados al verticeId1
    * y se verifica si el vértice destino del arco coincide con verticeId2.
    * En el peor de los casos, se recorren todos los arcos asociados al vérticeId1 antes de encontrar el arco buscado.
    */


    @Override
    public int cantidadVertices() {
        return cantVertices;
    }

    /**
     * Complejidad: O(1)
     * Este método devuelve la cantidad de vértices en el grafo implementado con un HashMap.
     * La complejidad es constante, ya que simplemente se devuelve el valor almacenado en la variable cantVertices.
     */

    @Override
    public int cantidadArcos() {
        return cantArcos;
    }
    /**
    * Complejidad: O(1)
    * Este método devuelve la cantidad de arcos en el grafo implementado con un HashMap.
    * La complejidad es constante, ya que simplemente se devuelve el valor almacenado en la variable cantArcos.
    */


    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    /**
     * Complejidad: O(1)
     * Este método devuelve un iterador sobre los vértices del grafo implementado con un HashMap.
     * La complejidad es constante, ya que simplemente se obtiene el conjunto de claves (vértices) del HashMap
     * y se devuelve un iterador sobre ese conjunto.
     */


    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Integer> adyacentes = new ArrayList<>();
        for(Iterator<Arco<T>> it = vertices.get(verticeId).iterator(); it.hasNext();){
            Arco<T> arco = (Arco<T>) it.next();
            adyacentes.add(arco.getVerticeDestino().getId());
        }
        return adyacentes.iterator();
    }

    /**
     * Complejidad: O(n)
     * Este método devuelve un iterador sobre los vértices adyacentes a un vértice dado en el grafo implementado con un HashMap.
     * La complejidad lineal se debe a que se recorre la lista de arcos asociados al vérticeId y se obtiene el vértice destino de cada arco.
     * En el peor de los casos, se recorren todos los arcos asociados al vérticeId para obtener sus vértices destino.
     */

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
    * Complejidad: O(V + A)
    * Este método devuelve un iterador sobre todos los arcos del grafo implementado con un HashMap.
    * La complejidad depende de la cantidad de vértices (V) y arcos (A) en el grafo.
    * En el peor de los casos, se recorren todos los vértices y se accede a todos sus arcos para agregarlos a la lista.
    * Luego, se crea un iterador sobre la lista de arcos y se devuelve.
    * Por lo tanto, la complejidad total es proporcional a la suma de V y A.
    */


    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        if(this.contieneVertice(verticeId)){
           return vertices.get(verticeId).iterator();
        }
        return null;
    }

    /**
    * Complejidad: O(log N) si el vértice existe y se utiliza un HashMap implementado con TreeMap, ya que se realiza una búsqueda en el árbol basado en el ID del vértice.
    */

}
