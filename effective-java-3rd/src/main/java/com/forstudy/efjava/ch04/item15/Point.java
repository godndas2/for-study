package com.forstudy.efjava.ch04.item15;

/**
* @author halfdev
* @since 2020-02-06
* 클래스와 멤버의 접근 권한을 최소화하라
 * Why ?
 * 시스템 개발 속도를 높인다. 여러 컴포넌트를 병렬로 개발할 수 있기 때문이다.
 * 시스템 관리 비용을 낮춘다.각 컴포넌트를 더 빨리 파악하여 디버깅할 수 있고, 다른 컴포넌트로 교체하는 부담도 적다.
 * 정보 은닉 자체가 성능을 높여주지는 않지만, 성능 최적화에 도움을 준다. 다른 컴포넌트에 영향을 주지 않고 해당 컴포넌트만 최적화할 수 있기 때문
 * 소프트웨어 재사용성을 높인다. 독자적으로 동작할 수 있는 컴포넌트라면 그 컴포넌트와 함께 개발되지 않은 낯선 환경에서도 유용하게 쓰일 가능성이 크다.
 *
 * 공개 API 를 세심히 설계한 후 그 외의 모든 멤버는 private 로 만든다.
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

    // ..

}
