package stack;

import java.lang.reflect.Array;

//based on array
public class MyStack<T> {
    //定义top，相当于栈中的指针
    private static int top = 0;
    //用于储存数据的底层数组
    private T[] stack;

    public MyStack(Class<T> type, int maxSize){
        stack = (T[]) Array.newInstance(type, maxSize);
    }

    public void push(T value)
    {
        stack[top] = value;
        top++;
    }

    public T pop()
    {
        T pop = stack[--top];
        stack[top] = null;
        return pop;
    }

    public boolean ifEmpty()
    {
        if(top == 0)
            return true;
        else return false;
    }

    public int getSize()
    {
        return top ;
    }

    public void printStack()
    {
        if (top == 0)
            System.out.println("空栈");
        else {
            System.out.println("栈中含有 " + this.getSize() + " 个元素");
            for (int i = 0; i < top; i++)
                System.out.print("  " + stack[i]);
            System.out.println();
        }
    }

    public T getTop()
    {
        int i = top - 1;
        return stack[i];
    }
}
