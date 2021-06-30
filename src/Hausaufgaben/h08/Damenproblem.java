package Hausaufgaben.h08;

/**
 * @author Gruppe 24
 */
public class Damenproblem {
    private static int validResults = 0;

    // Test
    public static void main(String[] args) {
        damenProblem(10);
    }

    /**
     * Gibt alle validen Loesungen des Damenproblems fuer ein n x n grosses Brett
     * aus.
     *
     * @param brettgroesse gibt die Hoehe und Breite des Brettes vor
     */
    public static void damenProblem(int brettgroesse) {
        if (brettgroesse < 1)
            throw new ArithmeticException("Brettgroesse kann nicht negativ sein");
        rekursivDamenProblem(new boolean[brettgroesse][brettgroesse], 0, 0, brettgroesse);
        System.out.println(validResults + " valide Lösungen");
    }

    /**
     * Hilfsfunktion, die Rekursiv die validen Loesungen ermittelt.
     *
     * @param brett        positionen der Damen
     * @param row          Koordinate der Reihe
     * @param col          Koordinate der Spalte
     * @param brettgroesse Groesse des Brettes
     */
    private static void rekursivDamenProblem(boolean[][] brett, int row, int col, int brettgroesse) {
        if (row == brettgroesse) {
            ausgabe(brett);
            return;
        }

        if (isValid(brett, row, col)) {
            brett[row][col] = true;
            rekursivDamenProblem(brett, row + 1, 0, brettgroesse);
            brett[row][col] = false;
        }

        if (col < brettgroesse - 1)
            rekursivDamenProblem(brett, row, col + 1, brettgroesse);
    }

    /**
     * Hilfsfunktion, die die Damenpositionen fuer jedes Ergebnis visualisiert und
     * diese in Reihenkoordinaten ausgibt.
     *
     * @param brett positionen der Damen
     */
    private static void ausgabe(boolean[][] brett) {
        validResults++;
        System.out.println("Ergebnis " + validResults + ":");
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < brett.length; row++) {
            boolean first = true;
            sb = new StringBuilder();
            for (int col = 0; col < brett[row].length; col++) {
                if (first) {
                    sb.append("|");
                    first = false;
                }
                if (brett[row][col])
                    sb.append("X|");
                else
                    sb.append(" |");
            }
            System.out.println(sb);
        }

        sb = new StringBuilder();
        sb.append("[");
        for (boolean[] y : brett)
            for (int x = 0; x < y.length; x++)
                if (y[x])
                    sb.append(x + 1).append(", ");
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        System.out.println(sb);
        System.out.println("\n------------------------\n");
    }

    /**
     * Hilfsfunktion die ueberprueft, ob die Position row, col valide ist.
     *
     * @param brett positionen der Damen
     * @param row   Koordinate der Reihe
     * @param col   Koordinate der Spalte
     * @return ob die Position row, col valide ist
     */
    private static boolean isValid(boolean[][] brett, int row, int col) {
        int brettgroesse = brett.length;
        for (int i = 0; i < brett.length; i++)
            if (brett[row][i] || brett[i][col])
                return false;
        for (int i = 0; i < brettgroesse; i++)
            for (int j = 0; j < brettgroesse; j++)
                if (brett[i][j])
                    if (Math.abs(i - row) == Math.abs(j - col))
                        return false;

        return true;
    }
}
