package Hausaufgaben.h11;

/**
 * Ein binaerer Suchbaum mit ganzen Zahlen als Datensatz:
 * Vorlage fuer die A1 von algo-pr05.
 * Als Operationen stehen `contains' und `insert' zur Verfuegung
 */
public class BinarySearchTree {

    /**
     * Baumwurzel
     */
    protected TreeNode root;

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 50);
            System.out.println(x);
            tree.insert(x);
        }
        for (int i = 0; i < 50; i++) {
            System.out.println(i + ": " + tree.contains(i));
        }
    }

    /**
     * Herausfinden, ob ein gewisser Datensatz schon im binaeren Suchbaum enthalten ist.
     *
     * @param data zu suchender Datensatz
     * @return true: Datensatz ist vorhanden; false: Datensatz ist nicht vorhanden.
     */
    public boolean contains(int data) {
        TreeNode temp = root;
        while (temp != null) {
            if (temp.getValue() == data) {
                return true;
            }
            if (temp.getValue() > data) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return false;
    }

    /**
     * Einen neuen Datensatz in den binaeren Suchbaum einfuegen.
     *
     * @param data einzufuegender Datensatz
     * @return true: Datensatz wurde eingefuegt; false: Datensatz war schon vorhanden.
     */
    public boolean insert(int data) {
        if (root == null) {
            root = new TreeNode(data, root);
            return true;
        }

        TreeNode temp = root;
        while (temp.getValue() != data) {
            if (temp.getValue() > data) {
                temp.averageLeft += data;                                                               // hinzugefuegt
                temp.subTreeSize++;                                                                     // hinzugefuegt
                if (temp.getLeft() == null) {
                    temp.setLeft(new TreeNode(data, temp));
                    return true;
                }
                temp = temp.getLeft();
            } else {
                temp.averageRight += data;                                                              // hinzugefuegt
                temp.subTreeSize++;                                                                     // hinzugefuegt
                if (temp.getRight() == null) {
                    temp.setRight(new TreeNode(data, temp));
                    return true;
                }
                temp = temp.getRight();
            }
        }
        return false;
    }

    /**
     * Die Knotenklasse als statische innere Klasse.
     */
    public static class TreeNode {
        protected int value;                                                                            // bearbeitet
        protected int averageRight;                                                                     // hinzugefuegt
        protected int averageLeft;                                                                      // hinzugefuegt
        protected int subTreeSize;                                                                      // hinzugefuegt
        protected TreeNode parent;                                                                      // hinzugefuegt
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value, TreeNode parent) {
            this.value = value;
            this.averageRight = 0;                                                                      // hinzugefuegt
            this.averageLeft = 0;                                                                       // hinzugefuegt
            this.subTreeSize = 1;                                                                       // hinzugefuegt
            this.parent = parent;                                                                       // hinzugefuegt
        }

        public String toString() {
            return this.value + " ";
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public double getAverage() {
            return this.averageLeft + this.value + this.averageRight;
        }        // hinzugefuegt

        public int getSubTreeSize() {
            return this.subTreeSize;
        }                                       // hinzugefuegt

        public TreeNode getLeft() {
            return this.left;
        }

        public void setLeft(TreeNode node) {
            this.left = node;
            node.parent = this;
        }

        public TreeNode getRight() {
            return this.right;
        }

        public void setRight(TreeNode node) {
            this.right = node;
            node.parent = this;
        }
    }
}

