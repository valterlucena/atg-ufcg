
package controller;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;
import util.No;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GrafoController implements GraphLibrary {

    private static final String NOVA_LINHA = System.lineSeparator();
    private static final int INFINITO = 100000000;
    private static final int ZERO = 0;

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
        grafo.setSize(quantidadeDeVertices);

        while (leitor.hasNext()) {
            String arestaAtual = leitor.nextLine();
            String[] argumentos = arestaAtual.split(" ");

            int inicioId = Integer.parseInt(argumentos[0]);
            int fimId = Integer.parseInt(argumentos[1]);
            double peso = isWeighted ? Double.parseDouble(argumentos[2]) : ZERO;

            grafo.addAresta(grafo.addVertice(inicioId), grafo.addVertice(fimId), peso);
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
        int sum = 0;
        for (Vertice vertice: grafo.getVertices()) {
            sum += this.getVertexDegree(grafo, vertice);
        }
        return sum / this.getVertexNumber(grafo);
    }

    private int getVertexDegree(Grafo grafo, Vertice vertice) {
        int grau = 0;
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
        return false;
    }

    @Override
    public String BFS(Grafo grafo, Vertice verticeInicial) {
        StringBuilder saida = new StringBuilder();
        Queue<Vertice> fila = new LinkedList<>();

        int[] nivel = new int[grafo.getSize()];
        Vertice[] pai = new Vertice[grafo.getSize()];

        setNivel(nivel);
        setPai(pai);

        nivel[verticeInicial.getId()] = 0;
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
                saida.append(0 + " ");
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

    private void setPai(Vertice[] pai) {
        for (int i = 0; i < pai.length; i++) {
            pai[i] = null;
        }
    }

    private void setNivel(int[] nivel) {
        for (int i = 0; i < nivel.length; i++) {
            nivel[i] = INFINITO;
        }
    }

    @Override
    public String DFS(Grafo grafo, int vertice) {
        return null;
    }

    @Override
    public String graphRepresentation(Grafo grafo, String type) {
        return null;
    }

    @Override
    public String mst(Grafo grafo) {
        return null;
    }

    @Override
    public String shortestPath(Grafo graph, Vertice v1, Vertice v2) {
        StringBuilder saida = new StringBuilder();
        PriorityQueue<No> fila = new PriorityQueue<>();
        No[] distancias = new No[graph.getSize()];

        saida.append(v1.getId());

        // padroniza distancia minimas para cada nó com infinito
        padronizaDistancias(distancias);

        // padroniza distancia minima do nó origem com 0
        No noInicial = new No();
        noInicial.vertice = v1;
        noInicial.distancia = 0;

        distancias[v1.getId()] = noInicial;
        fila.add(noInicial);

        while (!fila.isEmpty()) {
            No u = fila.remove();

            for (Aresta aresta : graph.getArestasDosVertices(u.vertice.getVerticesAdjacentes())) {
                Vertice destino = aresta.getFim();

                int origemId = u.vertice.getId();
                int destinoId = destino.getId();

                double distanciaOrigemU = distancias[origemId].distancia;
                double distanciaOrigemV = distancias[destinoId].distancia;

                if (distanciaOrigemU + aresta.getPeso() < distanciaOrigemV) {
                    distancias[destinoId].vertice = destino;
                    distancias[destinoId].distancia = distanciaOrigemU + aresta.getPeso();

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

    private void padronizaDistancias(No[] distancias) {
        for (int i = 0; i < distancias.length; i++) {
            distancias[i] = new No();
            distancias[i].distancia = INFINITO;
        }
    }
}