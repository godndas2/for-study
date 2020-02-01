package com.forstudy.efjava.ch02.item04;

import javax.rmi.CORBA.Util;

/**
* @author halfdev
* @since 2020-02-02
* 인스턴스화를 막으려거든 private 생성자를 사용하라
 *
 * 단순히 정적 메서드와 정적 필드만을 담은 클래스를 만들어야 하는 경우가 있다.
 * 이 방식은 객체 지향적으로 좋은 않은 클래스라고 여길 수 있지만, 이러한 클래스도 나름의 쓰임새가 있다.
 * 예를 들어 java.lang.Math 와 java.util.Arrays 클래스처럼 기본 타입 값이나
 * 배열 관련 메서드를 모아 놓을 수 있다. 또한, java.util.Collections 처럼
 * 특정 인터페이스를 구현하는 객체를 생성해주는 정적 메서드를 가지고 있는 클래스가 있을 수 있다.
*/
public class UtilClass {

    private UtilClass() {
//        throw new Exception("UtilClass 는 Instance 할 수 없다.");
        throw new AssertionError();
    }

    public static int plus(int a, int b) {
        return a+b;
    }
}


