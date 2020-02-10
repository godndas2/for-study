package com.forstudy.efjava.ch04.item20;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// 골격 구현을 사용해 완성한 구체 클래스
public class IntArrays {

    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<Integer>() {

            @Override
            public Integer get(int index) {
                return a[index]; // autoBoxing
            }

            @Override
            public Integer set(int index, Integer val) {
                int oldVal = a[index];
                a[index] = val; // autoUnboxing
                return oldVal; // autoBoxing
            }

            @Override
            public int size() {
                return a.length;
            }
        };

    }

    public static void main(String[] args) {
        int [] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        List<Integer> integers = intArrayAsList(a);
        Collections.shuffle(integers);
        System.out.println(integers);
    }
}
