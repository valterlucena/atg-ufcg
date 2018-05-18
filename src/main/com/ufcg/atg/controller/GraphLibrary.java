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

    /***
     * Faz a impressão da matriz e lista de adjacência do grafo
     * @param grafo
     * @param type
     * @return matriz ou lista de adjacência
     */
    String graphRepresentation(Grafo grafo, String type);

    /**
     * Percorre o grafo utilizando a busca em largura, dado um vértice inicial
     * fornecido.
     *
     * @param grafo
     * @param vertice
     *
     * @return String representando a árvore de busca gerada, com o nível de cada vértice na árvore.
     * A árvore gerada é descrita informando o pai de cada vértice e seu nível na String de saída.
     */
    String BFS(Grafo grafo, Vertice vertice);

    String DFS(Grafo grafo, int vertice);

    boolean connected(Grafo grafo);

    /**
     * Encontra o caminho mais curto entre dois vértices.
     *
     * @param graph
     * @param v1 Vértice Origem
     * @param v2 Vértice Destino
     *           
     * @return String composta pelos vértices que fazem parte do menor
     *          caminho, incluindo os vértices de origem e destino.
     */
    String shortestPath(Grafo graph, Vertice v1, Vertice v2);

    /**
     * Gera o menor caminho.
     * @param grafo
     * @return String composta do vértice pai, vértice filho e o entre eles.
     */
    String mst(Grafo grafo);

}
