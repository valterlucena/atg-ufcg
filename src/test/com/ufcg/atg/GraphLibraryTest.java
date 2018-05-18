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


}
