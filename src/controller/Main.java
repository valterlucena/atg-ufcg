package controller;

import grafo.Grafo;

public class Main {

    public static void main(String[] args) {
        GrafoController controller = new GrafoController();

        Grafo grafo = controller.readWeightedGraph("./entrada/entrada.txt");

        System.out.println(controller.BFS(grafo, grafo.getVertices().get(0)));

        System.out.println(controller.shortestPath(grafo, grafo.getVertices().get(0), grafo.getVertices().get(2)));
    }
}
