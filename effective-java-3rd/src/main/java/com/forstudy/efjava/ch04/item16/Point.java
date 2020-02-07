package com.forstudy.efjava.ch04.item16;

/**
* @author halfdev
* @since 2020-02-07
* public 클래스 안에는 public 필드를 두지 말고 접근자 메서드를 사용하라 (캡슐화)
 * public 클래스는 필드를 외부에 직접 공개 하지 말아야 한다.
*/
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
