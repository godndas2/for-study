package com.forstudy.efjava.ch04.item19;

import java.time.Instant;

/**
* @author halfdev
* @since 2020-02-09
* 재정의 가능 메서드를 내부적으로 어떻게 사용하는지 반드시 문서로 남겨라
 *
 * 상속을 고려하여 클래스를 설계할 때 protected 로 선언할 멤버는 어떻게 정하나?
 * 하위 클래스를 몇 개 만들어 봐도 사용할 일이 없었던 protected 멤버는 private 으로 선언 해야 함
*/
public final class Sub extends Super {

    // 생성자에서 호출하는 메서드를 재정의했을 때의 문제를 보여준다.
    private final Instant instant;

    Sub() {
        instant = Instant.now();
    }

    // 재정의 가능 메서드. 상위 클래스의 생성자가 호출한다.
    @Override
    public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        /*
        * 두 번 instance 찍힐것을 예상했지만, 첫번째는 null, 두번째만 instance 찍힌다.
        * Main 에서 new Sub() 를 하게되면 하위클래스의 생성자가 호출 ->
        * 상위 클래스의 생성자 호출 -> overrideMe()를 호출
        * (하지만 instance 값이 초기화만 되었고 아무 값이 없다.) 그리고 ->
        * sub 생성자의 Instant.now()가 실행되고 instance 필드에 날짜 값이 할당된다. -> sub.overrideMe()를 호출하면 ->
        * instance 가 찍힌다.
        * */
        sub.overrideMe();
    }
}
