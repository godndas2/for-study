package com.forstudy.efjava.ch04.item17;

import java.awt.event.ComponentListener;

/**
* @author halfdev
* @since 2020-02-07
* 변경 가능성을 최소화하라
 * 불변 클래스 : Instance 의 내부 값을 수정할 수 없는 클래스
 *
 * 불변 클래스로 만드는 규칙
 *
 * 객체의 상태를 변경하는 메서드를 제공하지 않는다.
 * 클래스를 확장할 수 없도록 한다.
 * 모든 필드를 final 로 선언한다.
 * 모든 필드를 private 으로 선언한다.
 * 자신 외에는 내부의 가변 컴포넌트에 접근하지 못하도록 한다.
 *
 * 단점
 * 값을 나타내는 클래스를 주로 불변 클래스로 설계한다. 이 때, 각 값이 다르면 독립된 객체를 만드는 것이 단점
*/
public class Complex {
    private final double re;
    private final double im;

    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    // private 생성자와 함께 사용해야한다.
    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im, re * c.im + c.re);
    }

    public Complex dividedBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
                (im * c.re - re * c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Complex))
            return false;
        Complex c = (Complex) o;

        return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
