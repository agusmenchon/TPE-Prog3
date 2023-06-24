package TPE;

import TPE.servicios.*;
import TPE.collections.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class main {
    public static void main(String[] args) {
//        Grafo<Integer> g = new GrafoNoDirigido<Integer>();
//        //agregado de vertices
//        g.agregarVertice(1); //a
//        g.agregarVertice(2); //b
//        g.agregarVertice(3); //c
//        g.agregarVertice(4); //d
//        g.agregarVertice(5); //e
//        g.agregarVertice(6); //f
//        g.agregarVertice(7); //g
//        g.agregarVertice(8); //h
//        g.agregarVertice(9); //i
//        g.agregarVertice(10); //j
//
////      agregado de arcos
//        g.agregarArco(1,4,4);
//        g.agregarArco(1,7,50);
//        g.agregarArco(1,2,70);
//        //g.agregarArco(2,4,null);
//        g.agregarArco(2,5,60);
//        g.agregarArco(2,8,50);
//        g.agregarArco(2,3,24);
//        g.agregarArco(3,9,59);
//        g.agregarArco(3,2,29);
//        g.agregarArco(3,6,34);
//        g.agregarArco(3,7,14);
//        //g.agregarArco(4,2,null);
//        g.agregarArco(4,8,25);
//        g.agregarArco(5,1,98);
//        g.agregarArco(6,1,85);
//        g.agregarArco(7,10,74);
//        g.agregarArco(8,3,23);
//        g.agregarArco(8,5,29);
//        g.agregarArco(9,4,32);
//        g.agregarArco(10,2,104);
////
//        System.out.println("Grafo:");
//        mostrarGrafo(g);
//
//        System.out.println("Obtener arco Origen: 10 y Destino 2:");
//        Arco<Integer> arco = g.obtenerArco(10,2);
//        System.out.println(arco.toString());
//
//        System.out.println("Borrado de vertice: 8");
//        g.borrarVertice(8);
//        System.out.println("Borrando....");
//        mostrarGrafo(g);
//
//        System.out.println("\nRecorrido DFS:");
//        ServicioDFS dfs = new ServicioDFS(g);
//        System.out.println(dfs.dfsForest().toString());
//
//        System.out.println("\nRecorrido BFS:");
//        ServicioBFS bfs = new ServicioBFS(g);
//        System.out.println(bfs.bfsForest().toString());
//
//        ServicioCaminos cam = new ServicioCaminos(g, 3, 2, 7);
//        List<List<Integer>> caminos = cam.caminos();
//        System.out.println("\nCaminos: ");
//        for (List<Integer> list : caminos) {
//            System.out.println(list.toString());
//        }

        String path = "src/TPE/dataset2.txt";
        CSVReader reader = new CSVReader(path);
        reader.read();
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
}