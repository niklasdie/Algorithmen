package Hausaufgaben.h06;

import java.util.ArrayList;

/**
 * Jedes Objekt dieser Klasse dient der Speicherung eines Graphen
 * mit Hilfe von Adjazenzlisten wie in der Vorlesung definiert.<br>
 * Loesung zu algo-h06 im SS 2021.
 */
public class MyGraph extends Graph {
    //TODO: Die noetigen Konstruktoren implementieren
    //TODO: Implementation der im Aufgabentext gewuenschten Operationen.

    public MyGraph(int v) {
        super(v);
    }

    public MyGraph(int v, int e) {
        super(v, e);
    }

    public MyGraph(int[] list) {
        super(list);
    }

    public ArrayList<Integer> getEdgeList() {
        ArrayList<Integer> res = this.firstTwoElements();
        for (int i = 0; i <= this.getVertexCount(); i++) {
            ArrayList<Integer> nachfolger = this.getAdjacent(i);
            for (int j = 0; j < nachfolger.size(); j++) {
                res.add(i);
                res.add(nachfolger.get(j));
            }
        }
        return res;
    }

    public ArrayList<Integer> getVertexList() {
        ArrayList<Integer> res = this.firstTwoElements();
        for (int i = 0; i <= this.getVertexCount(); i++) {
            ArrayList<Integer> nachfolger = this.getAdjacent(i);
            int anzahl = this.ausgangsgrad(i);
            if (anzahl != 0)
                res.add(anzahl);
            for (int j = 0; j < anzahl; j++) {
                res.add(nachfolger.get(j));
            }
        }
        return res;
    }

    public int[][] getAdjacencyMatrix() {
        int anzahlKnoten = this.getVertexCount();
        int[][] res = new int[anzahlKnoten][anzahlKnoten];
        for (int i = 0; i < anzahlKnoten; i++) // Array füllen
            for (int j = 0; j < anzahlKnoten; j++)
                res[i][j] = 0;
        for (int i = 0; i < anzahlKnoten; i++) {
            ArrayList<Integer> nachfolger = this.getAdjacent(i+1);
            for (int j = 0; j < nachfolger.size(); j++) {
                res[i][nachfolger.get(j) - 1] = 1;
            }
        }
        return res;
    }

    public ArrayList<Integer> bfs(int start) {
        boolean[] check = new boolean[this.getVertexCount()];
        for (boolean x : check) // Array füllen
            x = false;
        return this.bfsRekusiv(start, check);
    }

    private ArrayList<Integer> bfsRekusiv(int start, boolean[] check) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = start - 1; i < this.getVertexCount(); i++) {
            if (!check[i]) {
                ArrayList<Integer> nachfolger = this.getAdjacent(i);
                check[i] = true;
                if (!check[nachfolger.get(i) - 1])
                    res.addAll(this.bfsRekusiv(nachfolger.get(i), check));
            }
        }
        return res;
    }

    public ArrayList<Integer> dfs(int start) {
        boolean[] check = new boolean[this.getVertexCount()];
        for (boolean x : check) // Array füllen
            x = false;
        return this.dfsRekusiv(start, check);
    }

    private ArrayList<Integer> dfsRekusiv(int start, boolean[] check) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = start - 1; i < this.getVertexCount(); i++) {
            if (!check[i]) {
                ArrayList<Integer> nachfolger = this.getAdjacent(i);
                check[i] = true;
                if (!check[nachfolger.get(i) - 1])
                    res.addAll(this.bfsRekusiv(nachfolger.get(i), check));
            }
        }
        return res;
    }

    public ArrayList<Integer> getUnreachableVertices(int start) {
        int anzahl = this.getVertexCount();
        boolean[] check = new boolean[anzahl];
        for (boolean x : check) // Array füllen
            x = false;
        this.getUnreachableVerticesRekusiv(start, check);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < anzahl; i++)
            if (!check[i])
                res.add(i + 1);
        return res;
    }

    private void getUnreachableVerticesRekusiv(int start, boolean[] check) {
        for (int i = start - 1; i < this.getVertexCount(); i++) {
            if (!check[i]) {
                ArrayList<Integer> nachfolger = this.getAdjacent(i);
                check[i] = true;
                if (!check[nachfolger.get(i) - 1])
                    this.bfsRekusiv(nachfolger.get(i), check);
            }
        }
    }

    private ArrayList<Integer> firstTwoElements() {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(this.getVertexCount());
        res.add(this.getEdgeCount());
        return res;
    }

}

