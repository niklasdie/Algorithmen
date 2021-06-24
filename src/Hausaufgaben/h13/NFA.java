package Hausaufgaben.h13;

public class NFA {

    private final String[] knots;
    private final int target;
    private boolean res;

    /**
     * Erstellt einen NFA (nondeterministic finite automaton) anhand der gegebenen Kantenliste.
     *
     * @param x Kanntenliste als String
     */
    public NFA(String x) {
        int knotCount = x.charAt(0) - 48;
        this.target = x.charAt(2) - 48;
        knots = new String[knotCount];
        for (int it = 4; it < x.length(); it += 6) {
            if (knots[x.charAt(it) - 49] == null)
                knots[x.charAt(it) - 49] = "";
            knots[x.charAt(it) - 49] += "" + x.charAt(it + 4) + x.charAt(it + 2);
        }
    }

    /**
     * Ueberprueft ob der angegebene String durch den NFA akzeptiert wird oder nicht.
     *
     * @param s zu ueberpruefener String
     * @return ob der angegebene String durch den NFA akzeptiert wird oder nicht
     */
    public boolean testString(String s) {
        this.res = false;
        this.rekursivTestString(s, 0, 1);
        return this.res;
    }

    /**
     * Hilfsmethode, die den Wahrheitswert ermittelt, ob der NFA den String akzeptiert oder nicht.
     *
     * @param s zu ueberpruefener String
     * @param it Iterrator
     * @param knot aktueller Knoten
     */
    private void rekursivTestString(String s, int it, int knot) {
        if (s.length() == it) {
            if(knot == this.target)
                this.res = true;
            return;
        }
        if (knots[knot - 1] == null)
            return;
        for (int i = 0; i < knots[knot - 1].length() - 1; i += 2) {
            if (knots[knot - 1].charAt(i) == s.charAt(it))
                this.rekursivTestString(s, it + 1, knots[knot - 1].charAt(i + 1) - 48);
        }
    }

    // Testen
    public static void main(String[] args) {
        NFA nfa_test = new NFA("3,3,1,2,a,1,3,a,2,2,a,2,2,b,2,3,a");
        System.out.println(nfa_test.testString("abba")); //true
        System.out.println(nfa_test.testString("a")); //true
        System.out.println(nfa_test.testString("ab")); //false
    }
}
