package link;

import java.util.Scanner;

public class MyLink {
    public static void main(String[] args) throws Exception
    {
        Node<Integer> link = new Node<>();
        link.add(8);
        link.add(9);
        link.add(4);
        link.add(2);
        link.add(3);
        link.add(10);
        link.add(5);
        link.add(1);

        link.selectSort();
        link.printLink();
/*
        //reverse test
        System.out.println("reverse的结果为：");
        link.reverse();
        link.printLink();

        System.out.println();

        //insert test
        System.out.println("在第二、八个节点后插入新节点：");
        link.insert(2, new Node<>(999));
        link.insert(9, new Node<>(6));
        link.printLink();

        System.out.println();

        //getPos test
        link.getPos(new Node<>(4));

        //delete test
        System.out.println("删除链表中的第一个元素");
        link.delete(1);
        link.printLink();
        System.out.println();

        System.out.println("删除链表中的第四个元素");
        link.delete(4);
        link.printLink();

        System.out.println();

        //remove test
        System.out.println("删除链表中的元素2");
        link.remove(new Node<Integer>(2));
        link.printLink();
        System.out.println();
        System.out.println("删除链表中的元素6");
        link.remove(new Node<Integer>(6));
        link.printLink();
*/
    }
}
