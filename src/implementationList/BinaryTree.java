package implementationList;

public class BinaryTree <E1 extends Comparable<E1>, E2> {
    private Node<E1, E2> root = null;

    private void treatment(E2 value) {
        System.out.println(value.toString());
    }

    
    static class Node<E1, E2> {
        E1 key;
        E2 value;
        Node<E1, E2> left;
        Node<E1, E2> right;
       
        Node(E1 key, E2 value) {
                this.key = key;
                this.value = value;
        }
    }
    
    public void add(E1 k, E2 v) {
        Node<E1, E2> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp == 0) {
                x.value = v;
                return;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        Node<E1, E2> newNode = new Node<E1, E2>(k, v);
        if (y == null) {
            root = newNode;
        } else {
            if (k.compareTo(y.key) < 0) {
                y.left = newNode;
            } else {
                y.right = newNode;
            }
        }
    }
    
    public void preOrder() {
        recPreOrder(root);
    }
    public void inOrder() {
        recInOrder(root);
    }
    public void postOrder() {
        recPostOrder(root);
    }
    
    void recPreOrder(Node<E1, E2> node){
        treatment(node.value);
        if (node.left != null) {
            recPreOrder(node.left);
        }
        if (node.right!=null) {
            recPreOrder(node.right);
        }
    }
    void recInOrder(Node<E1, E2> node){
        if (node.left!=null) {
            recInOrder(node.left);
        }
        treatment(node.value);
        if (node.right!=null) {
            recInOrder(node.right);
        }
    }
    void recPostOrder(Node<E1, E2> node){
        if (node.left!=null) {
            recPostOrder(node.left);
        }
        if (node.right!=null) {
            recPostOrder(node.right);
        }
        treatment(node.value);
    }
}
