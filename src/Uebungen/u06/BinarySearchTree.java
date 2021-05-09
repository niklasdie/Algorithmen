package Uebungen.u06;

import java.util.ArrayList;

/**
 * ein binaerer Suchbaum mit ganzen Zahlen als Datensatz
 */
public class BinarySearchTree {

    private TreeNode root;

    public BinarySearchTree() {

    }

    public BinarySearchTree(int count, int min, int max) {

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
            if (temp.value == data) {
                return true;
            }
            if (temp.value > data) {
                temp = temp.left;
            } else {
                temp = temp.right;
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
            root = new TreeNode(data);
            return true;
        }

        TreeNode temp = root;
        while (temp.value != data) {
            if (temp.value > data) {
                if (temp.left == null) {
                    temp.left = new TreeNode(data);
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = new TreeNode(data);
                    return true;
                }
                temp = temp.right;
            }
        }
        return false;
    }

    public void printPreorder() {
        StringBuilder stringBuilder = new StringBuilder();
        this.printPreorderRekusiv(this.root, stringBuilder);
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        System.out.println("[ " + stringBuilder + "]");
    }

    private void printPreorderRekusiv(TreeNode node, StringBuilder stringBuilder) {
        stringBuilder.append(node.value).append(", ");
        if (node.left != null)
            this.printPreorderRekusiv(node.left, stringBuilder);
        if (node.right != null)
            this.printPreorderRekusiv(node.right, stringBuilder);
    }

    public ArrayList<Integer> getLeaves() {
        return this.getLeavesRekusiv(this.root, new ArrayList<>());
    }

    private ArrayList<Integer> getLeavesRekusiv(TreeNode node, ArrayList<Integer> list) {
        if (node.left != null)
            this.getLeavesRekusiv(node.left, list);
        if (node.right != null)
            this.getLeavesRekusiv(node.right, list);
        if (node.left == null && node.right == null)
            list.add(node.value);
        return list;
    }

    public int getMaxPathSum() {
        return this.getMaxPathSumRekusiv(this.root);
    }

    private int getMaxPathSumRekusiv(TreeNode node) {
        int left = 0, right = 0;
        if (node.left != null) {
            left = this.getMaxPathSumRekusiv(node.left);
        }
        if (node.right != null) {
            right = this.getMaxPathSumRekusiv(node.right);
        }
        if (node.left == null && node.right == null)
            return node.value;
        if (right < left)
            return left + node.value;
        return right + node.value;
    }

    public ArrayList<Integer> getElementsInLevel(int level) {
        return this.getElementsInLevelRekusiv(this.root, new ArrayList<>(), 0, level);
    }

    private ArrayList<Integer> getElementsInLevelRekusiv(TreeNode node, ArrayList<Integer> list,
                                                         int current, int level) {
        if (current == level)
            list.add(node.value);
        if (node.left != null)
            this.getElementsInLevelRekusiv(node.left, list, current + 1, level);
        if (node.right != null)
            this.getElementsInLevelRekusiv(node.right, list, current + 1, level);
        return list;
    }

    public boolean isComplete() {
        int lowestLevel = this.lowestLevel(this.root, 0, 0);
        return (this.getElementsInLevel(lowestLevel).size() - 1 / Math.pow(2, lowestLevel)) == 1;
    }

    private int lowestLevel(TreeNode node, int current, int lowest) {
        if (current > lowest)
            lowest = current;
        if (node.left != null)
            this.lowestLevel(node.left,current + 1, lowest);
        if (node.right != null)
            this.lowestLevel(node.right,current + 1, lowest);
        return lowest;
    }

    public boolean isAVL() {
        return false;
    }

    /**
     * die Knotenklasse als statische innere Klasse
     */
    public static class TreeNode {

        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        BinarySearchTree tree2 = new BinarySearchTree();
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 50);
            System.out.println(x);
            tree.insert(x);
        }

        for (int i = 0; i < 50; i++) {
            System.out.println(i + ": " + tree.contains(i));
        }

        for (int i = 9; i > 0; i--) {       // fuer getMaxPathSum()
            tree2.insert(i);
        }
        tree2.insert(10);

        BinarySearchTree tree3 = new BinarySearchTree();
        tree3.insert(2);
        tree3.insert(1);
        tree3.insert(3);

        System.out.print("\ntree.printPreorder(): ");
        tree.printPreorder();
        System.out.print("tree.getLeaves(): ");
        ArrayList<Integer> list = tree.getLeaves();
        for (Integer x : list)
            System.out.print(x + " ");
        System.out.print("\ntree2.printPreorder(): ");
        tree2.printPreorder();
        System.out.println("tree2.getMaxPathSum(): " + tree2.getMaxPathSum());
        System.out.println("tree.getElementsInLevel(4): " + tree.getElementsInLevel(1));
        System.out.println(tree3.isComplete());
    }
}
