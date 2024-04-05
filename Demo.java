package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<Integer> list1=new LinkedList<>();
        for(int i=0;i<1_000_000_0;i++){
            list1.add(i);
        }


        var start= System.currentTimeMillis();
        list1.remove(10000);
        var end =System.currentTimeMillis();
        System.out.println((end-start));
    }
}
