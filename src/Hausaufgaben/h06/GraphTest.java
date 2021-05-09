package Hausaufgaben.h06;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Klasse mit vorgefertigten Testfunktionen fuer die Ableitung {@link MyGraph} von {@link Graph}.<br>
 * Vorgabe zur Loesung zu algo-h06 im SS 2021.
  */
public class GraphTest {

  /** Kantenliste fuer g1 */
  private static int[] vlist = { 6, 10,       // 6 Knoten, 10 Kanten
                                 1, 5, 1, 4,  // alle Knotenpaare fuer Kanten mit 1 als Anfangsknoten
                                 2, 3, 2, 6,  // ...
                                 3, 4, 3, 5,  // ...
                                 4, 5, 4, 6,  // ...
                                 5, 6,        // ...
                                 6, 4 };      // alle Knotenpaare fuer Kanten mit 6 als Anfangsknoten

  /** erster Testgraph, auf den alle Methoden angewandt werden */
  private static MyGraph g1 = new MyGraph(vlist);  // Graph aus Kantenliste
  // g1 kann man sich so vorstellen:
  //
  //        +---------3<----2
  //        |         |     |
  //        |   1     |     |
  //        |   |\    |     |
  //        |   | \   |     |
  //        |   |  \  |     |
  //        |   |   \ |     |
  //        |   v    vv     v
  //        +-->5<----4<--->6<-+
  //            |              |
  //            +--------------+

  /** zweiter Testgraph, der nur oberflaechlich getestet wird */
  private static MyGraph g2 = new MyGraph(5, 20);  // Graph mit 5 Knoten und 20 Zufallskanten

  /** die Grundausstattung der Klasse testen */
  private static void minimaltest() {
    System.out.println();
    System.out.println("Test der Grundausstattung:");
    System.out.println("g1 = " + g1);

    System.out.println("g2 = " + g2);
  }

  /**
   * Die 6 zusaetzlich zu implementierenden Methoden der Klasse testen.
   */
  private static void zusatztest() {
    System.out.println();
    System.out.println("Test der zu implementierenden Methoden:");
    System.out.println("Kantenliste von g1 = " + g1.getEdgeList());
    System.out.println("Kantenliste von g2 = " + g2.getEdgeList());
    System.out.println("Knotenliste von g1 = " + g1.getVertexList());
    System.out.println("Knotenliste von g2 = " + g2.getVertexList());

    int[][] adjazenzmatrix = g1.getAdjacencyMatrix();
    System.out.println("Adjazenzmatrix von g1 = " + Arrays.deepToString(adjazenzmatrix));
    int start = 1;
    System.out.println("Startknoten fuer Breitensuche = " + start);
    ArrayList<Integer> liste = g1.bfs(start);
    System.out.println("Graph g1: Liste der durchlaufenen Knoten = " + liste);
    System.out.println("Startknoten fuer Tiefensuche = " + start);
    liste = g1.dfs(start);
    System.out.println("Graph g1: Liste der durchlaufenen Knoten = " + liste);

    // Wenn man bei Knoten 1 im Testgraphen g1 startet, sind
    // offensichtlich nur die Knoten 4, 5 und 6 erreichbar, was
    // Breiten- und Tiefensuche bestaetigen. Also sind von 1 aus
    // betrachtet die Knoten 2 und 3 unerreichbar. Das laesst sich
    // auch gut am Bild des Graphen erkennen, da von 2 und 3 nur
    // Kanten ausgehen und nicht umgekehrt.
    System.out.println("Startknoten = " + start);
    liste = g1.getUnreachableVertices(start);
    System.out.println("Graph g1: Liste der unerreichbaren Knoten = " + liste);
  }

  /**
   * Die Ableitung {@link MyGraph} von {@link Graph} komplett testen.
   *
   * @param  args  was dem Programmaufruf uebergeben wurde
   */
  public static void main(String[] args) {
    minimaltest();
    zusatztest();
  }
}

