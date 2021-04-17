package Hausaufgaben.h03;

public interface IList {

    /**
     * fuegt einen Wert an einer bestimmten Position ein
     * @param n Position
     * @param data Wert
     */
    void insertAt(int n, int data);

    /**
     * loescht einen Wert an einer bestimmten Position
     * @param n Position
     */
    void removeAt(int n);

    /**
     * liest einen Wert an einer bestimmten Position aus
     * @param n Position
     * @return Wert
     */
    int getAt(int n);

    /**
     * liest eine Position eines bestimmten Wertes aus
     * @param data Wert
     * @return Position
     */
    int search(int data);

    /**
     * loescht die gesamte Liste
     */
    void clear();

    /**
     * gibt die Groesse der Liste zurueck
     * @return Groesse
     */
    int getCount();
}
