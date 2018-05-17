package test.com.ufcg.atg;

import main.com.ufcg.atg.grafo.Grafo;
import main.com.ufcg.atg.grafo.Vertice;
import org.junit.Test;

public class GraphLibraryTest {

    @Test
    public void getMeanEdgeTest() {
        Grafo grafo = new Grafo();
        Vertice vertice1 = grafo.addVertice(1);
        Vertice vertice2 = grafo.addVertice(2);
        Vertice vertice3 = grafo.addVertice(3);
        Vertice vertice4 = grafo.addVertice(4);
    }

}
