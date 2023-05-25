package TPE;

import TPE.servicios.*;
import TPE.collections.*;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Grafo<String> g = new GrafoDirigido<String>();
        //agregado de vertices
        g.agregarVertice(1); //a
        g.agregarVertice(2); //b
        g.agregarVertice(3); //c
        g.agregarVertice(4); //d
        g.agregarVertice(5); //e
        g.agregarVertice(6); //f
        g.agregarVertice(7); //g
        g.agregarVertice(8); //h
        g.agregarVertice(9); //i
        g.agregarVertice(10); //j

//      agregado de arcos
        g.agregarArco(1,4,"");
        g.agregarArco(1,7,"");
        g.agregarArco(1,2,"");
        //g.agregarArco(2,4,null);
        g.agregarArco(2,5,"");
        g.agregarArco(2,8,"");
        g.agregarArco(2,3,"");
        g.agregarArco(3,9,"");
        g.agregarArco(3,2,"");
        g.agregarArco(3,6,"");
        g.agregarArco(3,7,"");
        //g.agregarArco(4,2,null);
        g.agregarArco(4,8,"");
        g.agregarArco(5,1,"");
        g.agregarArco(6,1,"");
        g.agregarArco(7,10,"");
        g.agregarArco(8,3,"");
        g.agregarArco(8,5,"");
        g.agregarArco(9,4,"");
        g.agregarArco(10,2,"");

        System.out.println("Grafo:");
        g.imprimir();

        System.out.println("\nRecorrido DFS:");
        ServicioDFS dfs = new ServicioDFS(g);
        System.out.println(dfs.dfsForest().toString());

        System.out.println("\nRecorrido BFS:");
        ServicioBFS bfs = new ServicioBFS(g);
        System.out.println(bfs.bfsForest().toString());

        ServicioCaminos cam = new ServicioCaminos(g, 3, 2, 10);
        List<List<Integer>> caminos = cam.caminos();
        System.out.println("\nCaminos: ");
        for (List<Integer> list : caminos) {
            System.out.println(list.toString());
        }
    }
}