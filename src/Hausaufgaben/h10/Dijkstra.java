package Hausaufgaben.h10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dijkstra {

    private static boolean[] check;

    /**
     * Diese Funktion zeigt den kuerzesten Weg eines Graphens vom Startknoten 1 nach dem Dijkstra-Algorithmus.
     *
     * @param kanten Kantenliste
     */
    public static void printDijkstra(int[] kanten) {
        check = new boolean[kanten[0]];
        int[][] dijkstra = new int[kanten[0]][kanten[0] * 2 - 1];
        ArrayList<Integer> warte = new ArrayList<>();
        warte.add(1);
        rekursivPrintDijkstra(kanten, dijkstra, 1, 0, 1, warte);
        ausgabe(dijkstra);
    }

    /**
     * Hilfsfunktion, die den kuerzensten Weg berechnet.
     *
     * @param kanten   Kantenliste
     * @param dijkstra Dijkstraliste
     * @param pos      Position in Kantenliste
     * @param y        Zeile der Dijkstraliste
     * @param last     letzter Knoten
     * @param warte    Warteliste
     */
    private static void rekursivPrintDijkstra(int[] kanten, int[][] dijkstra, int pos, int y, int last, ArrayList<Integer> warte) {

        try {
            if (dijkstra[y][0] == 0) {
                dijkstra[y][0] = warte.get(0);
                pos = getPos(kanten, warte.get(0));
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        int lowest = 0;
        int lowestIndex = 0;
        if (y > 0) {
            int[] tmp = new int[kanten[0] - 1];
            for (int j = 0; j < kanten[0] - 1; j++)
                if (dijkstra[y - 1][kanten[0] + j] == last || (!check[j + 1] && dijkstra[y][j + 1] != 0))
                    tmp[j] = j + 1;
            for (int i : tmp)
                if (i != 0)
                    if (dijkstra[y][i] < lowest || lowest == 0)
                        lowest = dijkstra[y][i];
        }

        if (dijkstra[y][kanten[pos + 1] - 1] > kanten[pos + 2] + lowest || dijkstra[y][kanten[pos + 1] - 1] == 0) {
            for (int j = y; j < dijkstra.length; j++) {
                dijkstra[j][kanten[pos + 1] - 1] = kanten[pos + 2] + lowest;
                dijkstra[j][kanten[0] + kanten[pos + 1] - 2] = warte.get(0);
            }
        }

        lowest = 0;
        try {
            if (kanten[pos + 3] != warte.get(0)) {
                check[warte.get(0) - 1] = true;
                int[] tmp = new int[kanten[0] - 1];
                for (int j = 0; j < kanten[0] - 1; j++)
                    if (dijkstra[y][kanten[0] + j] == warte.get(0) && !warte.contains(j + 2))
                        warte.add(j + 2);
                last = warte.get(0);
                warte.remove(0);
                for (int j = 0; j < warte.size(); j++)
                    if (lowest == 0 || dijkstra[y][warte.get(j) - 1] < lowest) {
                        lowest = dijkstra[y][warte.get(j) - 1];
                        lowestIndex = j;
                    }
                if (!warte.isEmpty())
                    Collections.swap(warte, 0, lowestIndex);
                y++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            check[warte.get(0) - 1] = true;
            last = warte.get(0);
            warte.remove(0);
            if (checkAllKnot())
                startAtNotUsedKnot(kanten, dijkstra, last, warte);
        }
        if (checkAllKnot())
            rekursivPrintDijkstra(kanten, dijkstra, pos + 3, y, last, warte);
    }

    /**
     * Hilfsfunktion, die ueberprueft ob alle Knoten durchlaufen wurden.
     *
     * @return ob alle Knoten durchlaufen wurden
     */
    private static boolean checkAllKnot() {
        for (boolean b : check)
            if (!b)
                return true;
        return false;
    }

    /**
     * Hilfsfunktion, die die rekursive Funktion von den noch nicht durchlaufenen Knoten startet.
     *
     * @param kanten   Kantenliste
     * @param dijkstra Dijkstraliste
     * @param last     letzter Knoten
     * @param warte    Warteliste
     */
    private static void startAtNotUsedKnot(int[] kanten, int[][] dijkstra, int last, ArrayList<Integer> warte) {
        for (int i = 0; i < check.length; i++)
            if (!check[i])
                rekursivPrintDijkstra(kanten, dijkstra, 1, i + 1, last, warte);
    }

    /**
     * Hilfsfunktion, die die Position eines Knoten in der Kantenliste angibt.
     *
     * @param kanten Kantenliste
     * @param knot   Knoten
     * @return Position des Knoten in der Kantenliste
     */
    private static int getPos(int[] kanten, int knot) {
        for (int i = 1; i < kanten.length; i += 3)
            if (kanten[i] == knot)
                return i;
        return -1;
    }

    /**
     * Hilfsfunktion, die die Dijkstraliste ausgibt.
     *
     * @param dijkstra Dijkstraliste
     */
    private static void ausgabe(int[][] dijkstra) {
        for (int[] x : dijkstra)
            System.out.println(Arrays.toString(x));
    }

    // Test
    public static void main(String[] args) {
        printDijkstra(new int[]{4, 1, 2, 2, 1, 4, 5, 2, 4, 1, 2, 3, 4, 3, 1, 1, 4, 3, 1});
        System.out.println();
        printDijkstra(new int[]{10, 1, 2, 30, 1, 3, 10, 2, 5, 15, 2, 8, 55, 3, 4, 5, 3,
                9, 35, 4, 2, 10, 4, 5, 45, 4, 6, 10, 5, 3, 20, 5, 7, 15, 5, 9, 25, 6, 7, 5, 7, 10, 20, 8,
                10, 15, 9, 8, 10, 9, 10, 30});
    }
}
