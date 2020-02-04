package com.forstudy.efjava.ch03.item11;

import java.util.HashMap;
import java.util.Map;

/**
* @author halfdev
* @since 2020-02-04
* equals 를 재정의하려거든 hashCode 도 재정의하라
*/
public class PhoneNumber {

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
        if (!(o instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber)o;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    // 자주 쓰이는 hashcode, hashCode() 를 주석하면 아래 Main 의 51 라인은 null 발생
    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();

        m.put(new PhoneNumber(707,867,5309), "Jenny");
        System.out.println(m.get(new PhoneNumber(707,867,5309)));
    }
}
