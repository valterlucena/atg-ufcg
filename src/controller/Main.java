package controller;

import grafo.Grafo;
import grafo.Vertice;

public class Main {

    public static void main(String[] args) {
        GrafoController controller = new GrafoController();

        Grafo grafo = controller.readGraph("./entrada/entrada.txt");
        Vertice vInicial = new Vertice(1);

        System.out.println(grafo.getArestas());

        System.out.println(controller.BFS(grafo, grafo.getVertices().get(0)));
    }
}
