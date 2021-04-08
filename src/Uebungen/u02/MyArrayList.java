package Uebungen.u02;

/**
 * Diese Klasse ist eine vereinfachte, selbst geschriebene ArrayList<T>.
 *
 * @param <T> Objekttyp welches die ArrayList fuellen soll.
 */
public class MyArrayList<T> {

    private T[] arr;
    private int index;

    /**
     * Konstruktor fuer MyArrayList.
     */
    @SuppressWarnings("unchecked") // wegen Konstruktor von T[]
    public MyArrayList() {
        this.arr = (T[]) new Object[10];
        this.index = 0;
    }

    /**
     * Fuegt den Parameter hinten an der ArrayList hinzu.
     *
     * @param data Objekt welches hinzugefuegt werden soll
     */
    public void add(T data) {
        this.proofSize();
        this.arr[index] = data;
        this.index++;
    }

    /**
     * Fuegt den Parameter an der Stelle pos in der ArrayList hinzu.
     *
     * @param data Objekt welches hinzugefuegt werden soll
     */
    public void add(int pos, T data) {
        this.proofIndex(pos);
        if (this.index > pos) {
            this.proofSize();
            if (index - pos >= 0) System.arraycopy(this.arr, pos, this.arr, pos + 1, index - pos);
            this.arr[pos] = data;
            this.index++;
        } else {
            this.add(data);
        }
    }

    /**
     * Aendert das Element an der Stelle des Indexes.
     *
     * @param data Objekt welches hinzugefuegt werden soll
     */
    public void set(int pos, T data) {
        this.arr[pos] = data;
    }

    /**
     * Gibt das Element an der Stelle des eingegebenen Index zurueck.
     *
     * @param pos Index
     * @return Element aus der ArrayList
     */
    public T get(int pos) {
        this.proofIndex(pos);
        return this.arr[pos];
    }

    /**
     * Loescht die gesamte ArrayList.
     */
    @SuppressWarnings("unchecked") // wegen Konstruktor von T[]
    public void clear() {
        this.arr = (T[]) new Object[10];
        index = 0;
    }

    /**
     * Gibt die Groesse der ArrayList zurueck.
     *
     * @return Groesse der ArrayList
     */
    public int size() {
        return this.index;
    }

    /**
     * Hilfsmethode die ueberprueft ob in der Index erlaubt ist.
     */
    private void proofIndex(int pos) {
        if (this.index < pos || pos < 0)
            throw new ArrayIndexOutOfBoundsException("index (" + pos + ") ist negativ oder zu groß!");
    }

    /**
     * Hilfsmethode die ueberprueft ob in der ArrayList Platz fuer ein weiteres Element ist.
     */
    private void proofSize() {
        if (this.arr[this.arr.length - 1] != null) this.extendAndCopy();
    }

    /**
     * Hilfsmethode die falls kein Platz mehr fuer ein weiteres Element in der ArrayList verfuegbar ist,
     * um das dopplete erweitert.
     */
    @SuppressWarnings("unchecked") // wegen Konstruktor von T[]
    private void extendAndCopy() {
        T[] temp = this.arr;
        this.arr = (T[]) new Object[temp.length * 2];
        this.index = 0;
        for (T x : temp) {
            this.arr[index] = x;
            this.index++;
        }
    }

    // Test
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(0); // Wert 0 hinten anhaengen
        System.out.println("Groesse der Liste: " + list1.size()); // 1
        for (int i = 1; i < 10; i++) {
            list1.add(0, i); // Wert i ganz vorne einfuegen
        }
        list1.add(10, 2); // Wert 2 hinten anhaengen
        System.out.println("Groesse der Liste: " + list1.size()); // 11
        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i) + " "); // 9 8 7 6 5 4 3 2 1 0 2
        }
        System.out.println();
        list1.clear();
        System.out.println("Groesse der Liste: " + list1.size()); // 0
        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add("Algorithmen");
        String s = list2.get(0);
        System.out.println(s); // Algorithmen
    }
}
