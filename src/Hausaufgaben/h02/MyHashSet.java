package Hausaufgaben.h02;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Diese Klasse ist eine vereinfachte, selbst geschriebenes HashSet<T>.
 *
 * @param <T> Objekttyp welches das HashSet fuellen soll.
 */
public class MyHashSet<T> {

    private ArrayList<T>[] arr;

    /**
     * Konstruktor fuer HashSet
     */
    @SuppressWarnings("unchecked") // wegen ArrayList Konstruktor
    public MyHashSet() {
        this.arr = new ArrayList[10];
        this.fillArray();
    }

    /**
     * Fuegt das uebergebende Element hinzu.
     *
     * @param elem Element was hinzugefuegt werden soll
     * @return Wahrheitswert ob das Element bereits in dem HashSet ist
     */
    public boolean add(T elem) {
        if (this.contains(elem))
            return true;
        this.proofSize();
        this.arr[Math.abs(elem.hashCode() % this.arr.length)].add(elem);
        return false;
    }

    /**
     * Loescht das uebergebende Element.
     *
     * @param elem Element was geloescht werden soll
     * @return Wahrheitswert ob das Element ueberhaupt in dem HashSet ist
     */
    public boolean delete(T elem) {
        if (!this.contains(elem))
            return false;
        this.arr[Math.abs(elem.hashCode() % this.arr.length)].remove(elem);
        return true;
    }

    /**
     * Prueft ob das uebergebende Element in dem HashSet ist.
     *
     * @param elem Element welches gesucht wird
     * @return Wahrheitswert ob das Element in dem HashSet ist
     */
    public boolean contains(T elem) {
        for (ArrayList<T> list : this.arr)
            if (!list.isEmpty())
                if (list.contains(elem))
                    return true;

        return false;
    }

    /**
     * Gibt eine ArrayList zurueck, welche alle Elemente enthaelt.
     *
     * @return Arraylist aus allen Elementen
     */
    public ArrayList<T> getElements() {
        ArrayList<T> res = new ArrayList<>();
        for (ArrayList<T> list : this.arr)
            res.addAll(list);
        return res;
    }

    /**
     * Hilfsmethode, die ueberprueft ob das HashSet erweitert werden muss.
     */
    private void proofSize() {
        int elem = 0;
        for (ArrayList<T> list : this.arr)
            elem += list.size();
        if (elem / this.arr.length >= 2)
            this.doubleSize();
    }

    /**
     * Hilfsmethode, die die Laenge des HashSets verdoppelt.
     */
    @SuppressWarnings("unchecked") // wegen ArrayList Konstruktor
    private void doubleSize() {
        ArrayList<T>[] copy = this.arr;
        this.arr = new ArrayList[this.arr.length * 2];
        this.fillArray();
        for (ArrayList<T> list : copy)
            for (T elem : list)
                this.add(elem);
    }

    /**
     * Hilfsmethode, das leere Array inizialisiert.
     */
    private void fillArray() {
        for (int i = 0; i < this.arr.length; i++)
            this.arr[i] = new ArrayList<T>();
    }

    // Testen
    public static void main(String[] args) {
        MyHashSet<Integer> myHash = new MyHashSet<>();
        for (int i = 0; i < 30; i++) {
            myHash.add(i);
        }
        System.out.println(myHash.contains(5)); // true
        myHash.delete(5);
        System.out.println(myHash.contains(5)); // false
        ArrayList<Integer> el = myHash.getElements();
        System.out.println(el); // Zahlen 0..29 ausser der 5 unsortiert
        Collections.sort(el);
        System.out.println(el); // 0,1,2,3,4,6,7,....,29
    }
}
