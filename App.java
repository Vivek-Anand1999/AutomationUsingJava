package com.vivek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        String[] ArrayOfVegitable = { "Cucumber", "Beetroot", "Tomato", "Carrot" };
        List name = Arrays.asList(ArrayOfVegitable);
        
        System.out.println(name.size());
    }
}
