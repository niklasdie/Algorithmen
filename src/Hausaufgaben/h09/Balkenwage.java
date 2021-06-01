package Hausaufgaben.h09;

import java.util.Arrays;
import java.util.Scanner;

public class Balkenwage {

    private final static int[] WEIGHTS = {1, 3, 8, 20};

    // Testfunktion
    public static void main(String[] args) {
        backtracking();
    }

    /**
     * Gibt alle moeglichen Gewichtskombinationen aus, die zu dem eingegebenen Gewicht
     * "productWeight" fuehren
     */
    public static void backtracking() {
        int productWeight;
        System.out.print("Gewicht des Artikels: ");
        try (Scanner sc = new Scanner(System.in)) {
            String in = sc.nextLine();
            productWeight = Integer.parseInt(in);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Eingabe ist keine Zahl!");
        }

        if (backtrackingRekursiv(WEIGHTS, new int[WEIGHTS.length], 0, productWeight, 0) == 0)
            System.out.println("Keine kombination der Gewichte " + Arrays.toString(WEIGHTS) + " ergibt ein Gewicht von "
                    + productWeight);
    }

    /**
     * Hilfsfunktion, die die moeglichen Gewichtskombinationen ermittelt und 0
     * zurueck gibt, wenn keine Loesung vorhanden ist
     *
     * @param weights       vorgegebene Gewichte
     * @param weightWay     bisher gewaehlte Gewichte
     * @param index         jetziger Index
     * @param productWeight gewicht des Produktes
     * @param loesungsMenge 0, wenn bisher kein Ergebnis ermittelt wurde
     * @return 0, wenn keine Loesung ermittelt wurde
     */
    public static int backtrackingRekursiv(int[] weights, int[] weightWay, int index, int productWeight,
                                           int loesungsMenge) {
        // wenn alle Gewichte durchgelaufen sind Das endgewicht abfragen:

        if (index >= weights.length) {
            int w = 0;
            for (int i = 0; i < weightWay.length; i++)
                switch (weightWay[i]) {
                    case 1 -> w += weights[i];
                    case -1 -> w -= weights[i];
                }
            if (w == productWeight) {
                ausgabe(transformToWeightsString(weights, weightWay), productWeight);
                return 1;
            } else {
                return 0;
            }
        }

        weightWay[index] = 1;
        loesungsMenge += backtrackingRekursiv(weights, weightWay, index + 1, productWeight, loesungsMenge);
        weightWay[index] = 0;
        loesungsMenge += backtrackingRekursiv(weights, weightWay, index + 1, productWeight, loesungsMenge);
        weightWay[index] = -1;
        loesungsMenge += backtrackingRekursiv(weights, weightWay, index + 1, productWeight, loesungsMenge);

        return loesungsMenge;
    }

    /**
     * Hilfsfunktion zur Anzeige der durchlaufenen Gewichte
     *
     * @param weights   Gewichte
     * @param weightWay durchlaufene Gewichte
     * @return Ergebnisstring
     */
    private static int[] transformToWeightsString(int[] weights, int[] weightWay) {
        if (weights.length != weightWay.length) {
            throw new ArithmeticException("Die Arrays sind verschieden lang");
        }

        // Anzahl der Nullen ermitteln:
        int effectiveLength = weightWay.length;
        for (int j : weightWay)
            if (j == 0)
                effectiveLength--;

        int[] res = new int[effectiveLength];

        int offset = 0;
        for (int i = 0; i < weights.length; i++)
            switch (weightWay[i]) {
                case 1 -> res[i - offset] = weights[i];
                case -1 -> res[i - offset] = -weights[i];
                default -> offset++;
            }

        return res;
    }

    /**
     * Hilfsfunktion, die das Ergebnis ausgibt.
     *
     * @param arr           Ergebnisarray
     * @param productWeight gewicht des Produktes
     */
    private static void ausgabe(int[] arr, int productWeight) {
        StringBuilder res = new StringBuilder();
        for (int x : arr)
            if (x < 0)
                res.append(x);
            else
                res.append("+").append(x);
        res.append(" = ").append(productWeight);
        System.out.println(res);
    }
}
