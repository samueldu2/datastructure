package com.samueldu.basics;

import java.util.LinkedList;
import java.util.Queue;

public class WorkingWithQueue {
    public static void main(String []a){
        Queue<String> q=new LinkedList<>();
        q.add("a");
        q.add("b");
        q.add("c");
        System.out.println(q);
        System.out.println(q.poll());

        System.out.println(q);

        System.out.println(q.offer("y"));
        System.out.println(q);

        System.out.println(q.offer("z"));
        System.out.println(q.peek());

    }
}
