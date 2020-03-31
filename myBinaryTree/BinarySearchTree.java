package myBinaryTree;

import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchTree<T> {
    //initialize a bsTree -- 相当于一个根节点
    private Node<T> bsTree;

    //无参数， 构建空的根节点
    public BinarySearchTree()
    {
        bsTree = null;
    }
    //有参数，将根节点的value设为传入的value
    public BinarySearchTree(T value)
    {
        bsTree = new Node<T>(null, null, value);
    }

    //add a value to the bsTree
    public void add(T value)
    {
        //如果根节点为空，构造根节点
        if (bsTree == null)
            bsTree = new Node<T>(null, null, value);

        else{
            //预储存bsTree
            Node<T> temp = bsTree;
            Node<T> add = new Node<>(null, null, value);
            add(add);
            bsTree = temp;
        }
    }
    private void add(Node<T> node)
    {
        //此处bsTree作为迭代节点
        //(强行比较，最为致命)

        //when the value of the node is lower than the value of the iterate node(bsTree)
        if ((int)bsTree.getValue() > (int)node.getValue())
        {
            //when the iterate node has no left node, set the node as the left node of the iterate node
            if (bsTree.getLeftNode() == null) {
                bsTree.setLeftNode(node);
                return;
            }
            //else, continue to recurse
            else{
                bsTree = bsTree.getLeftNode();
                add(node);
            }
        }
        //when the value of the node is higher than the value of the iterate node
        else
        {
            //when the iterate node has no right node, set the node as the right node of the iterate node
            if (bsTree.getRightNode() == null){
                bsTree.setRightNode(node);
                return;
            }
            //else, continue to recurse
            else{
                bsTree = bsTree.getRightNode();
                add(node);
            }
        }
    }

    //get the bsTree
    public Node<T> getBsTree()
    {
        return bsTree;
    }

   //find a specific node if exist
    public boolean findNode(T value)
    {
        return bsTree.findNode(value);
    }


    /*
     *  delete a specific node
     *  by using a specific value
     */

    public boolean delete(T value)
    {
        //if the node doesnt exist in the binary search tree, return false
        if (!bsTree.findNode(value))
            return false;
        //else, add the value of the node to the LinkedList of the binary search tree
        else {
            LinkedList<T> newList = bsTree.getNodesValues(bsTree);
            newList.remove(value);
            //System.out.println(newList);
            bsTree = rebuild(newList);
            return true;
        }
    }
    /*
     * get values of all nodes of the binary search tree
     * to rebuild the tree
     *
     * 方法getNodesValues为中序遍历添加集合元素
     * 所以获取的集合元素为从小到大排序
     */
    private LinkedList<T> getNodesValues()
    {
        return bsTree.getNodesValues(bsTree);
    }
    //using a Collection to rebuild a bst
    private Node<T> rebuild(LinkedList<T> nodesValues)
    {
        BinarySearchTree<T> bst = new BinarySearchTree<T>();
        while(!nodesValues.isEmpty())
            bst.add(nodesValues.pop());
        return bst.getBsTree();
    }
    /*private Node<T> rebuild()
    {
        LinkedList<T> list = this.getNodesValues();
        BinarySearchTree<T> bst = new BinarySearchTree<>();
        Iterator<T> it = list.listIterator();
        while (it.hasNext())
            bst.add(it.next());

        return bst.getBsTree();
    }*/

    //get all nodes between two values
    public void printRange(T first, T second, BinarySearchTree<T> BST)
    {
        //get the value collection of the bst
        LinkedList<T> list = BST.getNodesValues();

        //set the value of the bound value
        T small, big;
        if ((Integer) first <= (Integer)second){
            small = first;
            big = second;
        }
        else {
            small = second;
            big = first;
        }
        //the elements in the LinkedList has been sorted -- from smallest to the greatest
        if ( (Integer) small > (Integer) list.getLast() || (Integer) big < (Integer) list.getFirst()){
            System.out.println("不存在节点位于该范围内");
            return;
        }
        else {
            Iterator<T> iterator = list.listIterator();
            while (iterator.hasNext())
            {
                T temp = iterator.next();
                if ((Integer) temp >= (Integer) small && (Integer) temp <= (Integer) big)
                    System.out.print(temp + " ");
            }
            System.out.println();
        }
    }
}
