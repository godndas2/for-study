package com.forstudy.efjava.ch02.item03;

/**
* @author halfdev
* @since 2020-02-01
* private 생성자나 열거 타입으로 싱글턴임을 보증하라
 * Singleton : Instance 를 오직 하나만 생성할 수 있는 클래스
 * Singleton 을 만들 때 생성자는 private 으로, 유일한 Instance 에 접근할 수 있는 수단으로는
 * public static final 방식 및 정적팩토리 방식 제공
*/
public class ElvisPSF {

    public static final ElvisPSF INSTANCE = new ElvisPSF();

    private ElvisPSF() {}

    public static ElvisPSF getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
        System.out.println("I am outta here");
    }

    public static void main(String[] args) {
        ElvisPSF elvisPSF = ElvisPSF.getInstance();
        elvisPSF.leaveTheBuilding();
    }


}
