package com.forstudy.efjava.ch03.item14;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
* @author halfdev
* @since 2020-02-06
* Comparable 을 구현할지 고려하라
 * compareTo 메서드가 정의되어 있다. Object 클래스의 equals 와 비슷하지만 차이점으로는 순서를 비교할 수 있고, 제네릭이다.
 *
 * compareTo : 현재 객체와 주어진 객체의 순서를 비교한다
 * 현재 객체 < 주어진 객체 : 음의 정수
 * 현재 객체 == 주어진 객체 : 0
 * 현재 객체 > 주어진 객체 : 양의 정수
 *
 * 순서를 고려해야 하는 값 클래스라면 꼭 Comparable 인터페이스를 구현해야 한다.
 * 기존에 구현된 컬렉션에서는 compareTo 메서드가 구현되어 있다고 생각하고
 * 정렬, 검색, 비교 기능을 구현하고 있다. 필드의 값을 비교할 때는
 * 박싱된 기본 타입 클래스가 제공하는 compare Method 또는 Comparator 인터페이스가 제공하는 기능을 사용하자.
*/
public class WordList {
    public static void main(String[] args) {
        Set<String> objects = new TreeSet<>();
        Collections.addAll(objects, args);
        System.out.println(objects);
    }
}
