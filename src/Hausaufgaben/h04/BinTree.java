package Hausaufgaben.h04;

/**
 * Diese Klasse bildet einen binaren Suchbaum der ints speichert.
 */
public class BinTree {

    private TreeNode root;

    /**
     * Konstruktor
     */
    public BinTree() {
        this.root = null;
    }

    /**
     * sucht einen bestimmten Wert im Baum, gibt null zurueck, wenn der Wert nicht im Baum existiert
     *
     * @param x bestimmter Wert
     * @return bestimmten Wert in Form von einer TreeNode oder null
     */
    private TreeNode getNode(int x) {
        if (this.root == null)
            throw new ArithmeticException("BinTree ist leer!");
        TreeNode tmp = this.root;
        tmp = this.getNodeRekursiv(x, tmp);
        if (tmp.data == x) {
            return tmp;
        } else {
            return null;
        }
    }

    /**
     * Hilfsmethode, die den bestimmten Wert im Baum sucht
     *
     * @param x   bestimmter Wert
     * @param tmp TreeNode von dem die Suche gestartet wird
     * @return bestimmter Wert in Form einer TreeNode
     */
    private TreeNode getNodeRekursiv(int x, TreeNode tmp) {
        if (tmp.data == x) {
            return tmp;
        }
        if (tmp.data > x) {
            if (tmp.left == null) {
                return tmp;
            }
            if (tmp.left.data == x) {
                return tmp.left;
            }
            tmp = tmp.left;
        } else {
            if (tmp.right == null) {
                return tmp;
            }
            if (tmp.right.data == x) {
                return tmp.right;
            }
            tmp = tmp.right;
        }
        return this.getNodeRekursiv(x, tmp);
    }

    /**
     * gibt den Elternknoten eines bestimmten Wertes zurueck, gibt null zurueck falls x die Wurzel ist
     *
     * @param x bestimmter Wert
     * @return Elternknoten in Form von einer TreeNode oder null
     */
    private TreeNode getParentNode(int x) {
        TreeNode tmp = this.root;
        tmp = this.getNodeRekursiv(x, tmp);
        if (tmp.data == x) {
            if (tmp.parent == null)
                return null;
            return tmp.parent;
        }
        if (tmp.left.data == x || tmp.right.data == x)
            return tmp;
        return null;
    }

    /**
     * fuegt einen Wert im Baum ein
     *
     * @param x Wert
     */
    public void insert(int x) {
        if (this.root == null) {
            this.root = new TreeNode(x);
        } else {
            TreeNode tmp = this.root;
            tmp = this.getNodeRekursiv(x, tmp);
            if (tmp.data == x)
                throw new ArithmeticException("Der Wert ist bereits in dem BinTree!");
            if (tmp.data > x) {
                tmp.left = new TreeNode(x);
                tmp.left.parent = tmp;
            } else {
                tmp.right = new TreeNode(x);
                tmp.right.parent = tmp;
            }
        }
    }

    /**
     * loescht den gesamten Baum
     */
    public void clear() {
        this.root = null;
    }

    /**
     * loescht einen bestimmten Wert aus dem Baum
     *
     * @param x bestimmter Wert
     */
    public void remove(int x) { // FALSCH!!
        TreeNode tmp = this.getNode(x);
        if (tmp == null)
            throw new ArithmeticException("Der Wert existiert nicht in dem BinTree!");
        TreeNode node = tmp;
        if(node.left != null && node.right != null) {
            node = node.left;
            node.parent = tmp.parent;
            tmp.right.parent = node;
        } else {
            if (node.left == null) {
                node = node.right;
                node.parent = tmp.parent;
            }
            if (node.right == null) {
                node = node.left;
                node.parent = tmp.parent;
            }
        }
    }

    /**
     * Hilfsmethode, die von dem uebergebenen Knoten so weit nach links geht wie möglich
     *
     * @param tmp Startknoten
     * @return gibt das ganz linke Blatt zurueck
     */
    private TreeNode goLeft(TreeNode tmp) {
        while (tmp.left != null)
            tmp = tmp.left;
        return tmp;
    }

    /**
     * Knotenklasse
     */
    static class TreeNode {

        private final int data;

        private TreeNode parent;
        private TreeNode left;
        private TreeNode right;

        /**
         * Konstruktor
         *
         * @param data int-Wert
         */
        private TreeNode(int data) {
            this.data = data;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

    }

    // Test
    public static void main(String[] args) {
        BinTree tree = new BinTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(50);
        int[] testcases = {30, 35, 50, 20};
        for (int testcase : testcases) {
            TreeNode node = tree.getNode(testcase);
            if (node == null) {
                System.out.println("Knoten " + testcase + " nicht gefunden.");
            } else {
                System.out.println("Knoten " + testcase + " gefunden: " + node.data);
            }
        }
        tree.remove(30);
        System.out.println("Knoten geloescht: 30");
        tree.remove(30);
        System.out.println(tree.getNode(30));
        System.out.println("Elternknoten von 50: " + tree.getParentNode(50).data);// 20
        tree.clear();
        tree.getNode(20);
    }
}
