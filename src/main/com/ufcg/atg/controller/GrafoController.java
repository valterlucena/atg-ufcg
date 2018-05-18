package main.com.ufcg.atg.controller;

import main.com.ufcg.atg.grafo.Vertice;
import main.com.ufcg.atg.grafo.Aresta;
import main.com.ufcg.atg.grafo.Grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Queue;
import java.util.PriorityQueue;

public class GrafoController implements GraphLibrary {

    private static final String NOVA_LINHA = System.lineSeparator();
    private static final int INFINITO = 100000000;
    private static final int ZERO = 0;

    private static final String MATRIZADJACENCIA = "AM";
    private static final String LISTAADJACENCIA = "AL";

    private Scanner getScanner(String path) {
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scanner;
    }

    private Grafo readGraph(String path, boolean isWeighted) {
        Scanner leitor = getScanner(path);
        int quantidadeDeVertices = Integer.valueOf(leitor.nextLine());
        Grafo grafo = new Grafo();
        grafo.criaVertices(quantidadeDeVertices);

        while (leitor.hasNext()) {
            String arestaAtual = leitor.nextLine();
            String[] argumentos = arestaAtual.split(" ");

            int inicioId = Integer.parseInt(argumentos[0]);
            int fimId = Integer.parseInt(argumentos[1]);
            double peso = isWeighted ? Double.parseDouble(argumentos[2]) : ZERO;

            grafo.addAresta(grafo.criaVertice(inicioId), grafo.criaVertice(fimId), peso);
        }

        return grafo;
    }

    @Override
    public Grafo readGraph(String path) {
        return this.readGraph(path, false);
    }

    @Override
    public Grafo readWeightedGraph(String path) {
        return this.readGraph(path, true);
    }

    @Override
    public float getMeanEdge(Grafo grafo) {
        int sum = ZERO;
        for (Vertice vertice: grafo.getVertices()) {
            sum += this.getVertexDegree(grafo, vertice);
        }
        return sum / (float) this.getVertexNumber(grafo);
    }

    private int getVertexDegree(Grafo grafo, Vertice vertice) {
        int grau = ZERO;
        for (Aresta aresta: grafo.getArestas()) {
            if (aresta.getInicio().equals(vertice)
                    || aresta.getFim().equals(vertice)) {
                grau++;
            }
        }
        return grau;
    }

    @Override
    public int getVertexNumber(Grafo grafo) {
        return grafo.getVertices().size();
    }

    @Override
    public int getEdgeNumber(Grafo grafo) {
        return grafo.getArestas().size();
    }

    @Override
    public boolean connected(Grafo grafo) {
        boolean[] visitados = new boolean[grafo.getSize()+1];
        List<Vertice> vertices = grafo.getVertices();
        if (vertices.size() > 1) { // trivialmente, um grafo nulo ou com apenas um vertice é conectado
            Vertice primeiro = vertices.get(1);
            this.visita(primeiro, visitados);
            for (int i = 1; i < visitados.length; i++) {
                if (!visitados[i]) return false;
            }
        }
        return true;
    }

    /**
     * Marca como visitado um vertice e seus vertices adjacentes.
     * @param vertice O vértice que está sendo visitado
     * @param visitados Guarda informações sobre os vértices que foram visitados
     */
    private void visita(Vertice vertice, boolean[] visitados) {
        visitados[vertice.getId()] = true;
        List<Vertice> adjacentes = vertice.getVerticesAdjacentes();
        for (Vertice atual: adjacentes) {
            int idAtual = atual.getId();
            if (!visitados[idAtual]) visita(atual, visitados);
        }
    }

