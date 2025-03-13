package org.barsit.smallspring.test.MethodRef;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.test.MethodRef
 * @author:db
 * @createTime:2025/3/13 11:02
 * @version:1.0
 */
public class MethodRefTest {
    public static void main(String[] args) {
        String[] strings = {"apple", "Orange", "Banana", "Lemon"};
//        int[] ints = new int[0];
        Arrays.sort(strings, MethodRefTest::cmp);
        Arrays.stream(strings).forEach(System.out::println);
     }
    public static  int cmp(String s1, String s2){
        return s1.toLowerCase().compareTo(s2.toLowerCase());
//        return s1.compareTo(s2);
    }
}
