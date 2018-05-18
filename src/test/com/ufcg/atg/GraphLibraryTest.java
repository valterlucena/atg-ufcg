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
        Grafo grafo = new Grafo();
        Vertice vertice1 = grafo.addVertice(1);
        Vertice vertice2 = grafo.addVertice(2);
        Vertice vertice3 = grafo.addVertice(3);
        Vertice vertice4 = grafo.addVertice(4);

        grafo.addAresta(vertice1, vertice2, 0);
        grafo.addAresta(vertice2, vertice4, 0);
        grafo.addAresta(vertice1, vertice3, 0);

        int numeroDeArestas = grafo.getArestas().size();
        int numeroDeVertices = grafo.getVertices().size();
        float expectedMeanEdge = (2*numeroDeArestas)/numeroDeVertices;
        float meanEdge = controller.getMeanEdge(grafo);

        Assert.assertEquals(expectedMeanEdge, meanEdge);
    }

    @Test
    public void getVertexNumberTest() {
        Grafo grafo = new Grafo();
        Vertice vertice1 = grafo.addVertice(1);
        Vertice vertice2 = grafo.addVertice(2);
        Vertice vertice3 = grafo.addVertice(3);
        Vertice vertice4 = grafo.addVertice(4);

        grafo.addAresta(vertice1, vertice2, 0);
        grafo.addAresta(vertice2, vertice4, 0);
        grafo.addAresta(vertice1, vertice3, 0);

        int numeroDeVertices = 4;
        int vertexNumber = controller.getVertexNumber(grafo);

        Assert.assertEquals(numeroDeVertices, vertexNumber);
    }

    @Test
    public void mstTest() {
        Grafo grafo = new Grafo();
        Vertice v1 = grafo.addVertice(1);
        Vertice v2 = grafo.addVertice(2);
        Vertice v3 = grafo.addVertice(3);
        Vertice v4 = grafo.addVertice(4);
        Vertice v5 = grafo.addVertice(5);

        grafo.addAresta(v1, v2, 0.1);
        grafo.addAresta(v2, v5, 0.2);
        grafo.addAresta(v5, v3, 5);
        grafo.addAresta(v3, v4, -9.5);
        grafo.addAresta(v4, v5, 2.3);
        grafo.addAresta(v1, v5, 1);

        String esperado = "VI: 3 VF: 4 Peso: -9.5" + "\n" +
                            "VI: 1 VF: 2 Peso: 0.1" + "\n" +
                            "VI: 2 VF: 5 Peso: 0.2" + "\n" +
                            "VI: 4 VF: 5 Peso: 2.3" + "\n";

        Assert.assertEquals(esperado, controller.mst(grafo));

    }


    @Test
    public void representacionGraphTest() {
        Grafo grafo = new Grafo();

        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        Vertice v5 = new Vertice(5);

        grafo.addAresta(v1, v2, 0.1);
        grafo.addAresta(v2, v5, 0.2);
        grafo.addAresta(v3, v5, 5);
        grafo.addAresta(v3, v4, -9.5);
        grafo.addAresta(v1, v5, 1);

        String esperada =   "  1 2 3 4 5 " + "\n" +
                            "1 0 1 0 0 1 " + "\n" +
                            "2 1 0 0 0 1 " + "\n" +
                            "3 0 0 0 1 1 " + "\n" +
                            "4 0 0 1 0 1 " + "\n" +
                            "5 1 1 1 1 0 " + "\n";

        Assert.assertEquals(esperada, controller.graphRepresentation(grafo, "AM"));

    }

}
