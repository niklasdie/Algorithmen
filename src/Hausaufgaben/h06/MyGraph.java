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

    /**
     * Erzeugt leeren Graphen mit der angegebenen Anzahl an Knoten
     *
     * @param  v  die Anzahl der Knoten
     */
    public MyGraph(int v) {
        super(v);
    }

    /**
     * Erzeugt einen Graphen mit v Knoten und e zufaelligen Kanten
     *
     * @param  v  die Anzahl der Knoten
     * @param  e  die Anzahl der zufaelligen Kanten
     */
    public MyGraph(int v, int e) {
        super(v, e);
    }

    /**
     * Erzeugt einen Graphen aus der angegebenen Kantenliste
     *
     * @param  list  die Kantenliste
     */
    public MyGraph(int[] list) {
        super(list);
    }

    /**
     * Gibt den Graphen als Kantenliste zurueck
     *
     * @return Kantenliste
     */
    public ArrayList<Integer> getEdgeList() {
        ArrayList<Integer> res = this.firstTwoElements();
        for (int i = 0; i <= this.getVertexCount(); i++) {
            ArrayList<Integer> nachfolger = this.getAdjacent(i);
            for (Integer integer : nachfolger) {
                res.add(i);
                res.add(integer);
            }
        }
        return res;
    }

    /**
     * Gibt den Graphen als Knotenliste zurueck
     *
     * @return Knotenliste
     */
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

    /**
     * Gibt den Graphen als Adjazenzmatrix zurueck. Die Adjazenzmatrix soll keine Dummy-Zeile/Spalte zu Beginn haben.
     *
     * @return Adjazenzmatrix
     */
    public int[][] getAdjacencyMatrix() {
        int anzahlKnoten = this.getVertexCount();
        int[][] res = new int[anzahlKnoten][anzahlKnoten];
        for (int i = 0; i < anzahlKnoten; i++) // Array füllen
            for (int j = 0; j < anzahlKnoten; j++)
                res[i][j] = 0;
        for (int i = 0; i < anzahlKnoten; i++) {
            ArrayList<Integer> nachfolger = this.getAdjacent(i + 1);
            for (Integer integer : nachfolger) {
                res[i][integer - 1] = 1;
            }
        }
        return res;
    }

    /**
     * Durchlaeuft den Graphen mit Breitensuche und gibt eine Liste der Knoten in durchlaufener Reihenfolge zurueck
     *
     * @param start Startknoten
     * @return durchlaufende Reihenfolge der Breitensuche
     */
    public ArrayList<Integer> bfs(int start) {
        boolean[] check = new boolean[this.getVertexCount()];
        ArrayList<Integer> res = new ArrayList<>();
        for (boolean x : check) // Array füllen
            x = false;
        this.bfsRekusiv(start, check, res);
        return res;
    }

    /**
     * Hilfsfunktion für Breitensuche
     *
     * @param start Startknoten
     * @param check Checkliste
     * @param res Liste der Ergebnisknoten
     */
    private void bfsRekusiv(int start, boolean[] check, ArrayList<Integer> res) {
        ArrayList<Integer> nachfolger = this.getAdjacent(start);
        if (!check[start - 1]) {
            res.add(start);
            check[start - 1] = true;
        }
        for (Integer integer : nachfolger) {
            if (!check[integer - 1]) {
                res.add(integer);
                check[integer - 1] = true;
            }
        }
        for (int i = start; i < nachfolger.size(); i++) {
            this.bfsRekusiv(nachfolger.get(i), check, res);
        }
    }

    /**
     * Durchlaeuft den Graphen mit Tiefensuche und gibt eine Liste der Knoten in durchlaufener Reihenfolge zurueck
     *
     * @param start Startknoten
     * @return durchlaufende Reihenfolge der Tiefensuche
     */
    public ArrayList<Integer> dfs(int start) {
        boolean[] check = new boolean[this.getVertexCount()];
        ArrayList<Integer> res = new ArrayList<>();
        for (boolean x : check) // Array füllen
            x = false;
        this.dfsRekusiv(start, check, res);
        return res;
    }

    /**
     * Hilfsfunktion für Tiefensuche
     *
     * @param start Startknoten
     * @param check Checkliste
     * @param res Liste der Ergebnisknoten
     */
    private void dfsRekusiv(int start, boolean[] check, ArrayList<Integer> res) {
        ArrayList<Integer> nachfolger = this.getAdjacent(start);
        if (!check[start - 1]) {
            res.add(start);
            check[start - 1] = true;
        }
        for (Integer integer : nachfolger) {
            if (!check[integer - 1]) {
                check[integer - 1] = true;
                res.add(integer);
                this.dfsRekusiv(integer, check, res);
            }
        }
    }

    /**
     * Gibt die Liste aller Knoten zurueck, die vom Knoten start aus nicht erreichbar sind
     *
     * @param start Startknoten
     * @return nicht erreichbare Knoten vom Startknoten aus
     */
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

    /**
     * Hilfsfunktion für nicht erreichbare Knoten
     *
     * @param start Startknoten
     * @param check Checkliste
     */
    private void getUnreachableVerticesRekusiv(int start, boolean[] check) {
        ArrayList<Integer> nachfolger = this.getAdjacent(start);
        if (!check[start - 1]) {
            check[start - 1] = true;
        }
        for (Integer integer : nachfolger) {
            if (!check[integer - 1]) {
                check[integer - 1] = true;
                this.getUnreachableVerticesRekusiv(integer, check);
            }
        }
    }

    /**
     * Hilfsmethode die für Breiten/- und Tiefensuche die ersten beiden Elemente hinzufuegt.
     *
     * @return  ersten beiden Elemente
     */
    private ArrayList<Integer> firstTwoElements() {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(this.getVertexCount());
        res.add(this.getEdgeCount());
        return res;
    }
}