package myBinaryTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
 *  定义二叉树中的节点类
 */
public class Node<T> {
    //use a value to store the value of a node
    private T value;
    //define left and right nodes of a node
    private Node<T> leftNode;
    private Node<T> rightNode;
    //define the parent node of this node
    private Node<T> parentNode = null;
    //use a LinkedList to store the nodes' values
    private static LinkedList list = new LinkedList();
    //set a name
    private String name;

    //节点的构造器，传入左子节点，右子节点以及节点储存的值
    public Node(Node<T> leftNode, Node<T> rightNode, T value)
    {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.value = value;

        //to set this node to the parent of its childNode
        //and if this node is a leaf, jump these operations
        if (leftNode != null)
          this.leftNode.parentNode = this;
        if (rightNode != null)
          this.rightNode.parentNode = this;
    }
    //@Overload
    public Node(String name, Node<T> leftNode, Node<T> rightNode, T value)
    {
        this.name = name;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.value = value;

        //to set this node to the parent of its childNode
        //and if this node is a leaf, jump these operations
        if (leftNode != null)
            this.leftNode.parentNode = this;
        if (rightNode != null)
            this.rightNode.parentNode = this;
    }

    //get the leftNode
    public Node<T> getLeftNode()
    {
        return leftNode;
    }

    //get the rightNode
    public Node<T> getRightNode()
    {
        return rightNode;
    }

    //get the value stored
    public T getValue()
    {
        return value;
    }

    //set the leftNode
    public void setLeftNode(Node<T> temp)
    {
        this.leftNode = temp;
    }

    //set the rightNode
    public void setRightNode(Node<T> temp)
    {
        this.rightNode = temp;
    }

    //set the value of the node
    public void setValue(T value)
    {
        this.value = value;
    }

    //to judge the node if a leaf node , and if the node has child node
    public boolean isLeaf()
    {
        //if the node has left child or right child, its not a leaf node
        if (this.leftNode != null || this.rightNode != null)
            return false;
        else    return true;
    }
    public Node<T> getParentNode()
    {
        return parentNode;
    }

    //print information of a node
    public void printNode() throws Exception {
        if (this == null) {
            throw new Exception("null node");
        }

        if (this.isLeaf())
            System.out.print("该节点是叶节点, ");
        else System.out.print("该节点不是叶节点, " + ((this.getLeftNode() == null) ? "无左节点, " : "有左节点, ") +
                ((this.getRightNode() == null) ? "无右节点, " : "有右节点, "));
        System.out.println("该节点的值为 " + this.getValue());
    }

    /*
     *  traversal( 遍历 ）
     */
    //inOrder -- left, root, right
    public void inOrderTraversal(Node<T> root)
    {
        if (root != null)
        {
            inOrderTraversal(root.getLeftNode());
            System.out.println(root.getValue());
            inOrderTraversal(root.getRightNode());
        }
    }
    //preOrder -- root, left, right
    public void preOrderTraversal(Node<T> root)
    {
        if (root != null)
        {
            System.out.println(root.getValue());
            preOrderTraversal(root.getLeftNode());
            preOrderTraversal(root.getRightNode());
        }
    }
    //postOrder -- left, right, root
    public void postOrderTraversal(Node<T> root)
    {
        if (root != null)
        {
            postOrderTraversal(root.getLeftNode());
            postOrderTraversal(root.getRightNode());
            System.out.println(root.getValue());
        }
    }
    //levelOrder -- root, first level, second level ··· n level
    public void levelOrderTraversal(Node<T> root)
    {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        subLevelOrderTraversal(root, queue);
        while (!queue.isEmpty())
            System.out.println(queue.remove().getValue());
    }
    private void subLevelOrderTraversal(Node<T> root, Queue<Node<T>> queue)
    {
        if (root == null)   return;
        else
        {
            if (root.getLeftNode() != null)
                queue.add(root.getLeftNode());
            if (root.getRightNode() != null)
                queue.add(root.getRightNode());

            if (root.getLeftNode() != null && root.getRightNode() != null){
                subLevelOrderTraversal(root.getLeftNode(), queue);
                subLevelOrderTraversal(root.getRightNode(), queue);
            }
            else if (root.getLeftNode() != null && root.getRightNode() == null)
                subLevelOrderTraversal(root.getLeftNode(), queue);
            else if (root.getLeftNode() == null && root.getRightNode() != null)
                subLevelOrderTraversal(root.getRightNode(), queue);
            else return;
        }
    }


