package main.com.ufcg.atg.grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private List<Aresta> arestas;
    private List<Vertice> vertices;

    public Grafo() {
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
    }

    public void addAresta(Vertice inicio, Vertice fim, double peso) {
        Aresta aresta = new Aresta(inicio, fim, peso);

        inicio.addVerticeAdjacente(fim);
        fim.addVerticeAdjacente(inicio);

        this.arestas.add(aresta);
    }

    public void criaVertices(int tamanho) {
        for (int i = 1; i <= tamanho; i++) {
            Vertice aux = criaVertice(i);
            this.vertices.add(aux);
        }
    }

    public Vertice criaVertice(int id) {
        Vertice auxVertice = getVertice(id);

        if (auxVertice == null) {
            return new Vertice(id);
        } else {
            return auxVertice;
        }
    }

    public int getSize() {
        return this.getVertices().size();
    }

    private Vertice getVertice(int id) {
        for (Vertice vertice : this.vertices) {
            if (id == vertice.getId()) {
                return vertice;
            }
        }

        return null;
    }

    public List<Vertice> getVertices() {
        return this.vertices;
    }

    public List<Aresta> getArestas() {
        return this.arestas;
    }

    /**
     * Retorna as arestas cujo os vértices fornecidos fazem parte
     *
     * @param vertices
     * @return
     */
    public ArrayList<Aresta> getArestasDosVertices(List<Vertice> vertices) {
        ArrayList<Aresta> result = new ArrayList<Aresta>();
        for (Vertice vertice : vertices) {
            for (Aresta aresta : this.getArestas()) {
                if (aresta.getFim().equals(vertice)) {
                    if (!result.contains(aresta)){
                        result.add(aresta);
                    }
                }
            }
        }

        return result;
    }

    public String toString() {
        String saida = "";
        for (Aresta aresta: arestas) {
            saida = saida + aresta.toString() + "\n";
        }
        return saida.trim();
    }
}
