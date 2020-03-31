package myBinaryTree;

public class BST_Test {
    public static void main(String[] args)
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(4);
        bst.add(5);
        bst.add(1);
        bst.add(9);
        bst.add(9);
        bst.add(10);
        bst.add(0);
        bst.add(6);

        //inOrderTraversal -- from smallest to the biggest
        System.out.println("该二叉搜索树的中序遍历结果为：");
        Node<Integer> show = bst.getBsTree();
        show.inOrderTraversal(bst.getBsTree());

        System.out.println();

        //findNode test
        System.out.println("元素 6 是否为该搜索二叉树中的元素 ：" + (bst.findNode(6) ? "yes" : "no"));

        System.out.println();

        //delete test
        System.out.println("删除元素0 ： " + (bst.delete(0) ? "成功删除" : "删除失败"));
        System.out.println("当前二叉树的中序遍历结果为： ");
        show.inOrderTraversal(bst.getBsTree());

        //System.out.println(bst.getBsTree().getNodesValues(bst.getBsTree()));

        bst.printRange(0, 11, bst);
    }
}
