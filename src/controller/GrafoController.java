package controller;

import com.sun.xml.internal.ws.api.pipe.ServerTubeAssemblerContext;
import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;

import java.io.File;
import java.io.FileNotFoundException;
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
            result = this.matrixAdj(grafo);
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

    private String matrixAdj(Grafo grafo) {
        int size = this.getVertexNumber(grafo) + 1;
        int[][] matriz = new int[size][size];
        this.initMatrix(grafo, matriz);

        for (Vertice vertice: grafo.getVertices()) {
            for (Aresta aresta: grafo.getArestas()) {

            }
        }

        return null;
    }

    
    private void preencheVertMatriz(Grafo grafo, int[][] matriz) {
        int i = 1;
        for (Vertice vertice: grafo.getVertices()) {
            matriz[i][0] = vertice.getId();
            matriz[0][i] = vertice.getId();
            i++;
        }
    }

    private void initMatrix(Grafo grafo, int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 0;
            }
        }

        this.preencheVertMatriz(grafo, matrix);
    }


    private String listAdj(Grafo grafo) {
        int size = this.getVertexNumber(grafo);
        int[][] list = new int[size][size];

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
