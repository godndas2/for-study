package com.forstudy.efjava.ch02.item03;

// Singleton 방식 : Double Checked Locking
// 권장하지않음 http://wiki.c2.com/?DoubleCheckedLockingIsBroken
public class ElvisDCL {
    public volatile static ElvisDCL instance;

    private ElvisDCL() {}

    public static ElvisDCL getInstance() {
        if (instance == null) {
            synchronized (ElvisDCL.class) {
                if (instance == null) {
                    instance = new ElvisDCL();
                }
            }
        }
        return instance;
    }
}
