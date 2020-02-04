package com.forstudy.efjava.ch03.item10.composition;

import com.forstudy.efjava.ch03.item10.Color;
import com.forstudy.efjava.ch03.item10.Point;

import java.util.Objects;

/**
* @author halfdev
* @since 2020-02-04
* equals 규약을 지키면서 값 추가
*/
public class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        point = new Point(x,y);
        this.color = Objects.requireNonNull(color);
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }

    @Override public int hashCode() {
        return 31 * point.hashCode() + color.hashCode();
    }
}
