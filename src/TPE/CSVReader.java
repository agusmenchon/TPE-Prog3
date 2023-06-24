package TPE;

import TPE.collections.*;
import TPE.servicios.Backtracking;
import TPE.servicios.Greedy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.Timer;
public class CSVReader {

    private String path;

    public CSVReader(String path) {
        this.path = path;
    }

    public void read() {
        // Obtengo una lista con las lineas del archivo

        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent();
        Grafo<Integer> g = new GrafoNoDirigido<Integer>();
        for (String[] line: lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            Integer origen = Integer.parseInt(line[0].trim().substring(1));
            Integer destino = Integer.parseInt(line[1].trim().substring(1));
            Integer etiqueta = Integer.parseInt(line[2].trim());


            // Aca instanciar lo que necesiten en base a los datos leidos
            g.agregarVertice(origen);
            g.agregarVertice(destino);
            g.agregarArco(origen, destino, etiqueta);
        }
        Timer timer = new Timer();
        timer.start();
        Greedy prim = new Greedy(g);
        prim.ejecutarGreedy(1);
        double time = timer.stop();
        System.out.println("el tiempo es: " + time + "ms");
        int minimaDistancia = prim.getSumaEtiqueta();

        Backtracking backtracking = new Backtracking(g);
        System.out.println("------------------------");
        System.out.println("Algoritmo BACKTRACKING");
        List<Arco<?>> recorrido = backtracking.Backtracking(1);
//        int contador = 0;
//        for(Arco<?> arco2 : recorrido){
//            contador += arco2.getEtiqueta();
//        }
//        for(Arco<?> arco : recorrido){
//            System.out.print("E"+arco.getVerticeOrigen().getId()+"-"+"E"+arco.getVerticeDestino().getId()+",");
//        }
//        System.out.println("\nLa suma de los caminos es: " + contador);
    }

    private ArrayList<String[]> readContent() {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(this.path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        return lines;
    }
}