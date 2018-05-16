package grafo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
public class Vertice {

    int id;
    List<Aresta> adjacentes;

    public Vertice(int id ) {
        this.setId(id);
        this.adjacentes = new ArrayList<Aresta>();
    }

    public void addAdj(Aresta aresta) {
        this.adjacentes.add(aresta);
    }

    public List<Aresta> getAdj() {
        return this.adjacentes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
}
