package com.example;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add(""+i);
        }
        list.set(5,"456");
        for (String str : list) {
            System.out.print(str+" ");
        }

    }
}
