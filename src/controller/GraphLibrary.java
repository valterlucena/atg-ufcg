package controller;

import grafo.Grafo;
import grafo.Vertice;

public interface GraphLibrary {

    Grafo readGraph(String path);

    Grafo readWeightedGraph(String path);

    int getVertexNumber(Grafo grafo);

    int getEdgeNumber(Grafo grafo);

    float getMeanEdge(Grafo grafo);

    String graphRepresentation(Grafo grafo, String type);

    String BFS(Grafo grafo, Vertice vertice);

    String DFS(Grafo grafo, int vertice);

    boolean connected(Grafo grafo);

    String shortestPath(Grafo grafo, Vertice vertice1, Vertice vertice2);

    String mst(Grafo grafo);

}
