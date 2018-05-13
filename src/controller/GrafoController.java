package controller;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GrafoController implements GraphLibrary {

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

    @Override
    public Grafo readGraph(String path) {
        Scanner leitor = getScanner(path);
        int quantidadeDeVertices = Integer.valueOf(leitor.nextLine());
        Grafo grafo = new Grafo();
        while (leitor.hasNext()) {
            String arestaAtual = leitor.nextLine();
            String[] argumentos = arestaAtual.split(" ");
            Vertice inicio = new Vertice(Integer.parseInt(argumentos[0]));
            Vertice fim = new Vertice(Integer.parseInt(argumentos[1]));
            Aresta aresta = new Aresta(inicio, fim, 0); //peso default por enquanto
            grafo.getArestas().add(aresta);
        }

        return grafo;
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
    public Grafo readWeightedGraph(String path) {
        return null;
    }

    @Override
    public String BFS(Grafo grafo, int vertice) {
        return null;
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
    public String shortestPath(int vertice1, int vertice2) {
        return null;
    }
}
