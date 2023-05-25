package TPE.servicios;
import java.sql.Array;
import java.util.*;

import TPE.collections.Grafo;
import java.util.List;
import TPE.collections.Arco;

public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;
    private List<List<Integer>> caminos;

//  Caminos: dado un origen, un destino y un límite “lim” retorna todos los caminos que, partiendo del
//  vértice origen, llega al vértice de destino sin pasar por más de “lim” arcos.
//  Aclaración importante: en un camino no se puede pasar 2 veces por el mismo arco.

//  Servicio caminos
    public ServicioCaminos(Grafo<?> grafo, int origen,int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
        this.caminos = new ArrayList<>();
    }

    /**
     * Complejidad:  O()
     */
    public List<List<Integer>> caminos() {
        List<Integer> caminoActual = new ArrayList<>();
        List<Arco<?>> visitados = new ArrayList<>();
        caminoActual.add(this.origen);
        caminos(visitados, caminoActual, origen, 1);
        //caminos(caminoActual, origen, 0);
        return this.caminos;
    }


    /**
     * Complejidad:  O()
     */
    private void caminos(List<Arco<?>> arcosVisitados, List<Integer> caminoActual, int verticeActual, int contador) {
        if(verticeActual==destino){
            //System.out.println("completo el camino de 3 a 2");
            //System.out.println(caminoActual.toString());
            List<Integer> aux = new ArrayList<>();
            aux.addAll(caminoActual);
            this.caminos.add(aux);

        } else{
            for(Iterator<?> it = grafo.obtenerArcos(verticeActual); it.hasNext();){
                Arco<?> arco = (Arco<?>) it.next();
                if(!arcosVisitados.contains(arco)) {
                    arcosVisitados.add(arco);
                    //System.out.println(arco.getVerticeOrigen().getId() + " " + arco.getVerticeDestino().getId());
                    if (contador < lim) {
                        int vecino = arco.getVerticeDestino().getId();
                        caminoActual.add(vecino);
                        caminos(arcosVisitados, caminoActual, vecino, contador+1);
                        caminoActual.remove(caminoActual.size()-1);
                    }
                    arcosVisitados.remove(arcosVisitados.size()-1);
                }
            }
        }
    }
}