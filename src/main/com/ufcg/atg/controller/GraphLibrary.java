package main.com.ufcg.atg.controller;

import main.com.ufcg.atg.grafo.Vertice;
import main.com.ufcg.atg.grafo.Grafo;

public interface GraphLibrary {

    Grafo readGraph(String path);

    Grafo readWeightedGraph(String path);

    /**
     * Calcula o número de vértices do Grafo.
     *
     * @param grafo Grafo a ter o número de Vértices calculado.
     *
     * @return número de vértices.
     */
    int getVertexNumber(Grafo grafo);

    int getEdgeNumber(Grafo grafo);

    /**
     * Calcula o grau médio de um Grafo, sendo o número 2E/V,
     * onde E é número de arestas e V o número de vértices.
     *
     * @param grafo Grafo a ter grau médio calculado.
     *
     * @return Grau médio do Grafo.
     */
    float getMeanEdge(Grafo grafo);

    String graphRepresentation(Grafo grafo, String type);

    String BFS(Grafo grafo, Vertice vertice);

    String DFS(Grafo grafo, int vertice);

    boolean connected(Grafo grafo);

    String shortestPath(int vertice1, int vertice2);

    String mst(Grafo grafo);

}
