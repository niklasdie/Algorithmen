package Hausaufgaben.h07;

import java.util.ArrayList;

public class Prim {

    // TestFunktion
    public static void main(String[] args) {
        int[][] adjazenzmatrix = {{0, 3, 0, 2, 0, 0}, {3, 0, 2, 0, 3, 0}, {0, 2, 0, 1, 6, 0},
                {2, 0, 1, 0, 0, 0}, {0, 3, 6, 0, 0, 5}, {0, 0, 0, 0, 5, 0}};
        System.out.println("resultierende kosten: " + getMST(adjazenzmatrix));
        System.out.println("---------------------------");
        System.out.println("resultierende kosten: " + getMST(5, adjazenzmatrix));
    }

    /**
     * Gibt per Primalgorithmus die vom minimalen Spannbaum addierten Wegkosten mit
     * Startknoten "start" zurueck.
     *
     * @param edges ungerichteter und zusamenhaengender Graph als Adjazenzmatrix
     * @return Wegkosten des minimalen Spannbaums
     */
    public static int getMST(int start, int[][] edges) {
        start--;
        ArrayList<Integer> order = new ArrayList<Integer>();
        order.add(start);

        boolean[] arr = new boolean[edges.length];
        arr[start] = true;

        System.out.println("Waehle " + (start + 1) + " als Startknoten");
        return getMSTRekursiv(order, edges, arr, start);
    }

    /**
     * Gibt per Primalgorithmus die vom minimalen Spannbaum addierten Wegkosten mit
     * Startknoten "1" zurueck.
     *
     * @param edges ungerichteter und zusamenhaengender Graph als Adjazenzmatrix
     * @return Wegkosten des minimalen Spannbaums
     */
    public static int getMST(int[][] edges) {
        return getMST(1, edges);
    }

    /**
     * Hilfsmethode, die die Wegkosten des minimalen Spannbaums berechnet.
     *
     * @param edges  ungerichteter und zusamenhaengender Graph als Adjazenzmatrix
     * @param check  bereits verwendete Knoten
     * @param knoten aktueller Knoten
     * @return Wegkosten vom jetzigen zum naechsten Knoten, 0 wenn bereits alle
     * moeglichen Knoten verwendet wurden
     */
    private static int getMSTRekursiv(ArrayList<Integer> list, int[][] edges, boolean[] check, int knoten) {
        int lowest = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < edges[knoten].length; i++)
            if (edges[knoten][i] > 0 && edges[knoten][i] < lowest && !check[i]) {
                index = i;
                lowest = edges[knoten][i];
            }
        if (index == -1) {
            for (boolean x : check)
                if (!x && !list.isEmpty()) {
                    int y = list.get(list.size() - 1);
                    if (list.size() > 1)
                        list.remove(list.size() - 1);
                    return getMSTRekursiv(list, edges, check, y);
                }
            return 0;
        } else {
            check[index] = true;
            list.add(index);
            System.out.println("Kante hinzugefuegt von " + (knoten + 1) + " zu " + (index + 1));
            return lowest + getMSTRekursiv(list, edges, check, index);
        }
    }
}