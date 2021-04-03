package Hausaufgaben.h01;

import java.util.ArrayDeque;

public class Brackets {

    /**
     * Diese Funktion ueberprueft einen String auf Symmetrie von Klammern ('()'; '[]'; '{}').
     * Klammern duerfen sich nicht ueberschneiden, heißt die Klammern muessen in der gleichen Reihenfolge geschlossen
     * werden wie sie geöffnet wurden.
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
        System.out.println("Test 9: " + isValid("()[]{}()[]{}"));           // true

        String testExtreme = "package h11;\n" +
                "\n" +
                "/**\n" +
                " * Diese Klasse kann Zahlen aus dem Binaer-, Dezimal- und Hexadezimalsystem erhalten\n" +
                " * und diese in eins der anderen Zahlensysteme umrechnen und zurueckgeben.\n" +
                " */\n" +
                "public class PositiveNumber {\n" +
                "    private final String hexDigits = \"0123456789ABCDEF\";\n" +
                "    private int value; // es wird immer die Dezimalzahl gespeichert\n" +
                "\n" +
                "    /**\n" +
                "     * Konstruktor der ein neues PositiveNumber Objekt erzeugt.\n" +
                "     */\n" +
                "    public PositiveNumber() {\n" +
                "        this.value = 0;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Gibt die Zahl als Dezimalzahl zurueck.\n" +
                "     *\n" +
                "     * @return Dezimalzahl als String\n" +
                "     */\n" +
                "    public String getDecimal() {\n" +
                "        return \"\" + this.value;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Setzt die Zahl aus der Dezimalzahl.\n" +
                "     *\n" +
                "     * @param s Dezimalzahl als String\n" +
                "     */\n" +
                "    public void setDecimal(String s) {\n" +
                "        this.setValue(s, 10);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Gibt die Zahl als Hexadezimalzahl zurueck.\n" +
                "     *\n" +
                "     * @return Hexadezimalzahl als String\n" +
                "     */\n" +
                "    public String getHexadecimal() {\n" +
                "        return this.getValue(16);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Setzt die Zahl aus der Hexadezimalzahl.\n" +
                "     *\n" +
                "     * @param s Hexadezimalzahl als String\n" +
                "     */\n" +
                "    public void setHexadecimal(String s) {\n" +
                "        s = s.toUpperCase();\n" +
                "        this.setValue(s, 16);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Gibt die Zahl als Binaerzahl zurueck.\n" +
                "     *\n" +
                "     * @return Binaerzahl als String\n" +
                "     */\n" +
                "    public String getBinary() {\n" +
                "        return this.getValue(2);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Setzt die Zahl aus der Binaerzahl.\n" +
                "     *\n" +
                "     * @param s Binaerzahl als String\n" +
                "     */\n" +
                "    public void setBinary(String s) {\n" +
                "        this.setValue(s, 2);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Hilfsmethode die value setzt egal ob Dezimal oder Hexadezimal.\n" +
                "     *\n" +
                "     * @param s Zahl als String\n" +
                "     * @param x 10 oder 16 je nach Dezimal oder Hexadezimal\n" +
                "     */\n" +
                "    private void setValue(String s, int x) { // Codedopplung Vermeidung\n" +
                "        this.value = 0;\n" +
                "        this.proofBinDecHex(s, x);\n" +
                "        for (int i = 0; i < s.length(); i++) {\n" +
                "            char c = s.charAt(i);\n" +
                "            int d = this.hexDigits.indexOf(c);\n" +
                "            this.value = this.value * x + d;\n" +
                "        }\n" +
                "        this.proofVal();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Hilfsmethode die value zurueck gibt egal ob Dezimal oder Hexadezimal.\n" +
                "     *\n" +
                "     * @param x 2 oder 16 je nach Binaer oder Hexadezimal\n" +
                "     */\n" +
                "    private String getValue(int x){ // Codedopplung Vermeidung\n" +
                "        int v = this.value;\n" +
                "        StringBuilder res = new StringBuilder();\n" +
                "        if (v == 0) {\n" +
                "            res.append(\"0\");\n" +
                "        } else {\n" +
                "            while (v > 0) {\n" +
                "                res.insert(0, this.hexDigits.charAt(v % x));\n" +
                "                v /= x;\n" +
                "            }\n" +
                "        }\n" +
                "        return res.toString();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Hilfsmethode die prueft ob der String der eingegeben wurde zulaessig ist.\n" +
                "     *\n" +
                "     * @param s Zahl als String\n" +
                "     */\n" +
                "    private void proofBinDecHex(String s, int x) { // Codedopplung Vermeidung\n" +
                "        for (int i = 0; i < s.length(); i++) {\n" +
                "            int indexOf = hexDigits.indexOf(s.charAt(i));\n" +
                "            if (indexOf == -1 || indexOf > x - 1) {\n" +
                "                throw new NumberFormatException(\"Keine zulaessige Zahl!\");\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * Hilfsmethode die prueft ob die Zahl die eingegeben wurde zulaessig ist.\n" +
                "     */\n" +
                "    private void proofVal() {\n" +
                "        if (this.value < 0) { // Man braucht nicht zu ueberpruefen ob value > Integer.MAX_VALUE, da Ueberlauf\n" +
                "            this.value = 0;\n" +
                "            throw new NumberFormatException(\"Zahl < 0 oder > Integer.MAX_VALUE!\");\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // Testen\n" +
                "    public static void main(String[] args) {\n" +
                "        PositiveNumber zs = new PositiveNumber();\n" +
                "        zs.setDecimal(\"144\");\n" +
                "        System.out.println(\"Binaer: \" + zs.getBinary());\n" +
                "        zs.setHexadecimal(\"affe\");\n" +
                "        System.out.println(\"Dezimal: \" + zs.getDecimal());\n" +
                "        zs.setBinary(\"1000101011\");\n" +
                "        System.out.println(\"Hexadezimal: \" + zs.getHexadecimal());\n" +
                "    }\n" +
                "}\n";
        System.out.println("Test EXTREME: " + isValid(testExtreme));                // true
    }
}