    @Override
    public String BFS(Grafo grafo, Vertice verticeInicial) {
        StringBuilder saida = new StringBuilder();
        Queue<Vertice> fila = new LinkedList<>();

        int[] nivel = new int[grafo.getSize()];
        Vertice[] pai = new Vertice[grafo.getSize()];

        setNivel(nivel);
        setPai(pai);

        nivel[verticeInicial.getId()] = ZERO;
        fila.add(verticeInicial);

        while (!fila.isEmpty()) {
            Vertice v = fila.remove();

            for (Vertice verticeFinal : v.getVerticesAdjacentes()) {
                int idVerticeInicio = v.getId();
                int idVerticeFim = verticeFinal.getId();

                if ((nivel[idVerticeInicio] + 1) < nivel[idVerticeFim]) {
                    nivel[idVerticeFim] = nivel[idVerticeInicio] + 1;
                    pai[idVerticeFim] = v;
                    fila.add(verticeFinal);
                }
            }
        }

        // Monta saída
        for (int i = 1; i < grafo.getSize(); i++) {
            saida.append(i + " - ");

            if (nivel[i] == INFINITO) {
                saida.append(ZERO + " ");
            } else {
                saida.append(nivel[i] + " ");
            }

            if (pai[i] == null) {
                saida.append("-");
            } else {
                saida.append(pai[i].getId());
            }

            saida.append(NOVA_LINHA);
        }
        return saida.toString();
    }

    /**
     * Popula o Array de pais com posições vazias.
     *
     * @param pai
     */
    private void setPai(Vertice[] pai) {
        for (int i = ZERO; i < pai.length; i++) {
            pai[i] = null;
        }
    }

    /**
     * Popula o Array de niveis com infinito. Que representa o nível máximo
     * teórico que um gráfo pode chegar.
     *
     * @param nivel
     */
    private void setNivel(int[] nivel) {
        for (int i = ZERO; i < nivel.length; i++) {
            nivel[i] = INFINITO;
        }
    }

    @Override
    public String DFS(Grafo graph, Vertice vertex) {
        return DFS(graph, vertex, null, ZERO, new HashSet<>());
    }

    /**
     * Método auxiliar que implementa a lógica da busca em profundidade.
     * @param graph
     *      Grafo a ser percorrido
     * @param vertex
     *      Vértice inicial
     * @param parent
     *      Vértice pai
     * @param depth
     *      Profundidade do vértice
     * @param visited
     *      Conjunto de todos os vértices que já foram visitados
     * @return
     */
    private String DFS(Grafo graph, Vertice vertex, Vertice parent, int depth, Set<Vertice> visited) {
        StringBuilder output = new StringBuilder();
        output.append(vertex.getId());
        output.append(": ");
        output.append(depth);
        output.append(parent == null ? " -" : " " + parent.getId());
        output.append(NOVA_LINHA);
        visited.add(vertex);
        for (Vertice current: vertex.getVerticesAdjacentes()) {
            if (!visited.contains(current)) {
                output.append(DFS(graph, current, vertex, depth + 1, visited));
            }
        }
        return output.toString();
    }

    @Override
    public String graphRepresentation(Grafo grafo, String type) {

        String result = "";

        if (type.equalsIgnoreCase(MATRIZADJACENCIA)) {
            int[][] matrizAdj = this.matrixAdj(grafo);
            result = this.formatMatrixAdj(matrizAdj);
        } else if (type.equalsIgnoreCase(LISTAADJACENCIA)) {
            List<List> listaAdj = this.listAdj(grafo);
            result = this.formatListAdj(listaAdj);
        } return result;
    }

    private String formatMatrixAdj(int[][] matriz) {
        return null;
    }

    private String formatListAdj(List<List> matriz) {
        return null;
    }

    public int[][] matrixAdj(Grafo grafo) {
        int size = this.getVertexNumber(grafo) + 1;
        int[][] matriz = new int[size][size];
        this.iniciaMatriz(grafo, matriz);
        List<Aresta> arestas = grafo.getArestas();

        for (int i = 1; i < size; i++) {
            int inicio = arestas.get(i-1).getInicio().getId();
            int fim = arestas.get(i-1).getFim().getId();
            for (int j = 1; j < size; j++) {
                matriz[inicio][fim] = 1;
                matriz[fim][inicio] = 1;
            }
        }

        return matriz;
    }

