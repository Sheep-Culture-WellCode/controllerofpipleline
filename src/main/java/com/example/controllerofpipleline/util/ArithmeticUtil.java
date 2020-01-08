package com.example.controllerofpipleline.util;

public class ArithmeticUtil {
    public static int maxNum(int a,int b, int c ,int d){
        int number=a;
        if(number>=b&&number>=c&&number>=d)
        {
            return number;
        }
        else
        {
            if(number<b) {
                maxNum(b,b,c,d);
            } else if(number<c){
                maxNum(c,b,c,d);
            } else {
                maxNum(d,b,c,d);
            }
        }
        return -100;
    }

    public static boolean isBiggest(int a,int b ,int c,int d){
        if (a>=b && a>=c && a>= d){
            return true;
        }else {
            return false;
        }
    }
}
