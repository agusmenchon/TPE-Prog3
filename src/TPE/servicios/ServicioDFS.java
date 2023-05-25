package TPE.servicios;

import TPE.collections.Grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {

    private Grafo<?> grafo;
    private HashMap<Integer, String> vertices;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
        this.vertices = new HashMap<>();
    }


   /**
 * Complejidad: O(V + A)
 * Este método realiza un recorrido DFS (Depth-First Search) en el grafo implementado con un HashMap y devuelve un bosque DFS.
 * Utiliza una implementación iterativa del algoritmo DFS.
 * La complejidad depende de la cantidad de vértices (V) y arcos (A) en el grafo.
 * En el peor de los casos, se visita cada vértice y cada arco una vez durante la ejecución del algoritmo.
 * La complejidad total del método es O(V + A).
 */
    public List<Integer> dfsForest() {
        // Resolver DFS()
        ArrayList<Integer> lista = new ArrayList<Integer>();
        //agrego todos los vertices del grafo al map vertices y los inicializo en BLANCO.
        if(vertices!=null){
            for(Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext();){
                Integer vertice = (Integer) it.next();
                this.vertices.put(vertice, "BLANCO");
            }
        }

        //int tiempo = 0;
        for(Iterator<Integer> iterador = grafo.obtenerVertices(); iterador.hasNext();){
            Integer vertice = (Integer) iterador.next();
            if(vertices.get(vertice).equals("BLANCO")){
                lista.addAll(dfsForest(vertice));
            }
        }

        return lista;
    }

    /**
 * Complejidad: O(V + A)
 * Este método realiza un recorrido DFS (Depth-First Search) en el grafo implementado con un HashMap y devuelve un subárbol DFS a partir de un vértice dado.
 * Utiliza una implementación recursiva del algoritmo DFS.
 * La complejidad depende de la cantidad de vértices (V) y arcos (A) en el grafo.
 * En el peor de los casos, se visita cada vértice y cada arco una vez durante la ejecución del algoritmo.
 * La complejidad total del método es O(V + A).
 */
    public List<Integer> dfsForest(Integer vertice){
        ArrayList<Integer> lista = new ArrayList<Integer>();
        this.vertices.put(vertice, "AMARILLO");
        lista.add(vertice);
        for (Iterator<Integer> it = grafo.obtenerAdyacentes(vertice); it.hasNext();){
            Integer ady = (Integer) it.next();
            if(this.vertices.get(ady).equals("BLANCO")){
                lista.addAll(dfsForest(ady));
            }
        }
        this.vertices.put(vertice, "NEGRO");
        return lista;
    }

}
