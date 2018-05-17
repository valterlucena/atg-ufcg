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

    public void addAresta(Vertice inicio, Vertice fim, double peso) {
        Aresta aresta = new Aresta(inicio, fim, peso);

        inicio.addVerticeAdjacente(fim);
        fim.addVerticeAdjacente(inicio);

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size + 1;
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
