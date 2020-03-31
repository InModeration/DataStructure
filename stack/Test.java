package stack;

public class Test{

    public static void main(String[] args)
    {
        MyStack<Double> stack = new MyStack<Double>(Double.class, 100);
        stack.printStack();
        System.out.println(stack.ifEmpty());
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.0);
        stack.printStack();

        System.out.println("顶部元素为 " + stack.getTop());
        System.out.println("pop操作移除元素 " + stack.pop());
        stack.printStack();
       // stack.printStack();
    }
}
