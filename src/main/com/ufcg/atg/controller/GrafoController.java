
package main.com.ufcg.atg.controller;

import main.com.ufcg.atg.grafo.Vertice;
import main.com.ufcg.atg.grafo.Aresta;
import main.com.ufcg.atg.grafo.Grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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
        int sum = ZERO;
        for (Vertice vertice: grafo.getVertices()) {
            sum += this.getVertexDegree(grafo, vertice);
        }
        return sum / this.getVertexNumber(grafo);
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
        for (int i = 0; i < pai.length; i++) {
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
            result = listAdj(grafo).toString();

        } return result;
    }

    public String graphRepresentacion(Grafo grafo, String type, boolean peso) {
        String result = "";

        if (peso) {

            if (type.equalsIgnoreCase(MATRIZADJACENCIA)) {
                double[][] pesos = this.matrizAdjPeso(grafo);
                result = this.formatMatrizPeso(pesos, grafo);

            } else if (type.equalsIgnoreCase(LISTAADJACENCIA)) {
                Map mapa = this.listAdjPeso(grafo);
                result = mapa.toString();
            }
        } else {
            result = this.graphRepresentation(grafo, type);
        }
        return result;
    }

    /***
     * Método responsável pela formatação da saída da matriz de adjacência.
     * @param matriz
     * @return
     */
    private String formatMatrixAdj(int[][] matriz) {
        String result = "  ";

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else {
                    result += matriz[i][j] + " ";
                }
            } result += "\n";
        } return result;

    }

    /***
     * Gera a matriz de adjacência do grafo.
     * @param grafo
     * @return
     */
    private int[][] matrixAdj(Grafo grafo) {
        int size = this.getVertexNumber(grafo) + 1;
        int[][] matriz = new int[size][size];
        this.iniciaMatriz(grafo, matriz);
        List<Aresta> arestas = grafo.getArestas();

        for (int i = 1; i <= size; i++) {
            int inicio = arestas.get(i-1).getInicio().getId();
            int fim = arestas.get(i-1).getFim().getId();
            for (int j = 1; j < size; j++) {
                matriz[inicio][fim] = 1;
                matriz[fim][inicio] = 1;
            }
        }

        return matriz;
    }

    private double[][] matrizAdjPeso(Grafo grafo) {

        List<Aresta> arestas = grafo.getArestas();
        int size = this.getVertexNumber(grafo);
        int[][] m = this.matrixAdj(grafo);
        double[][] pesos = new double[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (m[i][j] == 1) {
                    pesos[i-1][j-1] = arestas.get(i).getPeso();
                }
            }
        } return pesos;


    }

    private String formatMatrizPeso(double[][] pesos, Grafo grafo) {
        String result = "";
        result += "   1   2   3   4   5" + "\n";
        for (int i = 0; i < grafo.getVertices().size(); i++) {
            result += (i+1) + " ";
            for (int j = 0; j < pesos.length; j++) {
                result += pesos[i][j] + " ";
            } result += "\n";
        } return result;
    }

    /**
     * Inicia a matriz com os vértices na primeira linha e coluna e com zeros nas outras posições.
     * @param grafo
     * @param matriz
     */
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

    /**
     * Cria um array de vértices para o grafo.
     * @param grafo
     * @return
     */
    private Vertice[] criaArrayVertices(Grafo grafo) {
        Set<Vertice> setVertices = new HashSet<Vertice>();
        List<Vertice> vertices = grafo.getVertices();
        for (Vertice vertice: vertices) {
            setVertices.add(vertice);
        }
        Vertice[] arrayVertices = setVertices.toArray(new Vertice[setVertices.size()]);
        return arrayVertices;
    }

    /**
     * Gera a lista de adjacência do grafo.
     * @param grafo
     * @return
     */
    private Map listAdj(Grafo grafo) {
        List<Aresta> arestas = grafo.getArestas();
        int numeroVertices = this.getVertexNumber(grafo);

        Map map = new HashMap();
        List listaAdj = new ArrayList<>();
        Vertice[] arrayVertices = this.criaArrayVertices(grafo);

        for (int i = 0; i < numeroVertices; i++) {

            List aux = new ArrayList();

            for (int j = 0; j < arestas.size(); j++) {

                int inicio = arestas.get(j).getInicio().getId();
                int fim = arestas.get(j).getFim().getId();

                if (arrayVertices[i].getId() == inicio) {
                    aux.add(fim);
                } else if (arrayVertices[i].getId() == fim) {
                    aux.add(inicio);
                }
            }

            Collections.sort(aux);
            map.put(arrayVertices[i], aux);

        }
        return map;
    }

    private Map listAdjPeso(Grafo grafo) {
        List<Aresta> arestas = grafo.getArestas();
        int numeroVertices = this.getVertexNumber(grafo);

        Map map = new HashMap();
        List listaAdj = new ArrayList<>();
        Vertice[] arrayVertices = this.criaArrayVertices(grafo);

        for (int i = 0; i < numeroVertices; i++) {

            List aux = new ArrayList();
            for (int j = 0; j < arestas.size(); j++) {

                int inicio = arestas.get(j).getInicio().getId();
                int fim = arestas.get(j).getFim().getId();

                if (arrayVertices[i].getId() == inicio) {
                    aux.add(fim + "(" + arestas.get(j).getPeso() + ")");
                } else if (arrayVertices[i].getId() == fim) {
                    aux.add(inicio + "(" + arestas.get(j).getPeso() + ")");
                }
            }

            Collections.sort(aux);
            map.put(arrayVertices[i], aux);
        }
        return map;
    }



    /**
     * Gera o menor caminho.
     * @param grafo
     * @return
     */
    @Override
    public String mst(Grafo grafo) {
        String result = "";
        double pesoMst = 0;

        int size = grafo.getVertices().size();
        int[] pai = new int[size + 1];

        for (int i = 0; i < pai.length; i++) {
            pai[i] = i;
        }

        List<Aresta> arestas = grafo.getArestas();
        this.ordenaList(arestas);

        for (int i = 0; i < arestas.size(); i++) {
            int inicio = arestas.get(i).getInicio().getId();
            int fim = arestas.get(i).getFim().getId();
            double peso = arestas.get(i).getPeso();

            if (this.find(pai, inicio) != this.find(pai, fim)) {
                this.uniao(pai, inicio, fim);
                pesoMst += peso;
                result += "VI: " + inicio + " VF: " + fim + " Peso: " + peso + "\n";
            }
        }

         return result;

    }

    /**
     * Ordena uma lista de arestas pelo peso.
     * @param arestas
     */
    private void ordenaList(List<Aresta> arestas) {
        Collections.sort(arestas, new Comparator<Aresta>() {
            @Override
            public int compare(Aresta aresta, Aresta aresta2) {
                int result = 0;
                if (aresta.getPeso() > aresta2.getPeso()) {
                    result = 1;
                } else if (aresta.getPeso() < aresta2.getPeso()) {
                    result = -1;
                } return result;
            }
        });
    }

    /**
     * Conecta dois vértices.
     * @param pai
     * @param inicio
     * @param fim
     */
    private void uniao(int[] pai, int inicio, int fim) {
        int a = find(pai, inicio);
        int b = find(pai, fim);
        pai[a] = b;

    }

    /**
     * Encontrar o pai do vértice.
     * @param pai
     * @param verticeId
     * @return
     */
    private int find(int[] pai, int verticeId) {
        if (pai[verticeId] == verticeId) {
            return verticeId;
        } return find(pai, pai[verticeId]);
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
        for (int i = 0; i < distancias.length; i++) {
            distancias[i] = new Vertice(i);
        }
    }
}
