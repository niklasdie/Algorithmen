package Hausaufgaben.h11;

import java.util.NoSuchElementException;

public class BinarySearchTree3 extends BinarySearchTree2 {

    // Testen
    public static void main(String[] args) {
        BinarySearchTree3 test = new BinarySearchTree3();
        baumBauen(test, 8, 5, 10, 2, 3, 4, 9, 13, 11, 1, 6);
//                    8
//                 /    \
//                5      10
//              /   \   /   \
//             2    6   9    13
//            / \           /
//           1   3         11
//                \
//                 4
        test.show();
        System.out.println("\nTesten");
        System.out.println("getAverageOfSubtree(2) (sollte 2,5): " + test.getAverageOfSubtree(2));
        System.out.println("getAverageOfSubtree(5) (sollte 3,5): " + test.getAverageOfSubtree(5));
        System.out.println("getAverageOfSubtree(10) (sollte 10,75): " + test.getAverageOfSubtree(10));
        System.out.println("getAverageOfSubtree(8) (sollte 6,545454...): " + test.getAverageOfSubtree(8));
        System.out.println("test.remove(2)");
        test.remove(2);
        System.out.println("getAverageOfSubtree(5) (sollte 3,16...): " + test.getAverageOfSubtree(5));
        System.out.println("test.remove(2)");
        try {
            test.remove(2);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException");
            System.out.println("getAverageOfSubtree(5) (sollte 3,16...): " + test.getAverageOfSubtree(5));
        }
    }

    /**
     * Gibt den Mittelwert des Unterbaumes vom angegebenden Knoten zurueck (unabhaengig von der Laufzeit).
     *
     * @param val Wurzel des Unterbaums
     * @return Mittelwert des Unterbaums
     */
    public double getAverageOfSubtree(int val) {
        TreeNode valNode = this.getNode(val);
        if (valNode == null)
            throw new NoSuchElementException("Dieses Knoten ist nicht im Baum enthalten!");
        return valNode.getAverage() / valNode.getSubTreeSize();
    }
}
