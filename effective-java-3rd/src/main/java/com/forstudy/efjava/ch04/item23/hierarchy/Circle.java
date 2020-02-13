package com.forstudy.efjava.ch04.item23.hierarchy;

// 태그 달린 클래스를 클래스 계층구조로 변환
public class Circle extends Figure {

    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI *  (radius * radius);
    }
}
