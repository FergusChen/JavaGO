package main.collection;
import java.util.LinkedList;
/**
 * Created by Administrator on 2016/8/22.
 *
 * 用LinkedList实现堆栈或队列是比较合适的。相类似的数据结构都可以用LinkedList
 */
class Stack{
    private LinkedList stack;
    Stack(){
        stack = new LinkedList();
    }

    public void push(Object obj){
        stack.addFirst(obj);
    }

    public Object pop(){
        return stack.pollFirst();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}

class Queue{
    private LinkedList queue;
    Queue(){
        queue = new LinkedList();
    }

    public void enQueue(Object obj){
        queue.addFirst(obj);
    }
    public Object deQueue(){
        return queue.pollLast();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
public class LinkedListDemo {
    public static void main(String[] args){
        LinkedList list = new LinkedList();
        list.addFirst("itemfirst");
        list.add("m1");
        list.addLast("itemlast");
//        System.out.println(list);
        Stack myStack = new Stack();
        myStack.push("a1");
        myStack.push("a2");
        myStack.push("a3");
        while(!myStack.isEmpty()){
            System.out.println(myStack.pop());
        }

        Queue myQueue = new Queue();
        myQueue.enQueue("a1");
        myQueue.enQueue("a2");
        myQueue.enQueue("a3");
        while (!myQueue.isEmpty()){
            System.out.println(myQueue.deQueue());
        }
    }
}
