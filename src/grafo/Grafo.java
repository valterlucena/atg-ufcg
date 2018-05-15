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
}
