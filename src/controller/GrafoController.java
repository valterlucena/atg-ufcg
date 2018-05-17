
package controller;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

            for (Vertice verticeFinal : v.getAdj()) {
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

        for (int i = 0; i < numeroVertices; i++) {
            List aux = new ArrayList();
            aux.add(arrayVertices[i].getId());
            for (int j = 0; j < arestas.size(); j++) {

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

    @Override
    public String shortestPath(int vertice1, int vertice2) {
        return null;
    }
}