package com.forstudy.efjava.ch03.item14;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

// PhoneNumber 비교
public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {

    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area Code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "lineNum");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof com.forstudy.efjava.ch03.item11.PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%03d-%03d-%04d",areaCode,prefix,lineNum);
    }

    // 기본 타입 필드가 여럿일 때 비교
//    public int compareTo(PhoneNumber p) {
//        int result = Short.compare(areaCode,p.areaCode);
//        if (result == 0) {
//            result = Short.compare(prefix, p.prefix);
//            if (result == 0) {
//                result = Short.compare(lineNum, p.lineNum);
//            }
//        }
//        return result;
//    }

    @Override
    public int compareTo(PhoneNumber o) {
        return COMPARATOR.compare(this, o);
    }

    // 비교자 생성 메서드를 활용한 비교자
    private static final Comparator<PhoneNumber> COMPARATOR = Comparator.comparingInt((PhoneNumber pn) ->
            pn.areaCode)
            .thenComparing(pn -> pn.prefix)
            .thenComparing(pn -> pn.lineNum);

    public static PhoneNumber randomPhoneNumber() {
        Random r = ThreadLocalRandom.current();
        return new PhoneNumber((short) r.nextInt(1000),
                (short) r.nextInt(1000),
                (short) r.nextInt(10000));
    }

    public static void main(String[] args) {
        NavigableSet<PhoneNumber> p  = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            p.add(randomPhoneNumber());
        }
        System.out.println(p);
    }

}
