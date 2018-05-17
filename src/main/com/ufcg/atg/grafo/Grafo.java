package main.com.ufcg.atg.grafo;

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
        Aresta aresta = new Aresta(inicio, fim, peso);

        inicio.addAdj(fim);
        fim.addAdj(inicio);

        this.arestas.add(aresta);
    }

    public Vertice addVertice(int id) {
        Vertice auxVertice = getVertice(id);

        if (auxVertice == null) {
            Vertice vertice = new Vertice(id);
            this.vertices.add(vertice);

            return vertice;
        } else {
            return auxVertice;
        }
    }

    private Vertice getVertice(int id) {
        for (Vertice vertice : this.vertices) {
            if (id == vertice.getId()) {
                return vertice;
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
