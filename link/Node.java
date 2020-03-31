package link;

public class Node<T> {
    private Node<T> head;
    private Node<T> next;
    private T value;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    //add an element to the link
    public void add(T value) {
        Node<T> temp = new Node<T>(value);
        Node<T> ptr = head;

        if (head == null) {
            head = temp;
            return;
        }

        while (head.hasNext()) {
            head = head.next;
        }
        head.next = temp;

        head = ptr;
    }

    //print all the elements of this link
    public void printLink() {
        if (this.getLength() == 0) {
            System.out.println("Empty Link");
            return;
        }

        Node<T> iterate = head;
        System.out.println("链表中的元素为：");
        while (iterate != null) {
            System.out.print("  " + iterate.value);
            iterate = iterate.next;
        }
    }

    //judge if current node has a next node
    public boolean hasNext() {
        if (this.next != null)
            return true;
        else return false;
    }

    //return the number of elements in the Link
    public int getLength() {
        int count = 1;
        Node<T> iterate = head;

        if(head == null)
            return 0;

        while (iterate.hasNext()) {
            count++;
            iterate = iterate.next;
        }

        return count;
    }

    /*
     *  reverse the link
     *  eg.
     *  1 -> 2 -> 3 -> null
     *  aim:
     *  null <- 1 <- 2 <- 3
     */
    public void reverse()
    {
        //定义先前节点和下一节点为NULL
        Node<T> preNode = null;
        Node<T> nextNode = null;
        //定义当前节点为head
        Node<T> tempNode = head;
        //通过遍历，将节点间的指针反向
        while(tempNode != null)        //当tempNode为空时，结束遍历
        {
            nextNode = tempNode.next;
            tempNode.next = preNode;
            preNode = tempNode;
            //最后一次遍历无需再将nextNode赋给tempNode,终止循环
            if(nextNode == null)
                break;
            tempNode = nextNode;
        }
        //最后令tempNode为headNode
        head = tempNode;
    }

    /*
     *  delete a Node                          -- using position
     *  eg. 1 -> 2 -> 3 -> 4 -> null
     *  aim:
     *  1 -> 3 -> 4 -> null
     */
    public void delete(int pos) throws Exception
    {
        //如果传入参数大于链表长度，则删除最后一个元素
        if (pos > this.getLength())
        {
            Node<T> iterate = head;
            while(iterate.next.hasNext())
                iterate = iterate.next;
            iterate.next = null;

            return;
        }
        //若传入的位置参数小等于0，则抛出异常
        if (pos <= 0)
            throw new Exception("位置输入有错！");

        if (pos == 1)
            head = head.next;
        else {
            //定义迭代节点
            Node<T> iterate = head;
            int i = 1;
            while (i++ < pos - 1)
                iterate = iterate.next;
            iterate.next = iterate.next.next;
        }
    }

    //remove designated node                      -- using an element
    public void remove(Node<T> node)
    {
        //定义迭代节点
        Node<T> iterate = head;

        //若头节点为要删除的节点
        if (head.isEqual(node))
        {
            head = head.next;
            return;
        }

        //其他节点为要删除的节点
        while(iterate.hasNext())
        {
            //如果node等于迭代的节点，删除该节点
            if (iterate.next.isEqual(node)) {
                iterate.next = iterate.next.next;
                return;
            }
            iterate = iterate.next;
        }
        System.out.println("未找到元素" + node.getValue());
    }

    /*
     *  insert a Node at the designated position
     *  eg.
     *  1 -> 2 -> 3 -> null
     *  aim:
     *  1 -> 7 -> 2 -> 3 -> null
     */
    public void insert(int pos, Node<T> node)
    {
        //如果插入的位置大于链表的总长度，则放在链表最后面，相当于add
        if(pos > this.getLength()) {
            this.add(node.value);
            return;
        }

        //遍历到指定位置的节点处
        int i = 1;
        //保留头节点
        Node<T> temp = head;
        while(i < pos)
        {
            head = head.next;
            i++;
        }

        //将新节点插入至链表
        node.next = head.next;
        head.next = node;

        head = temp;
    }

    //return the pos of a Node
    public void getPos(Node<T> node) {
        int pos = 1;
        //定义迭代节点
        Node<T> iterate = head;
        while (iterate.hasNext())
        {
            if (iterate.isEqual(node)) {
                System.out.println("value为 " + node.getValue() + " 的节点位置为 " + pos);
                return;
            }
            pos++;
            iterate = iterate.next;
        }

        System.out.println("该节点不在链表中");
    }

    //judge if two Nodes equals
    public boolean isEqual(Node<T> node)
    {
        if (this.value == node.value)
            return true;
        else    return false;
    }

    //return the value of the Node
    public T getValue()
    {
        return this.value;
    }

    //select sort
    public void selectSort()
    {
        //定义迭代节点_1
        Node<T> iterate_1 = head;
        //定义i记录迭代节点_1的位置
        int i = 1;
        //迭代节点_1迭代至倒数第二个元素处
        while (iterate_1.hasNext()){
            //定义j记录迭代节点_2的位置
            int j = i + 1;
            //定义j记录最小节点的位置
            int k = 0;
            //定义迭代节点_2用于找寻最小值节点
            Node<T> iterate_2 = iterate_1.next;
            //定义迭代节点_3用于交换最小值
            Node<T> iterate_3 = iterate_1;
            //定义low记录最小值
            T low = iterate_1.getValue();
            while (iterate_2 != null){
                //若某一节点的值小于low，更新low并且记录该节点的位置
                if ((Integer)iterate_2.getValue() < (Integer)low){
                    low = iterate_2.getValue();
                    k = j;
                }
                iterate_2 = iterate_2.next;
                j++;
            }
            //利用最小节点的位置k，找到该节点
            for (int iterator = i; iterator < k; iterator++){
                iterate_3 = iterate_3.next;
            }
            //最小值节点与迭代节点_1的值互换
            iterate_3.value = iterate_1.value;
            iterate_1.value = low;

            iterate_1 = iterate_1.next;
            i++;
        }
    }
}