package grafo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
public class Vertice implements Comparable<Vertice>{
    private static final int INFINITO = 100000000;

    int id;
    List<Vertice> verticesAdjacentes;
    private double distancia;

    public Vertice(int id ) {
        this.setId(id);
        this.verticesAdjacentes = new ArrayList<Vertice>();
        this.distancia = INFINITO;
    }

    public void addVerticeAdjacente(Vertice vertice) {
        this.verticesAdjacentes.add(vertice);
    }

    public List<Vertice> getVerticesAdjacentes() {
        return this.verticesAdjacentes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return getId() == vertice.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.valueOf(this.getId());
    }

    @Override
    public int compareTo(Vertice vertice) {
        if (this.distancia < vertice.distancia) {
            return -1;
        } else if (this.distancia > vertice.distancia) {
            return 1;
        }

        return 0;
    }
}
