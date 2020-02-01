package com.forstudy.efjava.ch02.item07;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
* @author halfdev
* @since 2020-02-02
* 다 쓴 객체 참조를 해제하라
 * GC : 시스템에서 더 이상 사용하지 않는 동적 할당된 메모리 블록 혹은 개체를 찾아서
 * 자동적으로 다시 사용 가능한 자원으로 회수하는 것을 말한다.
*/
public class Stack {

    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        this.ensureCapacity();
        this.elements[size++] = e;
    }

    // 메모리 누수
//    public Object pop() {
//        if (size == 0) {
//            throw new EmptyStackException();
//        }
//        return this.elements[--size]; // 스택이 다 쓴 참조(obsolete reference)를 가지고 있기 때문
//    }

    // 메모리 누수를 해결하기 위한 null 처리
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Object o = this.elements[--size];
        this.elements[size] = null;
        return o;
    }

    /**
     * 원소를 위한 공간을 적어도 하나 이상 확보한다.
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
     */
    public void ensureCapacity() {
        if (this.elements.length == size) {
            this.elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        for (String arg : args) {
            stack.push(arg);
        }

        while (true) System.err.println(stack.pop());

    }
}
