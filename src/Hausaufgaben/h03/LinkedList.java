package Hausaufgaben.h03;

/**
 * Diese Klasse ist eine einfache doppelt verkettete Liste, welche das Interface IList implementiert.
 */
public class LinkedList implements IList {

    private Node first;
    private Node last;
    private int size;

    /**
     * Konstruktur
     */
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * fuegt einen Wert an einer bestimmten Position ein
     *
     * @param n    Position
     * @param data Wert
     */
    @Override
    public void insertAt(int n, int data) {
        if (this.first == null) {
            this.first = new Node(data);
            this.last = this.first;
        } else {
            Node tmp = this.goTo(n - 1);
            Node node = new Node(data);
            if (tmp == this.last) {
                this.last.next = node;
                this.last = node;
            } else {
                tmp.next.prev = node;
                node.next = tmp.next;
                tmp.next = node;
                node.prev = tmp;
            }
        }
        this.size++;
    }

    /**
     * loescht einen Wert an einer bestimmten Position
     *
     * @param n Position
     */
    @Override
    public void removeAt(int n) {
        if (this.first == null)
            throw new ArrayIndexOutOfBoundsException("LinkedList ist leer");
        if (n == 0) {
            this.first.next.prev = null;
            this.first = null;
        } else {
            Node tmp = this.goTo(n - 1);
            if (tmp == this.last)
                throw new ArrayIndexOutOfBoundsException("Fehlerhafter Index");
            if (tmp.next != this.last) {
                tmp.next.next.prev = tmp;
                tmp.next = tmp.next.next;
            } else {
                tmp.next = null;
                this.last = tmp;
            }

            this.size--;
        }
    }

    /**
     * liest einen Wert an einer bestimmten Position aus
     *
     * @param n Position
     * @return Wert
     */
    @Override
    public int getAt(int n) {
        return this.goTo(n).data;
    }

    /**
     * liest eine Position eines bestimmten Wertes aus
     *
     * @param data Wert
     * @return Position
     */
    @Override
    public int search(int data) {
        if (this.first == null)
            throw new ArrayIndexOutOfBoundsException("LinkedList ist leer");
        if (this.first.data == data)
            return 0;
        Node tmp = this.first;
        int count = 0;
        while (tmp.next != null) {
            tmp = tmp.next;
            count++;
            if (tmp.data == data)
                return count;
        }
        return -1;
    }

    /**
     * loescht die gesamte Liste
     */
    @Override
    public void clear() {
        this.first = null;
        this.size = 0;
    }

    /**
     * gibt die Groesse der Liste zurueck
     *
     * @return Groesse
     */
    @Override
    public int getCount() {
        return this.size;
    }

    /**
     * Hilfsmethode die zu einer bestimmten Position geht und gibt diese Node zurueck
     *
     * @param n Position
     * @return Node
     */
    private Node goTo(int n) {
        if (n + 1 < 0)
            throw new ArrayIndexOutOfBoundsException("Fehlerhafter Index");
        if (n + 1 == 0)
            return this.first;
        Node tmp = this.first;
        if (tmp == null)
            throw new ArrayIndexOutOfBoundsException("LinkedList ist leer");
        for (int i = 0; i < n; i++) {
            if (tmp.next == null) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return tmp;
    }

    /**
     * Hilfsmethode, die die gesamte Liste als String zurueck gibt
     *
     * @return Liste als String
     */
    public String toString() {
        StringBuilder res = new StringBuilder("[ ");
        for (int i = 0; i < size; i++) {
            res.append(this.getAt(i)).append(" ");
        }
        res.append("]");
        return res.toString();
    }

    /**
     * Hilfsklasse, die einzelne Elemente der LinkedList darstellt
     */
    private static class Node {

        private Node next;
        private Node prev;
        private final int data;

        private Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // Testen
    public static void testList() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 20; i++) {
            list.insertAt(i, i + 1);
        }
        System.out.println("list.insertAt(i, i + 1) fuer: 0 =< i < 20");
        System.out.println(list);
        System.out.println("list.getAt(4) = " + list.getAt(4));
        System.out.println("list.removeAt(4)");
        list.removeAt(4);
        System.out.println("list.getAt(4) = " + list.getAt(4));
        System.out.println(list);
        System.out.println("list.search(15) = " + list.search(15));
        System.out.println("list.getCount() = " + list.getCount());
        System.out.println("list.clear()");
        list.clear();
        System.out.println("list.getCount() = " + list.getCount());
    }

    public static void main(String[] args) {
        testList();
    }
}
