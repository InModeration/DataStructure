package myBinaryTree;

public class BinaryTreeTest {

    public static void main(String[] args) throws Exception
    {
        //creat a binary tree
        Node<Integer> node_1 = new Node<>(null, null,0);
        Node<Integer> node_2 = new Node<>(node_1, null, 1);
        Node<Integer> node_3 = new Node<>(null, null, 10);
        Node<Integer> node_4 = new Node<>(null, node_3, 9);
        Node<Integer> node_5 = new Node<>(null, null, 6);
        Node<Integer> node_6 = new Node<>(node_5, node_4, 9);
        Node<Integer> node_7 = new Node<>(node_2, node_6, 5);



        //traversal test
        System.out.println("中序遍历");
        node_3.inOrderTraversal(node_7);
        System.out.println("前序遍历");
        node_2.preOrderTraversal(node_7);
        System.out.println("后序遍历");
        node_1.postOrderTraversal(node_7);
        System.out.println("层次遍历");
        node_1.levelOrderTraversal(node_7);


        //getDepth test
        System.out.println("该树的深度为 ： ");
        System.out.println(node_1.getDepth(node_7));

        //getSize test
        System.out.println("该树共有节点数为：");
        System.out.println(node_2.getSize(node_7));

        //findNode test
        System.out.println("该数中是否存在元素1、4、5、9、10");
        System.out.println(node_7.findNode(0));
        System.out.println(node_7.findNode(1));
        //System.out.println(node_7.findNode(4));
        System.out.println(node_7.findNode(5));
        System.out.println(node_7.findNode(6));
        System.out.println(node_7.findNode(9));
        System.out.println(node_7.findNode(10));

        System.out.println("该二叉树的所有节点的值集合为：");
        System.out.println(node_7.getNodesValues(node_7));
        node_7.printNode();
    }
}
