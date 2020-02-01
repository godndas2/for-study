package com.forstudy.efjava.ch02.item06;

/**
* @author halfdev
* @since 2020-02-02
* auto boxing 은 불필요한 객체를 만들어낸다.
 * 오토 박싱은 개발자가 기본 타입과 박싱된 기본 타입을 섞어 쓸 때 자동으로 상호 변환해주는 기술이다.
 * int 기본 타입의 박싱된 기본 타입은 Integer
*/
public class Sum {

    public static long sum() {
        // long 이 아닌 박싱된 기본 타입 Long 으로 선언했기 때문에 불필요한 Long 인스턴스 를 생성한다.
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) { }




}
