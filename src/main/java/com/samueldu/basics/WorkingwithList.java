package com.samueldu.basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WorkingwithList {
    public static void main(String [] args){
        //List<String> l = new ArrayList<>();
        List<String> l = new LinkedList<>();
        for( int i=0; i<10; i++){
            l.add("Element"+i);
        }

        l.forEach(System.out::println);

        List ls = List.of('a', 'b', 'c');

        //throws Exception, can't add into immutable arrays.
        //ls.add('d');
        ls.forEach(System.out::println);


        List lString = List.of("a", "b", "c");
        lString.forEach(System.out::print);

    }
}
