package main;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class main {

    public static void main(String[] args) {

        Grafo grafo = new Grafo();

        BufferedReader buffer = null;
        FileReader arquivo = null;

        try {
            arquivo = new FileReader("./entrada/entrada.txt");
            buffer = new BufferedReader(arquivo);

            int quantidadeDeVertices = buffer.read();
            String linhaAtual;

            while ((linhaAtual = buffer.readLine()) != null) {
                Aresta aresta;
                String arestaAtual;
                while ((arestaAtual = buffer.readLine()) != null) {
                    String[] argumentos = arestaAtual.split(" ");
                    Vertice inicio = new Vertice(Integer.parseInt(argumentos[0]));
                    Vertice fim = new Vertice(Integer.parseInt(argumentos[1]));
                    aresta = new Aresta(inicio, fim, 0); //peso default por enquanto
                    grafo.getArestas().add(aresta);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffer != null) buffer.close();
                if (arquivo != null) arquivo.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

        System.out.println(grafo);

        Set<Vertice> vertices = grafo.getVertices();
        for (Vertice vertice: vertices) {
            System.out.println(vertice.toString() + " " + grafo.getVertexDegree(vertice));
        }

        System.out.println(grafo.getMeanEdge());
    }
}
