package Hausaufgaben.h05;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Diese Klasse ist ein vereinfachter Heap.
 */
public class Heap {

    private ArrayList<Integer> heap;

    /**
     * Konstruktor
     */
    public Heap() {
        heap = new ArrayList<>();
    }

    /**
     * gibt zurueck ob der Heap leer ist
     * @return boolean
     */
    public boolean isEmpty() {
        return heap == null || heap.size() < 2;
    }

    /**
     * fuegt ein Element hinzu
     * @param i Element
     */
    public void add(int i) {
        if (this.heap.isEmpty())
            this.heap.add(0);
        if (this.heap.contains(i))
            throw new ArithmeticException(i + " ist bereits im Heap!");
        this.heap.add(i);
        this.upheap(i, this.heap.indexOf(i));
    }

    /**
     * Hilfsmethode, die das eingefuegte Element nach oben sortiert
     * @param i Element
     * @param pos Position
     */
    private void upheap(int i, int pos) {
        if (pos != 1) {
            if (this.heap.get(pos / 2) < i) {
                Collections.swap(heap, pos / 2, pos);
                this.upheap(i, pos / 2);
            }
        }
    }

    /**
     * gibt die Wurzel (groesste Element) zurueck
     * @return groesste Element
     */
    public int getMax() {
        if (this.isEmpty()) {
            throw new ArithmeticException("Heap ist leer!");
        }
        int res = this.heap.get(1);
        this.downheap(1);
        return res;
    }

    /**
     * Hilfsmethode, die den Heap von oben sortiert
     * @param pos
     */
    private void downheap(int pos) {
        pos *= 2;
        if (this.heap.size() <= pos + 1) {
            this.heap.remove(pos / 2);
            return;
        }
        int tmp1 = this.heap.get(pos);
        int tmp2 = this.heap.get(pos + 1);
        if (tmp1 > tmp2) {
            this.heap.set(pos / 2, tmp1);
        }
        if (tmp1 < tmp2) {
            this.heap.set(pos / 2, tmp2);
            pos += 1;
        }
        this.downheap(pos);
    }

    /**
     * gibt den Heap als String zurueck
     * @return String
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int x : this.heap)
            res.append(x).append(",");
        res.delete(1, 3);
        if (res.length() > 2)
            res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    // Testen
    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(1);
        System.out.println(heap);
        heap.add(6);
        System.out.println(heap);
        heap.add(8);
        System.out.println(heap);
        heap.add(18);
        System.out.println(heap);
        heap.add(23);
        System.out.println(heap);
        heap.add(5);
        System.out.println(heap);
        heap.add(17);
        System.out.println(heap);
        heap.add(20);
        System.out.println(heap);
        heap.add(26);
        System.out.println(heap);
        heap.add(21);
        System.out.println(heap);
        heap.add(9);
        System.out.println(heap);
        System.out.println();
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
        System.out.println(heap.getMax());
        System.out.println(heap);
    }
}