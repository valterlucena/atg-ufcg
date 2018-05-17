package main.com.ufcg.atg.controller;

import main.com.ufcg.atg.grafo.Vertice;
import main.com.ufcg.atg.grafo.Grafo;

public class Main {

    public static void main(String[] args) {
        GrafoController controller = new GrafoController();

        Grafo grafo = controller.readGraph("./entrada/entrada.txt");
        Vertice vInicial = new Vertice(1);

        System.out.println(controller.BFS(grafo, grafo.getVertices().get(0)));
    }
}
