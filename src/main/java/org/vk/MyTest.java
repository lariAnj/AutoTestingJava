package org.vk;

import org.junit.jupiter.api.Assertions;
import java.util.Scanner;


public class MyTest {
    private static int numberFunc() {
        System.out.println("Enter a number:");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }


    public static void main(String[] args) {
        int numberFuncOutput = numberFunc();
        Assertions.assertTrue(numberFuncOutput < 10, "Number < 10");
        System.out.println("Everything is ok");
    }
}