    //traversal to get values -- using inOrder
    public LinkedList<T> getNodesValues(Node<T> root)
    {
        subRun(root);
        return Node.list;
    }
    public void subRun(Node<T> root)
    {
        /*if (root != null);
        {
            subRun(root.getLeftNode());
            Node.list.add(this.getValue());
            subRun(root.getRightNode());
        }*/
        if (root == null)
            return;

        if (root.isLeaf())
            Node.list.add(root.getValue());
        else {
            if (root.getLeftNode() != null && root.getRightNode() != null)
            {
                subRun(root.getLeftNode());
                Node.list.add(root.getValue());
                subRun(root.getRightNode());
            }
            else if (root.getRightNode() != null && root.getLeftNode() == null)
            {
                Node.list.add(root.getValue());
                subRun(root.getRightNode());
            }
            else {
                subRun(root.getLeftNode());
                Node.list.add(root.getValue());
            }
        }
    }

    /*
     *  ***********
     *  两个递归方法
     *  ***********
     */
    // get the depth of the tree -- start at the root node
    public int getDepth(Node<T> root)
    {
        //when the node is null, it means the node doesn't exist, so it doesn't deserve a level
        //return 0 to upper
        if (root == null)
            return 0;

        else
        {
            //when the node is not null, it can define a level
            //for the same level(all the brother nodes), they may have different subtrees
            //so return the biggest depth between left and right

            int i = getDepth(root.getLeftNode());
            //左右同时遍历
            int j = getDepth(root.getRightNode());
            return i > j ? (i + 1) : (j + 1);
        }
    }

    //get the size of the tree -- start at the root node
    public int getSize(Node<T> root)
    {
        //when the node is null, it means the node doesn't exist
        //so return 0 to the upper node
        if(root == null)
            return 0;
        else
        {
            //if the node isn't null
            //it needs all the nodes of its subtrees  -- i + j
            //and +1 means its own number

            int i = getSize(root.getLeftNode());
            //左右同时遍历
            int j = getSize(root.getRightNode());
            return i + j + 1;
        }
        // so at the last step of the recursion, it returns (1 + all the nodes of the root's sub nodes)
    }

    //get the number of its sub nodes (1 or 2)
    public int getChildNum()
    {
        if (this.getLeftNode() == null && this.getRightNode() == null)
            return 0;
        else if (this.getLeftNode() != null && this.getRightNode() != null)
            return 2;
        else
            return 1;
    }

    //find the specific node that has the specific value
    public boolean findNode(T value)
    {
        return this.subFindNode(value);
    }
    private boolean subFindNode(T value)
    {
        //if the node is a leaf node, judge if its value is equal to the value
        if (this.isLeaf()){
            if (this.value == value)
                return true;
            else return false;
        }
        //else, continue to recurse
        else {
            //true condition
            if (this.getValue() == value)
                return true;
            else {
                if (this.getLeftNode() != null && this.getRightNode() != null) {
                    Node<T> left = this.getLeftNode();
                    Node<T> right = this.getRightNode();
                    return (left.subFindNode(value) || right.subFindNode(value));
                }
                else if (this.getLeftNode() != null && this.getRightNode() == null){
                    Node<T> left = this.getLeftNode();
                    return left.subFindNode(value);
                }
                else {
                    Node<T> right = this.getRightNode();
                    return right.subFindNode(value);
                }
            }
        }
    }
}
