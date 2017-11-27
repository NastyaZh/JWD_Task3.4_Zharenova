package task3.pkg4;

import implementationList.BinaryTree;

public class Task34 {

    public static void main(String[] args) {
        BinaryTree<Integer,String> b = new BinaryTree<>();
        b.add(5, "a");
        b.add(4, "s");
        b.add(6, "m");
        b.add(3, "f");
        b.add(2, "l");
        
        b.preOrder();
        b.inOrder();
        b.postOrder();
        
    }
    
}
