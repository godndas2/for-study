package com.forstudy.efjava.ch04.item16;

// 불변 필드를 노출한 public Class - public 으로 노출했지만 변경이 불가능하다. 그래도 쓰지말자.
public class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTE_PER_HOUR = 60;

    // Here
    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour >= HOURS_PER_DAY) throw new IllegalArgumentException("Hour : " + hour);
        if (minute < 0 || minute >= MINUTE_PER_HOUR) throw new IllegalArgumentException("Minute : " + minute);

        this.hour= hour;
        this.minute = minute;
    }
}
