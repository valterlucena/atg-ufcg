package controller;

import grafo.Grafo;

public interface GraphLibrary {

    Grafo readGraph(String path);

    Grafo readWeightedGraph(String path);

    int getVertexNumber(Grafo grafo);

    int getEdgeNumber(Grafo grafo);

    float getMeanEdge(Grafo grafo);

    String graphRepresentation(Grafo grafo, String type);

    String BFS(Grafo grafo, int vertice);

    String DFS(Grafo grafo, int vertice);

    boolean connected(Grafo grafo);

    String shortestPath(int vertice1, int vertice2);

    String mst(Grafo grafo);

}
