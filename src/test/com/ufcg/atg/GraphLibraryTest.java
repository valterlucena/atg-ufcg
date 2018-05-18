package test.com.ufcg.atg;

import junit.framework.Assert;
import main.com.ufcg.atg.controller.GrafoController;
import main.com.ufcg.atg.controller.GraphLibrary;
import main.com.ufcg.atg.grafo.Grafo;
import main.com.ufcg.atg.grafo.Vertice;
import org.junit.Test;

public class GraphLibraryTest {

    private GraphLibrary controller = new GrafoController();

    @Test
    public void getMeanEdgeTest() {
        Grafo grafo = controller.readGraph("src/test/resources/entrada.txt");

        int numeroDeArestas = grafo.getArestas().size();
        int numeroDeVertices = grafo.getVertices().size();
        float expectedMeanEdge = (2*numeroDeArestas)/numeroDeVertices;
        float meanEdge = controller.getMeanEdge(grafo);

        Assert.assertEquals(expectedMeanEdge, meanEdge);
    }

    @Test
    public void getEdgeNumberTest() {
        Grafo grafo = new Grafo();
        Vertice vertice1 = grafo.addVertice(1);
        Vertice vertice2 = grafo.addVertice(2);
        Vertice vertice3 = grafo.addVertice(3);
        Vertice vertice4 = grafo.addVertice(4);

        grafo.addAresta(vertice1, vertice2, 0);
        grafo.addAresta(vertice2, vertice4, 0);
        grafo.addAresta(vertice1, vertice3, 0);

        int realEdgeNumber = 3;
        int edgeNumber = controller.getEdgeNumber(grafo);

        Assert.assertEquals(realEdgeNumber, edgeNumber);
    }

    @Test
    public void DFSTest() {
        Grafo grafo = new Grafo();
        Vertice vertice1 = grafo.addVertice(1);
        Vertice vertice2 = grafo.addVertice(2);
        Vertice vertice3 = grafo.addVertice(3);
        Vertice vertice4 = grafo.addVertice(4);

        grafo.addAresta(vertice1, vertice2, 0);
        grafo.addAresta(vertice2, vertice4, 0);
        grafo.addAresta(vertice1, vertice3, 0);

        String realDFS = "1: 0 -" + System.lineSeparator() +
                "2: 1 1" + System.lineSeparator()+
                "4: 2 2" + System.lineSeparator()+
                "3: 1 1" + System.lineSeparator();
        Assert.assertEquals(realDFS, controller.DFS(grafo, vertice1));
    }

    @Test
    public void getVertexNumberTest() {
        Grafo grafo = controller.readGraph("src/test/resources/entrada.txt");

        int numeroDeVertices = 5;
        int vertexNumber = controller.getVertexNumber(grafo);

        Assert.assertEquals(numeroDeVertices, vertexNumber);
    }

}
