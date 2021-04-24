package Hausaufgaben.h04;

public class BinTree {

    private TreeNode root;

    public BinTree() {
        this.root = null;
    }

    private TreeNode getNode(int x) {
        TreeNode tmp = this.root;
        tmp = this.getNodeRekusiv(x, tmp);
        if (tmp.data == x) {
            return tmp;
        } else {
            return null;
        }
    }

    private TreeNode getNodeRekusiv(int x, TreeNode tmp) {
        if (this.root == null)
            throw new ArithmeticException("BinTree ist leer!");
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
        return this.getNodeRekusiv(x, tmp);
    }

    private TreeNode getParentNode(int x) {
        TreeNode tmp = this.root;
        tmp = this.getNodeRekusiv(x, tmp);
        if (tmp.data == x) {
            if (tmp.parent == null)
                return null;
            return tmp.parent;
        }
        if (tmp.left.data == x || tmp.right.data == x)
            return tmp;
        return null;
    }

    public void insert(int x) {
        if (this.root == null) {
            this.root = new TreeNode(x);
        } else {
            TreeNode tmp = this.root;
            tmp = this.getNodeRekusiv(x, tmp);
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

    public void clear() {
        this.root = null;
    }

    public void remove(int x) {
        TreeNode tmp = this.root;
        tmp = this.getNodeRekusiv(x, tmp);
        if (tmp.data != x)
            throw new ArithmeticException("Der Wert existiert nicht in dem BinTree!");
        TreeNode node = tmp;
        tmp = this.goLeft(tmp);
        if (tmp == node)
            if (tmp.right != null)
                tmp = tmp.right;
        tmp.parent = node.parent;
        tmp.left = node.left;
        tmp.right = node.right;
    }

    private TreeNode goLeft(TreeNode tmp) {
        while (tmp.left != null)
            tmp = tmp.left;
        return tmp;
    }

    static class TreeNode {

        private final int data;
        private TreeNode parent;
        private TreeNode left;
        private TreeNode right;

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
        System.out.println("Elternknoten von 50: " + tree.getParentNode(50).data);// 20
        tree.clear();
        tree.getNode(20);
    }
}
