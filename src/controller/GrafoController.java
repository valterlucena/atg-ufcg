package controller;

import com.sun.xml.internal.ws.api.pipe.ServerTubeAssemblerContext;
import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GrafoController implements GraphLibrary {


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

        String result = "";
        if (type.equalsIgnoreCase(MATRIZADJACENCIA)) {
            int[][] matrizAdj = this.matrixAdj(grafo);
            result = this.formatMatrixAdj(matrizAdj);
        } else if (type.equalsIgnoreCase(LISTAADJACENCIA)) {
            result = this.listAdj(grafo);
        } return result;
    }

    private String formatMatrixAdj(int[][] matriz) {
        return null;
    }

    private String formatListAdj(int[][] matriz) {
        return null;
    }

    private int[][] matrixAdj(Grafo grafo) {
        int size = this.getVertexNumber(grafo) + 1;
        int[][] matriz = new int[size][size];
        this.iniciaMatriz(grafo, matriz);
        List<Aresta> arestas = grafo.getArestas();

        for (int i = 1; i < size; i++) {
            int inicio = arestas.get(i-1).getInicio().getId();
            int fim = arestas.get(i-1).getFim().getId();
            for (int j = 1; j < size; j++) {
                matriz[inicio][fim] = 1;
            }
        }

        return matriz;
    }

    private void iniciaMatriz(Grafo grafo, int[][] matriz) {
        int i = 1;
        Set<Vertice> vertices = grafo.getVertices();
        Vertice[] arrayVertices = vertices.toArray(new Vertice[vertices.size()]);
        for (Vertice vertice: vertices) {
            matriz[i][0] = vertice.getId();
            matriz[0][i] = vertice.getId();
            i++;
        }

    }

    private String listAdj(Grafo grafo) {
        List<Aresta> arestas = grafo.getArestas();
        int numeroVertices = this.getVertexNumber(grafo);

        List<Vertice> listaAdjCompleta = new ArrayList<>();

        Set<Vertice> vertices = grafo.getVertices();
        Vertice[] arrayVertices = vertices.toArray(new Vertice[vertices.size()]);

        for (int i = 0; i < arestas.size(); i++) {
            List<Vertice> listaAdjAux = new ArrayList<>();
            for (int j = 0; j < numeroVertices; j++) {
                List<Vertice> extremidades = arestas.get(i).getExtremidades();
                //if (extremidades.contains(grafo.getVertices()))
            }
        }

        return null; //to do

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
