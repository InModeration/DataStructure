package sort;

import java.util.Iterator;

/**
 * 顺序表(array-based list)
 */
public class ArrayBasedList {
    // 默认的顺序表长度
    private int defaulSize = 1000;
    // 用于存储的数组
    private int[] listArray;
    // 数组最多可存储的数据数目
    private int maxSize;
    // 当前的位置
    private int currPos;
    // 当前顺序表的长度
    private int length;

    public ArrayBasedList() {
        listArray = new int[defaulSize];
        maxSize = defaulSize;
        currPos = length = 0;
    }
    public ArrayBasedList(int size) {
        listArray = new int[size];
        maxSize = size;
        currPos = length = 0;
    }

    // 清空当前顺序表的所有元素
    public void clear() {
        listArray = new int[maxSize];
        currPos = length = 0;
    }

    // 在当前位置插入元素
    public void insert(int item) {

    }

    // 在顺序表末尾插入元素
    public void append(int item) {

    }

    // 删除当前位置的元素并返回该元素
    public int remove() {
        return 0;
    }

    public static void main(String[] args) {

    }
}
