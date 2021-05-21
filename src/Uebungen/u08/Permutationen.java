package Uebungen.u08;

public class Permutationen {

    public static void printPermutationen(int n){
        printPermutationen(n, n, "");
    }

    private static void printPermutationen(int n, int start, String s) {
        if(n==0) {
            System.out.println(s);
            return;
        }
        for(int i = 1; i <= start; i++) {
            if(!s.contains(String.valueOf(i))) {
                String t = s + i;
                printPermutationen(n - 1, start, t);
            }
        }
    }

    public static void printPermutationen2(int n, int ges) {
        printPermutationen2(n, ges, "");
    }

    private static void printPermutationen2(int n, int ges, String s) {
        if(s.length() == ges) {
            char[] c = s.toCharArray();
            int count = 0;
            for (char x : c)
                if(x == 49)
                    count++;
            if(count == n)
                System.out.println(s);
            return;
        }
        for(int i = 0; i <= 1; i++) {
            String t = s + i;
            printPermutationen2(n, ges, t);
        }
    }

    public static void printPermutationen3(int n, int ges) {
        printPermutationen3(n, ges, "");
    }

    private static void printPermutationen3(int n, int ges, String s) {
        if(s.length() == ges) {
            char[] c = s.toCharArray();
            int count = 0;
            for (char x : c)
                if(x == 49)
                    count++;
            if(count == n)
                System.out.println(s);
            return;
        }
        for(int i = 0; i <= 1; i++) {
            String t = s + i;
            printPermutationen3(n, ges, t);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        printPermutationen(3);
        long end = System.currentTimeMillis();
        System.out.println("Dauer: " + (end-start) + "ms");
        System.out.println();
        start = System.currentTimeMillis();
        printPermutationen2(2, 4);
        end = System.currentTimeMillis();
        System.out.println("Dauer: " + (end-start) + "ms");
        System.out.println();
        start = System.currentTimeMillis();
        printPermutationen3(2, 4);
        end = System.currentTimeMillis();
        System.out.println("Dauer: " + (end-start) + "ms");
    }
}
