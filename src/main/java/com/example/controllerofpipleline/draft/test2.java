package com.example.controllerofpipleline.draft;

import java.io.DataInputStream;
import java.util.HashSet;

public class test2 {
    public static String staticProperty = "1";
    public String property = "a";
    static {
        staticProperty = "2";
    }
    public test2 () {
        this.property = "b";
        staticProperty = "3";
    }
    {
        this.property = "c";
        staticProperty = "4";
    }
    public static void main(String[] args){
        System.out.println(test2.staticProperty);
        System.out.println(new test2().property);
        System.out.println(test2.staticProperty);
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {

    }
}

