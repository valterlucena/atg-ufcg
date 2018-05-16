package grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private List<Aresta> arestas;
    private List<Vertice> vertices;
    private int size;

    public Grafo() {
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size + 1;
    }

    public List<Aresta> getArestas() {
        return this.arestas;
    }

    public void addAresta(Vertice inicio, Vertice fim, double peso) {
        //Arestas para adjacentes do vértice
        Aresta arestaIF = new Aresta(inicio, fim, peso);
        Aresta arestaFI = new Aresta(fim, inicio, peso);

        inicio.addAdj(arestaIF);
        fim.addAdj(arestaFI);

        this.arestas.add(arestaIF);
    }

    public Vertice addVertice(int id) {
        Vertice auxVertice = contemVertice(id);

        if (auxVertice == null) {
            Vertice vertice = new Vertice(id);
            this.vertices.add(vertice);

            return vertice;
        } else {
            return auxVertice;
        }
    }

    private Vertice contemVertice(int id) {
        for (Vertice v : this.vertices) {
            if (id == v.getId()) {
                return v;
            }
        }

        return null;
    }

    public String toString() {
        String saida = "";
        for (Aresta aresta: arestas) {
            saida = saida + aresta.toString() + "\n";
        }
        return saida.trim();
    }

    public List<Vertice> getVertices() {
        return this.vertices;
    }
}
