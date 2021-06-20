package Hausaufgaben.h12;

import java.util.ArrayList;

public class TextSearch {

    /**
     * Sucht ein angegebendes Pattern in einem angegebenen String und gibt alle Startpositionen des Patterns
     * im Text zurueck.
     *
     * @param text    String in dem gesucht wird
     * @param pattern Pattern was gesucht wird
     * @return alle Startpositionen des Patterns im Text
     */
    public static ArrayList<Integer> textSearch(String text, String pattern) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<String> newPattern = toStringArray(pattern);
        for (int textIndex = 0; textIndex < text.length() - newPattern.size() + 1; textIndex++) {
            for (int patternIndex = 0; patternIndex < newPattern.size(); patternIndex++) {
                switch (newPattern.get(patternIndex).charAt(0)) {
                    case '@' -> {
                        if (newPattern.get(patternIndex).contains("" + text.charAt(textIndex + patternIndex))) {
                            if (patternIndex == newPattern.size() - 1)
                                res.add(textIndex);
                        } else {
                            patternIndex = newPattern.size();
                        }
                    }
                    case '\\' -> {
                        if (text.charAt(textIndex + patternIndex) == newPattern.get(patternIndex).charAt(1)) {
                            if (patternIndex == newPattern.size() - 1)
                                res.add(textIndex);
                        } else {
                            patternIndex = newPattern.size();
                        }
                    }
                    case '.' -> {
                        if (patternIndex == newPattern.size() - 1)
                            res.add(textIndex);
                    }
                    default -> {
                        if (text.charAt(textIndex + patternIndex) == newPattern.get(patternIndex).charAt(0)) {
                            if (patternIndex == newPattern.size() - 1)
                                res.add(textIndex);
                        } else {
                            patternIndex = newPattern.size();
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * Hilfsfunktion, die eine ArrayList<String> erzeugt, die beim Suchen des Patterns hilft.
     * @param pattern Pattern
     * @return ArrayList<String></String>
     */
    private static ArrayList<String> toStringArray(String pattern) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < pattern.length(); i++) {
            switch (pattern.charAt(i)) {
                case '[' -> {
                    StringBuilder tmp = new StringBuilder();
                    tmp.append("@");
                    i++;
                    while (pattern.charAt(i) != ']') {
                        tmp.append(pattern.charAt(i));
                        i++;
                    }
                    list.add(tmp.toString());
                }
                case '\\' -> {
                    list.add("\\" + pattern.charAt(i + 1));
                    i++;
                }
                default -> list.add("" + pattern.charAt(i));
            }
        }
        return list;
    }

    // Testen
    public static void main(String[] args) {
        System.out.println(textSearch("abcabcdababdc.", "ab")); //0, 3, 7, 9
        System.out.println(textSearch("abcabcdababdc.", "c.")); //2, 5, 12
        System.out.println(textSearch("abcabcdababdc.", "c\\.")); //12
        System.out.println(textSearch("abcabcdababdc.", "b[cd]")); //1,4,10
        System.out.println(textSearch("abcabcdababdc.", "a....c")); //0,7
        System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); //3,5
        System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); //7
    }
}
