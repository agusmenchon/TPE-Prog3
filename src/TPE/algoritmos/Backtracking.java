package TPE.algoritmos;

import TPE.collections.*;

import java.util.*;

public class Backtracking {
    private Grafo<?> grafo;
    private int distanciaMenor;
    private int ciclos;
    private ArrayList<Arco<Integer>> recorridoMinimo;

    public Backtracking(Grafo<?> grafo, int metros) {
        this.grafo = grafo;
        this.distanciaMenor = metros;
        this.ciclos=0;
    }

    public Backtracking(Grafo<?> grafo) {
        this.grafo = grafo;
        this.distanciaMenor = Integer.MAX_VALUE;
        this.recorridoMinimo = new ArrayList<>();
        this.ciclos = 0;
    }

    public int getDistanciaActual(){
        return this.distanciaMenor;
    }

    public int getCiclos() {
        return this.ciclos;
    }

    public ArrayList<Arco<Integer>> backtracking(int origen) {
        Integer distanciaActual = 0;

        ArrayList<Arco<Integer>> recorridoParcial = new ArrayList<>();
        ArrayList<Integer> noVisitados = new ArrayList<>();

        Iterator<Integer> vertices = grafo.obtenerVertices();
        while (vertices.hasNext()) {
            Integer v = vertices.next();
            noVisitados.add(v);
        }

        backtracking(recorridoParcial, noVisitados, origen, 0, null);

        return this.recorridoMinimo;
    }

    public void backtracking(ArrayList<Arco<Integer>> recorridoParcial, ArrayList<Integer> noVisitados, Integer vertice, Integer distanciaActual, Integer verticeAnterior) {

        noVisitados.remove(vertice);
        this.ciclos++;

        if (noVisitados.isEmpty() && distanciaActual < distanciaMenor) {
            distanciaMenor = distanciaActual;
            this.recorridoMinimo.clear();
            this.recorridoMinimo.addAll(recorridoParcial);
        }


        for (Integer VerticeNoVisitado : noVisitados) {
            Arco<Integer> arco = (Arco<Integer>) grafo.obtenerArco(vertice, VerticeNoVisitado);
            if(arco!=null){
                if (distanciaActual + arco.getEtiqueta() < this.distanciaMenor) {
                    recorridoParcial.add(arco);
                    backtracking(recorridoParcial, new ArrayList<>(noVisitados), VerticeNoVisitado, distanciaActual + arco.getEtiqueta(), vertice);
                    recorridoParcial.remove(arco);
                }
            }
        }

        if(verticeAnterior != null){
            backtracking(recorridoParcial, noVisitados, verticeAnterior, distanciaActual, null);
        }
    }
}


