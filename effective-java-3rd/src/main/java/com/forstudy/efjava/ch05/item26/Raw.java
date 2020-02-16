package com.forstudy.efjava.ch05.item26;

import java.util.ArrayList;
import java.util.List;

/**
* @author halfdev
* @since 2020-02-16
* 런타임에 실패한다. - unsafeAdd 메서드가 로 타입(List)을 사용
*/
public class Raw {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));
        String s = strings.get(0);
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }
}
