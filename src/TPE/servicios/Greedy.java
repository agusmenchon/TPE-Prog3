package TPE.servicios;

import TPE.collections.*;
import java.util.*;


public class Greedy {
    private Grafo<?> grafo;
    private List<Arco<?>> recorrido;
    private int sumaEtiqueta;

    public Greedy(Grafo<?> grafo) {
        this.grafo = grafo;
        this.recorrido = new ArrayList<>();
        this.sumaEtiqueta = 0;
    }

    public void ejecutarGreedy(int vertice){
        this.Prim(vertice);
        System.out.println("Algoritmo PRIM");
        for(Arco<?> arco : recorrido){
            System.out.print("E"+arco.getVerticeOrigen().getId()+"-"+"E"+arco.getVerticeDestino().getId()+",");
        }
        System.out.println("\nLa suma de los caminos es: " + sumaEtiqueta);
    }

    public int getSumaEtiqueta(){
        if(sumaEtiqueta!=0){
            return this.sumaEtiqueta;
        }
        return -1;
    }

    public void Prim(int vertice){
        ArrayList<Integer> verticesVisitados = new ArrayList<>();
        PriorityQueue<Arco<?>> cola = new PriorityQueue<>(Comparator.comparingInt(Arco::getEtiqueta));

        verticesVisitados.add(vertice);

        seleccion(cola, vertice, verticesVisitados);
        int cont = 0;
        while(!cola.isEmpty() && cont < this.grafo.cantidadVertices()){
            Arco<?> arcoActual = cola.poll();
            int verticeDestino = arcoActual.getVerticeDestino().getId();
            if(!verticesVisitados.contains(verticeDestino)){
                recorrido.add(arcoActual);
                sumaEtiqueta += arcoActual.getEtiqueta();
                verticesVisitados.add(verticeDestino);
                vertice = arcoActual.getVerticeDestino().getId();
                cont++;
                seleccion(cola,vertice,verticesVisitados);
            }
        }
    }

    public void seleccion(PriorityQueue cola, int vertice, ArrayList<Integer> visitados){
        for(Iterator<?> it = grafo.obtenerArcos(vertice); it.hasNext();){
            Arco<?> arco = (Arco<?>) it.next();
            if(!visitados.contains(arco.getVerticeDestino().getId())){
                cola.add(arco);
            }
        }
    }
}
