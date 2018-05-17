package grafo;

import java.util.Objects;

public class Aresta {

    Vertice inicio;
    Vertice fim;
    double peso;

    public Aresta(Vertice inicio, Vertice fim, double peso) {
        this.setInicio(inicio);
        this.setFim(fim);
        this.setPeso(peso);
    }

    public Vertice getInicio() {
        return this.inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFim() {
        return this.fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public List<Vertice> getExtremidades() {
        List<Vertice> extremidades = new ArrayList<>();
        extremidades.add(this.getInicio());
        extremidades.add(this.getFim());

        return extremidades;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta aresta = (Aresta) o;
        return Objects.equals(getInicio(), aresta.getInicio()) &&
                Objects.equals(getFim(), aresta.getFim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInicio(), getFim());
    }

    @Override
    public String toString() {
        String saida = "";
        saida = this.getInicio().toString() + " -- " +
                String.valueOf(this.getPeso()) + " -- " + this.getFim().toString();
        return saida;
    }
}
