package TPE;

import TPE.datasets.CSVReader;
import TPE.algoritmos.Backtracking;
import TPE.algoritmos.Greedy;
import TPE.collections.*;
import util.Timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class main {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Ingrese el vertice inicial de los caminos: ");
//        String entrada = bufer.readLine();
//        int verticeInicial = Integer.parseInt(entrada);

        Timer timer = new Timer();

        System.out.println("--- Dataset 1 ---");
        GrafoNoDirigido<Integer> grafo = new GrafoNoDirigido<Integer>();
        String path1 = "src/TPE/datasets/dataset1.txt";
        CSVReader reader = new CSVReader(path1, grafo);
        reader.read();

        Greedy prim = new Greedy(grafo);
        timer.start();
        ArrayList<Arco<Integer>> solucion = prim.ejecutarGreedy(1);
        double time = timer.stop();
        print(solucion, time, prim.getCiclos(), prim.getDistanciaMenor(), "Algoritmo PRIM(Greedy)");

        Backtracking back = new Backtracking(grafo);
        timer.start();
        solucion.clear();
        solucion.addAll(back.backtracking(1));
        time = timer.stop();
        print(solucion, time, back.getCiclos(), back.getDistanciaActual(), "Algoritmo BACKTRACKING");


        System.out.println("---------- Dataset 2 ----------");
        String path2 = "src/TPE/datasets/dataset2.txt";
        grafo = new GrafoNoDirigido<>();
        reader = new CSVReader(path2, grafo);
        reader.read();

        prim = new Greedy(grafo);
        timer.start();
        solucion = prim.ejecutarGreedy(1);
        time = timer.stop();
        print(solucion, time, prim.getCiclos(), prim.getDistanciaMenor(), "Algoritmo PRIM(Greedy)");

        back = new Backtracking(grafo);
        timer.start();
        solucion = back.backtracking(1);
        time = timer.stop();
        print(solucion, time, back.getCiclos(), back.getDistanciaActual(), "Algoritmo BACKTRACKING");

        System.out.println("-------------------------- Dataset 3 --------------------------");
        grafo = new GrafoNoDirigido<>();
        String path3 = "src/TPE/datasets/dataset3.txt";
        reader = new CSVReader(path3, grafo);
        reader.read();

        prim = new Greedy(grafo);
        timer.start();
        solucion = prim.ejecutarGreedy(1);
        time = timer.stop();
        print(solucion, time, prim.getCiclos(), prim.getDistanciaMenor(), "Algoritmo PRIM(Greedy)");

        back = new Backtracking(grafo);
        timer.start();
        solucion = back.backtracking(1);
        time = timer.stop();
        print(solucion, time, back.getCiclos(), back.getDistanciaActual(), "Algoritmo BACKTRACKING");
    }

    public static <T> void mostrarGrafo (Grafo<T> grafo)
    {
        // Recorremos todos los vertices
        Iterator<Integer> it = grafo.obtenerVertices();

        while (it.hasNext()) {
            Integer v = (Integer) it.next();
            System.out.println("    " + v);
            // Recorremos todos los adyacentes de cada vertice
            Iterator<Arco<T>> itA = grafo.obtenerArcos(v);
            while (itA.hasNext()) {
                Arco<T> arco = itA.next();
                System.out.println("    " + v + "-> " + arco.getVerticeDestino() + " (" + arco.getEtiqueta() + ")");
            }
        }
    }

    public static void print(ArrayList<Arco<Integer>> solucion, double tiempo, Integer ciclos, Integer distancia, String metodo){
        System.out.println(metodo);
        for(Arco<Integer> arco : solucion){
            System.out.print("E"+arco.getVerticeOrigen()+"-"+"E"+arco.getVerticeDestino()+",");
        }
        System.out.println("\n"+distancia+" kms");
        System.out.println("la cantidad de ciclos realizados es: " + ciclos + " y el tiempo: " + tiempo + "ms\n");
    }
}