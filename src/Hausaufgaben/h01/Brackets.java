package Hausaufgaben.h01;

import java.util.ArrayDeque;

public class Brackets {

    /**
     * Diese Funktion ueberprueft einen String auf Symmetrie von Klammern ('()'; '[]'; '{}').
     *
     * @param s String der ueberprueft werden soll.
     * @return Wahrheitswert ob die Klammern von String s symmetrisch sind.
     */
    public static boolean isValid(String s) {
        ArrayDeque<Character> arr = new ArrayDeque<>();
        for (char x : s.toCharArray()) {
            if (x == '(' || x == '[' || x == '{')
                arr.addLast(x);
            if (x == ')' || x == ']' || x == '}') {
                if (arr.isEmpty())
                    return false;
                if (getInverse(arr.getLast()) == x)
                    arr.removeLast();
                else
                    return false;
            }
        }
        return arr.isEmpty();
    }

    /**
     * Hilfsfunktion welche von einer offenen Klammer ('('; '['; '{') die passende geschlossende Klammer (')'; ']'; '}')
     * zurueck gibt.
     *
     * @param c Klammer von welcher die geschlossende Klammer zurueck gegeben werden soll.
     * @return die passende geschlossende Klammer
     */
    private static char getInverse(char c) {
        return switch (c) {
            case '(' -> ')';
            case '[' -> ']';
            case '{' -> '}';
            default -> '#';
        };
    }

    // Testen
    public static void main(String[] args) {
        System.out.println("Test 1: " + isValid("(([[]]))"));               // true
        System.out.println("Test 2: " + isValid("([)]"));                   // false
        System.out.println("Test 3: " + isValid("([]])"));                  // false
        System.out.println("Test 4: " + isValid("(()))"));                  // false
        System.out.println("Test 5: " + isValid("(()"));                    // false
        System.out.println("Test 6: " + isValid("({[])}"));                 // false
        System.out.println("Test 7: " + isValid("Gar keine Klammern"));     // true
        System.out.println("Test 8: " + isValid("(a[b{c{d[e(f)g]h}i}j]k)"));// true
    }
}