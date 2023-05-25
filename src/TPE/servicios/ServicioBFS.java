package TPE.servicios;
import TPE.collections.Grafo;

import java.lang.reflect.Array;
import java.util.*;

public class ServicioBFS {

    private Grafo<?> grafo;
    private HashMap<Integer, Boolean> visitados;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
        this.visitados = new HashMap<>();
    }

    /**
     * Complejidad:  O()
     */
    public List<Integer> bfsForest() {
        ArrayList<Integer> lista = new ArrayList<>();
        if(visitados!=null){
            for(Iterator<Integer> iterador = grafo.obtenerVertices(); iterador.hasNext();){
                Integer vertice = (Integer) iterador.next();
                this.visitados.put(vertice, false);
            }
        }

        // Resolver BFS
        for(Iterator<Integer> iterador = grafo.obtenerVertices(); iterador.hasNext();){
            Integer vertice = (Integer) iterador.next();
            if(visitados.get(vertice).equals(false)){
                lista.addAll(bfsForest(vertice));
            }
        }

        return lista;
    }

    /**
     * Complejidad:  O()
     */
    public List<Integer> bfsForest(int vertice){
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();
        visitados.put(vertice, true);
        aux.add(vertice);
        end.add(vertice);
        while(!aux.isEmpty()){
            for (Iterator<Integer> ady = grafo.obtenerAdyacentes(aux.get(0)); ady.hasNext();){
                Integer adyacente = (Integer) ady.next();
                if (!visitados.get(adyacente)){
                    visitados.put(adyacente, true);
                    aux.add(adyacente);
                    end.add(adyacente);
                }
            }
            aux.remove(0);
        }
        return end;
    }

}