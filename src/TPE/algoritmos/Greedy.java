package TPE.algoritmos;

import TPE.collections.*;
import java.util.*;


public class Greedy {
    private Grafo<?> grafo;
    private ArrayList<Arco<Integer>> recorrido;
    private Integer distanciaMenor;
    private int ciclos;

    public Greedy(Grafo<?> grafo) {
        this.grafo = grafo;
        this.recorrido = new ArrayList<>();
        this.distanciaMenor = 0;
    }

    public ArrayList<Arco<Integer>> ejecutarGreedy(int vertice){
        this.Prim(vertice);
        return this.recorrido;
    }

    public int getDistanciaMenor(){
        if(distanciaMenor!=0){
            return this.distanciaMenor;
        }
        return -1;
    }

    public int getCiclos() {
        return ciclos;
    }

    public void Prim(int vertice){
        ArrayList<Integer> verticesVisitados = new ArrayList<>();
        PriorityQueue<Arco<Integer>> cola = new PriorityQueue<>(Comparator.comparingInt(Arco::getEtiqueta));

        verticesVisitados.add(vertice);

        seleccion(cola, vertice, verticesVisitados);
        int cont = 0;
        while(!cola.isEmpty() && cont < this.grafo.cantidadVertices()){

            Arco<Integer> arcoActual = cola.poll();
            int verticeDestino = arcoActual.getVerticeDestino();

            if(!verticesVisitados.contains(verticeDestino)){
                this.recorrido.add(arcoActual);
                distanciaMenor += arcoActual.getEtiqueta();
                verticesVisitados.add(verticeDestino);
                vertice = verticeDestino;
                cont++;
                seleccion(cola,vertice,verticesVisitados);
            }
            ciclos++;
        }
    }

    public void seleccion(PriorityQueue cola, int vertice, ArrayList<Integer> visitados){
        for(Iterator<?> it = grafo.obtenerArcos(vertice); it.hasNext();){
            Arco<?> arco = (Arco<?>) it.next();
            if(!visitados.contains(arco.getVerticeDestino())){
                cola.add(arco);
            }
        }
    }
}
