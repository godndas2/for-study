package com.forstudy.efjava.ch02.item01;

import com.sun.prism.paint.Color;

import java.util.EnumSet;

import static com.forstudy.efjava.ch02.item01.Main.Country.GERMANY;
import static com.forstudy.efjava.ch02.item01.Main.Country.KOREA;

/**
* @author halfdev
* @since 2020-01-29
* 생성자 대신 정적 팩토리 메소드를 고려하라
 *
 * static factory method RULE
 *
 * from : 매개변수 하나 받아서 인스턴스화 ex. Date.from()
 * of : 여러 매개변수를 받아서 인스턴스화 ex. Enumset.of()
 * valueOf : from 과 of의 자세한 버전 ex. BigInteger.valueOf()
 * getInstance : 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지 않는다.
 * create, newInstance : 매번 새로운 인스턴스를 생성해 반환한다.
*/
public class Main {

    String name;
    String age;

    public Main(){}

    private static final Main THIS_IS_MAIN = new Main();

    public Main(String name) {
        this.name = name;
    }

    public static Main withAge(String age) {
        Main main = new Main();
        main.age = age;
        return main;
    }

    public static Main withName(String name) {
        return new Main(name);
    }

    public static Main getMain() {
        return THIS_IS_MAIN;
    }

    public static Main getMain(boolean b) {
        Main main = new Main();

        return main;
    }

    public static void main(String[] args) {
        Main main = new Main("halfdev");
        Main main2 = Main.withName("halfdev");
        Main main3 = Main.getMain();
        Main main4 = Main.getMain(false);

        EnumSet<Country> colors = EnumSet.allOf(Country.class);

        EnumSet<Country> krAndGer = EnumSet.of(KOREA,GERMANY);

    }

    enum Country {
        KOREA, GERMANY, JAPAN
    }

    // private static method 가 필요한 이유
    public static void test() {
        testing();
    }

    public static void test2() {
        testing();
    }

    public static void testing() {
        System.out.println("TESTING..!");
    }



}
