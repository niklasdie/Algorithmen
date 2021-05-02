package Uebungen.u05;

/**
 * ein binaerer Suchbaum mit ganzen Zahlen als Datensatz
 */
public class BinarySearchTree {

    private TreeNode root;

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        int ges = 0;
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 50);
            //int x = i;
            System.out.println(x);
            tree.insert(x);
            ges += x;
        }
        for (int i = 0; i < 50; i++) {
            System.out.println(i + ": " + tree.contains(i));
        }
        System.out.println(tree);
        System.out.println("tree.getElementCount(): " + tree.getElementCount());
        System.out.println("tree.getSum(): " + tree.getSum());
        System.out.println("tree.getHight(): " + tree.getHight());
        System.out.println("tree.getLeafCount(): " + tree.getLeafCount());
        System.out.println("tree.hasNodesWithOneChild(): " + tree.hasNodesWithOneChild());
    }

    public String toString() {
        return "[ " + this.rekusivToString(this.root) + " ]";
    }

    private String rekusivToString(TreeNode tmp) {
        if (tmp.right != null && tmp.left != null) {
            return this.rekusivToString(tmp.left) + ", " + tmp.value + ", " + this.rekusivToString(tmp.right);
        }
        if (tmp.left != null) {
            return this.rekusivToString(tmp.left) + ", " + tmp.value;
        }
        if (tmp.right != null) {
            return tmp.value + ", " + this.rekusivToString(tmp.right);
        }
        return "" + tmp.value;
    }

    public int getElementCount() {
        return rekusivGetElementCount(this.root);
    }

    private int rekusivGetElementCount(TreeNode tmp) {
        if (tmp.right != null && tmp.left != null) {
            return this.rekusivGetElementCount(tmp.left) + 1 + this.rekusivGetElementCount(tmp.right);
        }
        if (tmp.left != null) {
            return this.rekusivGetElementCount(tmp.left) + 1;
        }
        if (tmp.right != null) {
            return 1 + this.rekusivGetElementCount(tmp.right);
        }
        return 1;
    }

    public int getSum() {
        return rekusivGetSum(this.root);
    }

    private int rekusivGetSum(TreeNode tmp) {
        if (tmp.right != null && tmp.left != null) {
            return this.rekusivGetSum(tmp.left) + tmp.value +
                    this.rekusivGetSum(tmp.right);
        }
        if (tmp.left != null) {
            return this.rekusivGetSum(tmp.left) + tmp.value;
        }
        if (tmp.right != null) {
            return tmp.value + this.rekusivGetSum(tmp.right);
        }
        return tmp.value;
    }

    public int getHight() {
        int count = this.getElementCount();
        int tmp = 1;
        int res = 0;
        while(count / tmp > 1) {
            tmp = tmp * 2   ;
            res++;
        }
        return res+1;
    }

    public int getLeafCount() {
        return rekusivGetLeafCount(this.root);
    }

    private int rekusivGetLeafCount(TreeNode tmp) {
        if (tmp.right != null && tmp.left != null) {
            return this.rekusivGetLeafCount(tmp.left) + this.rekusivGetLeafCount(tmp.right);
        }
        if (tmp.left != null) {
            return this.rekusivGetLeafCount(tmp.left);
        }
        if (tmp.right != null) {
            return this.rekusivGetLeafCount(tmp.right);
        }
        return 1;
    }

    public boolean hasNodesWithOneChild() {
        return this.rekusivHasNodesWithOneChild(this.root);
    }

    private boolean rekusivHasNodesWithOneChild(TreeNode tmp) {
        if (tmp.right != null && tmp.left != null) {
            return this.rekusivHasNodesWithOneChild(tmp.left) && this.rekusivHasNodesWithOneChild(tmp.right);
        }
        if (tmp.left != null) {
            return true;
        }
        if (tmp.right != null) {
            return true;
        }
        return false;
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
}
