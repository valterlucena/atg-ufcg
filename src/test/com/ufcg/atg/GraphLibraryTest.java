package test.com.ufcg.atg;

import junit.framework.Assert;
import main.com.ufcg.atg.controller.GrafoController;
import main.com.ufcg.atg.controller.GraphLibrary;
import main.com.ufcg.atg.grafo.Grafo;
import main.com.ufcg.atg.grafo.Vertice;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GraphLibraryTest {

    private GraphLibrary controller = new GrafoController();
    private GrafoController c = new GrafoController();

    @Test
    public void getMeanEdgeTest() {
        Grafo grafo = controller.readGraph("src/test/resources/entrada.txt");

        int numeroDeArestas = grafo.getArestas().size();
        int numeroDeVertices = grafo.getVertices().size();
        float expectedMeanEdge = (2*numeroDeArestas)/(float)numeroDeVertices;
        float meanEdge = controller.getMeanEdge(grafo);

        Assert.assertEquals(expectedMeanEdge, meanEdge);
    }

    @Test
    public void getEdgeNumberTest() {
        Grafo grafo = controller.readGraph("src/test/resources/entrada.txt");

        int realEdgeNumber = 6;
        int edgeNumber = controller.getEdgeNumber(grafo);

        Assert.assertEquals(realEdgeNumber, edgeNumber);
    }

    @Test
    public void DFSTest() {
        Grafo grafo = controller.readGraph("src/test/resources/entrada.txt");

        String realDFS = "1: 0 -" + System.lineSeparator() +
                "2: 1 1" + System.lineSeparator()+
                "5: 2 2" + System.lineSeparator()+
                "3: 3 5" + System.lineSeparator()+
                "4: 4 3" + System.lineSeparator();
        Assert.assertEquals(realDFS, controller.DFS(grafo, grafo.getVertices().get(0)));
    }

    @Test
    public void getVertexNumberTest() {
        Grafo grafo = controller.readGraph("src/test/resources/entrada.txt");

        int numeroDeVertices = 5;
        int vertexNumber = controller.getVertexNumber(grafo);

        Assert.assertEquals(numeroDeVertices, vertexNumber);
    }

    @Test
    public void mstTest() {
        Grafo grafo = controller.readWeightedGraph("src/test/resources/entradaComPeso.txt");

        String esperado = "VI: 3 VF: 4 Peso: -9.5" + "\n" +
                            "VI: 1 VF: 2 Peso: 0.1" + "\n" +
                            "VI: 2 VF: 5 Peso: 0.2" + "\n" +
                            "VI: 4 VF: 5 Peso: 2.3" + "\n";

        Assert.assertEquals(esperado, controller.mst(grafo));

    }


    @Test
    public void representacionGraphTest() {
        Grafo grafo = controller.readGraph("entrada/entrada.txt");

        String esperada =   "  1 2 3 4 5 " + "\n" +
                            "1 0 1 0 0 1 " + "\n" +
                            "2 1 0 0 0 1 " + "\n" +
                            "3 0 0 0 1 1 " + "\n" +
                            "4 0 0 1 0 1 " + "\n" +
                            "5 1 1 1 1 0 " + "\n";

        String list = "{1=[2, 5], 2=[1, 5], 3=[4, 5], 4=[3, 5], 5=[1, 2, 3, 4]}";
        Assert.assertEquals(list, controller.graphRepresentation(grafo, "AL"));
        Assert.assertEquals(esperada, controller.graphRepresentation(grafo, "AM"));
    }

    @Test
    public void repreGraphPesoTest() {
        Grafo grafo = controller.readWeightedGraph("entrada/entrada.txt");
        GrafoController c = new GrafoController();

        String esperada =  "   1   2   3   4   5" + "\n"+
                            "1 0.0 0.2 0.0 0.0 0.2 "+ "\n"+
                            "2 5.0 0.0 0.0 0.0 5.0 "+ "\n"+
                            "3 0.0 0.0 0.0 -9.5 -9.5 "+ "\n"+
                            "4 0.0 0.0 2.3 0.0 2.3 "+ "\n"+
                            "5 1.0 1.0 1.0 1.0 0.0 "+ "\n";

        String saida = controller.graphRepresentation(grafo, "AM");
        Assert.assertEquals(esperada, saida);

        String lista = controller.graphRepresentation(grafo, "AL");
        String saida2 = "{1=[2(0.1), 5(1.0)], 2=[1(0.1), 5(0.2)], 3=[4(-9.5), 5(5.0)], 4=[3(-9.5), 5(2.3)], 5=[1(1.0), 2(0.2), 3(5.0), 4(2.3)]}";
        Assert.assertEquals(lista, saida2);

    }

    @Test
    public void shortestPath() {
        Grafo grafo = controller.readWeightedGraph("src/test/resources/entradaComPeso.txt");
        Vertice verticeInicial = grafo.getVertices().get(0);

        // TESTE 1
        Vertice verticeFinal = grafo.getVertices().get(4);
        String shortestPathResult = controller.shortestPath(grafo, verticeInicial, verticeFinal);

        Assert.assertEquals("1 2 5", shortestPathResult);

        // TESTE 2
        verticeFinal = grafo.getVertices().get(3);
        shortestPathResult = controller.shortestPath(grafo, verticeInicial, verticeFinal);

        Assert.assertEquals("1 2 5 3 4", shortestPathResult);

        // TESTE 3
        verticeFinal = grafo.getVertices().get(1);
        shortestPathResult = controller.shortestPath(grafo, verticeInicial, verticeFinal);

        Assert.assertEquals("1 2", shortestPathResult);

        // TESTE 4
        verticeInicial = grafo.getVertices().get(1);
        verticeFinal = grafo.getVertices().get(3);
        shortestPathResult = controller.shortestPath(grafo, verticeInicial, verticeFinal);

        Assert.assertEquals("2 5 3 4", shortestPathResult);

        // TESTE 5
        verticeInicial = grafo.getVertices().get(3);
        verticeFinal = grafo.getVertices().get(2);
        shortestPathResult = controller.shortestPath(grafo, verticeInicial, verticeFinal);

        Assert.assertEquals("4 3", shortestPathResult);
    }

    @Test
    public void BFS() {
        Grafo grafo = controller.readGraph("src/test/resources/entrada.txt");

        Vertice verticeInicial = grafo.getVertices().get(0);

        // TESTE 1
        String BFSResult = controller.BFS(grafo, verticeInicial);

        String realBFS = "1 - 0 -" + System.lineSeparator() +
                "2 - 1 1" + System.lineSeparator()+
                "3 - 2 5" + System.lineSeparator()+
                "4 - 2 5" + System.lineSeparator()+
                "5 - 1 1" + System.lineSeparator();

        Assert.assertEquals(realBFS, BFSResult);

        // TESTE 2
        verticeInicial = grafo.getVertices().get(2);
        BFSResult = controller.BFS(grafo, verticeInicial);

        realBFS = "1 - 2 5" + System.lineSeparator() +
                "2 - 2 5" + System.lineSeparator()+
                "3 - 0 -" + System.lineSeparator()+
                "4 - 1 3" + System.lineSeparator()+
                "5 - 1 3" + System.lineSeparator();

        Assert.assertEquals(realBFS, BFSResult);
    }

    @Test
    public void connectedTest() {
        Grafo conectado = controller.readGraph("src/test/resources/entrada.txt");
        Grafo desconectado = controller.readGraph("src/test/resources/entradaDesconectada.txt");
        Assert.assertTrue(controller.connected(conectado));
        Assert.assertFalse(controller.connected(desconectado));
    }
}
