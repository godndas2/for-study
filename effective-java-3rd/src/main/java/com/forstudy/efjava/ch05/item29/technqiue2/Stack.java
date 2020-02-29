package com.forstudy.efjava.ch05.item29.technqiue2;

import com.forstudy.efjava.ch05.item29.EmptyStackException;

import java.util.Arrays;

/**
* @author halfdev
* @since 2020-02-29
* Object[]를 이용한 제네릭 Stack
*/
public class Stack<E> {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    // 코드 29-4 배열을 사용한 코드를 제네릭으로 만드는 방법
    // 비검사 경고를 적절히 숨긴다.
    public E pop() {
        if (size == 0) throw new EmptyStackException();

        // push 에서 E 타입만 허용하므로 이 형변환은 안전하다.
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];

        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    // 제네릭 Stack 을 사용하는 맛보기
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        for (String arg : args) stack.push(arg);
        while (!stack.isEmpty()) System.out.println(stack.pop().toUpperCase());
    }
}
