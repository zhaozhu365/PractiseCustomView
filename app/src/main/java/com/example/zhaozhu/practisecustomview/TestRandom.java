package com.example.zhaozhu.practisecustomview;

import java.util.Random;

/**
 * author: zhaozhu
 * Created on 17/8/1
 */
public class TestRandom {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int a = random.nextInt(4);
            System.out.print(a);
        }
    }

}
