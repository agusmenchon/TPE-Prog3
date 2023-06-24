package TPE.servicios;

import TPE.collections.*;

import java.util.*;

public class Backtracking {
    private Grafo<?> grafo;
    private int menorMetros;


    public Backtracking(Grafo<?> grafo, int metros) {
        this.grafo = grafo;
        this.menorMetros = metros;
    }

    public Backtracking(Grafo<?> grafo){
        this.grafo = grafo;
        this.menorMetros = 999;
    }

    public List<Arco<?>> Backtracking(int vertice){
        //Creo las listas
        List<Arco<?>> recorridoMinimo = new ArrayList<>();
        List<Integer> visitados = new ArrayList<>();
        List<Arco<?>> caminoActual = new ArrayList<>();

        //inicializo las variables en 0 y menorMetros en infinito
        int metrosActuales = 0;
        int contVisitados = 1;
        visitados.add(vertice);

        //Llamo a backtracking para iniciar el algoritmo.
        Backtracking(recorridoMinimo,caminoActual, visitados, vertice, contVisitados, metrosActuales);
        return recorridoMinimo;
    }


    public void Backtracking(List<Arco<?>> recorridoMinimo, List<Arco<?>> caminoActual,
                             List<Integer> visitados, int vertice, int contVisitados,
                             int metrosActuales) {

        //si contVisitados == a la cantidad de vertices que tiene el grafo
        if (contVisitados == grafo.cantidadVertices()) {
//            if (metrosActuales < menorMetros) {
                menorMetros = metrosActuales;
                recorridoMinimo.clear();
                recorridoMinimo.addAll(caminoActual);
                for(Arco<?> arco : recorridoMinimo){
                    System.out.print("E"+arco.getVerticeOrigen()+"-"+"E"+arco.getVerticeDestino()+",");
                }
                System.out.println("\nLa suma de los caminos es: " + menorMetros);
//            }

        } else {
            //itero todos los arcos del vertice pasado por parametro (inicializado en 1)
            for (Iterator<?> iterador = grafo.obtenerArcos(vertice); iterador.hasNext(); ) {
                Arco<Integer> arco = (Arco<Integer>) iterador.next();
                int verticeDestino = arco.getVerticeDestino();

                if (!visitados.contains(verticeDestino)) {
                    visitados.add(verticeDestino);

                    //RESTRICCION IMPLICITA o poda del arbol
//                    if ((arco.getEtiqueta()+metrosActuales) < menorMetros) {
                        contVisitados++;
                        metrosActuales += arco.getEtiqueta();
                        caminoActual.add(arco);
                        //llamado recursivo a backtracking
                        Backtracking(recorridoMinimo, caminoActual, visitados, verticeDestino, contVisitados , metrosActuales);
//                    }
                }
                if (contVisitados == grafo.cantidadVertices()){
                    caminoActual.remove(arco);
                    metrosActuales = metrosActuales - arco.getEtiqueta();
                    visitados.remove(arco.getVerticeDestino());
                    contVisitados--;
                } 
            }
        }
    }
}
