package com.example;

public class MyClass {

    public static void main(String[] args) {
        int i = 1000;
        while (i-- > 0)
            System.out.print(Math.round(Math.random()) == 1);

    }

}
