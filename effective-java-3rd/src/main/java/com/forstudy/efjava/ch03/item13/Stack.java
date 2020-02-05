package com.forstudy.efjava.ch03.item13;

import java.util.Arrays;

/**
* @author halfdev
* @since 2020-02-06
* clone 재정의는 주의해서 진행하라
 *  clone 메서드가 선언된 곳이 Cloneable 이 아닌 Object 이고,
 *  protected 로 되어있어서, Cloneable 를 구현하는 것만으로도 외부 객체에서 clone 메서드 호출이 불가능하다.
 *  Cloneable 을 구현한 클래스의 인스턴스에서 clone()을 호출하면,
 *  그 객체의 필드들을 하나하나 복사한 객체를 반환하며, 그렇지 않은 클래스의 인터페이스를 호출하면
 *  CloneNotSupportedException 발생
*/
public class Stack implements Cloneable {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // 사용이 끝난 참조 해제
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 가변 상태를 참조하는 Class 전용 Clone Method
    @Override
    public Stack clone() {
        try {
            Stack result = (Stack) super.clone();
            result.elements = elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // 원소를 위한 공간을 적어도 하나 이상 확보한다.
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    // clone 이 동작하는 모습을 보려면 명령줄 인수를 몇 개 덧붙여서 호출해야 한다.
    public static void main(String[] args) {
        Stack stack = new Stack();
        for (String arg : args) {
            stack.push(arg);
            Stack copy = stack.clone();
            while (!stack.isEmpty()) System.out.println(stack.pop() + " ");
            System.out.println();
            while (!copy.isEmpty()) System.out.println(copy.pop() + " ");
        }
    }
}
