package controller;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GrafoController implements GraphLibrary {

    private static final int ZERO = 0;
    Set<Integer> verticesVisitados = new HashSet<>();

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
        while (leitor.hasNext()) {
            String arestaAtual = leitor.nextLine();
            String[] argumentos = arestaAtual.split(" ");
            Vertice inicio = new Vertice(Integer.parseInt(argumentos[0]));
            Vertice fim = new Vertice(Integer.parseInt(argumentos[1]));
            double peso = isWeighted ? Double.parseDouble(argumentos[2]) : ZERO;
            Aresta aresta = new Aresta(inicio, fim, peso);
            grafo.getArestas().add(aresta);
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
    public String BFS(Grafo grafo, Vertice vertice) {
        StringBuilder saida = new StringBuilder();
        Queue<Vertice> fila = new LinkedList<>();

        int nivel = 0;

        saida.append(vertice.getId()).append(" - ").append(nivel).append(" -").append("\n");

        fila.add(vertice);
        vertice.setVisitado(true);

        this.verticesVisitados.add(vertice.getId());

        while(!fila.isEmpty()) {
            boolean nivelVisitado = false;

            Vertice pai = fila.poll();
            Vertice filho = null;

            while ((filho = filhoNaoVisitado(grafo, pai)) != null) {
                if(!nivelVisitado) {
                    nivelVisitado = true;
                    nivel++;
                }

                filho.setVisitado(true);
                fila.add(filho);

                saida.append(filho.getId()).append(" - ").append(nivel).append(" ").append(pai.getId()).append("\n");
            }

            pai.setVisitado(true);
        }

        System.out.println(saida.toString());
        return saida.toString();
    }

    private Vertice filhoNaoVisitado(Grafo grafo, Vertice pai) {
        for (Aresta aresta : grafo.getArestas()) {
            Vertice v1 = aresta.getInicio();
            Vertice v2 = aresta.getFim();

            if (v1.equals(pai) && !(v2.isVisitado())) {
                // verifica se já não foi visitado
                int oldLen = verticesVisitados.size();
                this.verticesVisitados.add(v2.getId());
                int newLen = verticesVisitados.size();
                if(oldLen != newLen) {
                    return v2;
                }
            } else if (v2.equals(pai) && !(v1.isVisitado())) {
                // verifica se já não foi visitado
                int oldLen = verticesVisitados.size();
                this.verticesVisitados.add(v1.getId());
                int newLen = verticesVisitados.size();
                if(oldLen != newLen) {
                    return v1;
                }
            }
        }

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
