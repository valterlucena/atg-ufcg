package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grafo {

    public List<Aresta> arestas;

    public Grafo() {
        this.arestas = new ArrayList<>();
    }

    public List<Aresta> getArestas() {
        return this.arestas;
    }

    public String toString() {
        String saida = "";
        for (Aresta aresta: arestas) {
            saida = saida + aresta.toString() + "\n";
        }
        return saida.trim();
    }

    public Set<Vertice> getVertices() {
        Set<Vertice> vertices = new HashSet<>();
        for (Aresta aresta: this.getArestas()) {
            vertices.add(aresta.inicio);
            vertices.add(aresta.fim);
        }
        return vertices;
    }

    public int getVertexNumber() {
        return this.getVertices().size();
    }

    public int getEdgeNumber() {
        return this.getArestas().size();
    }

    public float getMeanEdge() {
        int sum = 0;
        for (Vertice vertice: this.getVertices()) {
            sum += this.getVertexDegree(vertice);
        }
        return sum / this.getVertexNumber();
    }

    public int getVertexDegree(Vertice vertice) {
        int grau = 0;
        for (Aresta aresta: this.getArestas()) {
            if (aresta.getInicio().equals(vertice)
                    || aresta.getFim().equals(vertice)) {
                grau++;
            }
        }
        return grau;
    }
}