    private void iniciaMatriz(Grafo grafo, int[][] matriz) {
        int indice = 1;
        Set<Vertice> setVertices = new HashSet<Vertice>();
        List<Vertice> vertices = grafo.getVertices();
        for (Vertice vertice: vertices) {
            setVertices.add(vertice);
        }

        for (Vertice vertice: setVertices) {
            matriz[indice][0] = vertice.getId();
            matriz[0][indice] = vertice.getId();
            indice++;
        }

    }

    private Vertice[] criaArrayVertices(Grafo grafo) {
        Set<Vertice> setVertices = new HashSet<Vertice>();
        List<Vertice> vertices = grafo.getVertices();
        for (Vertice vertice: vertices) {
            setVertices.add(vertice);
        }
        Vertice[] arrayVertices = setVertices.toArray(new Vertice[setVertices.size()]);
        return arrayVertices;
    }

    public List<List> listAdj(Grafo grafo) {
        List<Aresta> arestas = grafo.getArestas();
        int numeroVertices = this.getVertexNumber(grafo);

        List listaAdj = new ArrayList<>();
        Vertice[] arrayVertices = this.criaArrayVertices(grafo);

        for (int i = ZERO; i < numeroVertices; i++) {
            List aux = new ArrayList();
            aux.add(arrayVertices[i].getId());
            for (int j = ZERO; j < arestas.size(); j++) {

                int inicio = arestas.get(j).getInicio().getId();
                int fim = arestas.get(j).getFim().getId();

                if (arrayVertices[i].getId() == inicio) {
                    aux.add(fim);
                } else if (arrayVertices[i].getId() == fim) {
                    aux.add(inicio);
                }
            }

            Collections.sort(aux.subList(1, aux.size()));
            listaAdj.add(aux);
        }

        return listaAdj;
    }

    @Override
    public String mst(Grafo grafo) {
        return null;
    }

    /**
     * Encontra o caminho mais curto entre dois vértices.
     *
     * @param graph
     * @param v1 Vértice Origem
     * @param v2 Vértice Destino
     * @return
     */
    @Override
    public String shortestPath(Grafo graph, Vertice v1, Vertice v2) {
        StringBuilder saida = new StringBuilder();
        PriorityQueue<Vertice> fila = new PriorityQueue<>();
        Vertice[] distancias = new Vertice[graph.getSize()];

        saida.append(v1.getId());

        setDistancias(distancias);

        // padroniza distancia minima do nó origem com 0
        distancias[v1.getId()] = v1;
        distancias[v1.getId()].setDistancia(ZERO);

        fila.add(distancias[v1.getId()]);

        while (!fila.isEmpty()) {
            Vertice u = fila.remove();

            for (Aresta aresta : graph.getArestasDosVertices(u.getVerticesAdjacentes())) {
                Vertice destino = aresta.getFim();

                int origemId = u.getId();
                int destinoId = destino.getId();

                double distanciaOrigemU = distancias[origemId].getDistancia();
                double distanciaOrigemV = distancias[destinoId].getDistancia();

                if (distanciaOrigemU + aresta.getPeso() < distanciaOrigemV) {
                    distancias[destinoId] = destino;
                    distancias[destinoId].setDistancia(distanciaOrigemU + aresta.getPeso());

                    saida.append(" " + destino.getId());

                    if (destino.getId() == v2.getId()) {
                        break;
                    }

                    fila.add(distancias[destinoId]);
                }
            }
        }

        return saida.toString();
    }

    /**
     * Inicia o array de distâncias com vértices.
     *
     * @param distancias
     */
    private void setDistancias(Vertice[] distancias) {
        for (int i = ZERO; i < distancias.length; i++) {
            distancias[i] = new Vertice(i);
        }
    }
}