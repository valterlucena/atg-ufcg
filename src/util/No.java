package util;

import grafo.Vertice;

public class No implements Comparable<No>{
    public Vertice vertice;
    public double distancia;

    @Override
    public int compareTo(No no) {
        if (this.distancia < no.distancia) {
            return -1;
        } else if (this.distancia > no.distancia) {
            return 1;
        }
        return 0;
    }
}
