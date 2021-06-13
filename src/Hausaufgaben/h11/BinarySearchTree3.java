package Hausaufgaben.h11;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinarySearchTree3 extends BinarySearchTree2 {

    // Testen
    public static void main(String[] args) {
        BinarySearchTree3 test = new BinarySearchTree3();
        baumBauen(test, 5, 7, 4, 2, 8, 1, 3);
        test.show();
        System.out.println(test.getAverageOfSubtree(3));
    }

    /**
     * Gibt den Mittelwert des Unterbaumes des angegebenden Knotens zurueck.
     *
     * @param val Wurzel des Unterbaums
     * @return Mittelwert des Unterbaums
     */
    public double getAverageOfSubtree(int val) {
        TreeNode valNode = this.getNode(val);
        if (valNode == null)
            throw new NoSuchElementException("Dieses Knoten ist nicht im Baum enthalten!");
        ArrayList<Integer> res = new ArrayList<>();
        rekursivGetAverageOfSubtree(res, valNode);
        double average = 0;
        for (Integer x : res)
            average += x;
        System.out.println("Sum: " + average);
        average /= res.size();
        return average;
    }

    /**
     * Hilfsmethode, die alle Knotenwerte in einer ArrayList sammelt.
     *
     * @param arrayList Knotenwerte
     * @param valNode   aktueller Knoten
     */
    private void rekursivGetAverageOfSubtree(ArrayList<Integer> arrayList, TreeNode valNode) {
        if (valNode.getLeft() != null)
            rekursivGetAverageOfSubtree(arrayList, valNode.getLeft());
        arrayList.add(valNode.getValue());
        if (valNode.getRight() != null)
            rekursivGetAverageOfSubtree(arrayList, valNode.getRight());
    }
}
