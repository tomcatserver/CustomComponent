package com.example.java;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Test {
    @org.junit.Test
    public void main() {
        arr(10);
    }

    public static Object[] arr(int length){
        Random random= new Random(length);
        List<Integer> list =new LinkedList<>();

        while(true){
            int val= random.nextInt(10);
            if(!list.contains(val)){
                list.add(val);
            }
            if(list.size()==length){
                break;
            }
        }
//        Object[] objects = list.toArray();

        System.out.println("arrats------="+ list);

        return null;
    }
}
