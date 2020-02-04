package com.forstudy.efjava.ch03.item11;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* @author halfdev
* @since 2020-02-04
* equals 를 재정의하려거든 hashCode 도 재정의하라
 *  equals 에서 사용된 필드는 hashCode 를 생성할 때 반드시 사용해야 한다.
 * hashCode 를 재정의 하지 않으면 아래와 같은 버그가 발생한다.
 * 해당 객체를 생성해서 HashMap 컬렉션에 우선 저장한다.
 * 이전에 저장한 객체와 동일하게 객체를 생성해서 HashMap 에서 get 메서드를 호출하면 null 값이 반환된다.
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

    // 자주 쓰이는 hashcode 재정의 방법. hashCode() 를 주석하면 아래 Main 의 51 라인은 null 발생
//    @Override
//    public int hashCode() {
//        int result = Short.hashCode(areaCode);
//        result = 31 * result + Short.hashCode(prefix);
//        result = 31 * result + Short.hashCode(lineNum);
//        return result;
//    }

    // 방법 2
//    @Override
//    public int hashCode() {
//        return Objects.hash(areaCode,prefix,lineNum);
//    }

    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();

        m.put(new PhoneNumber(707,867,5309), "Jenny");
        System.out.println(m.get(new PhoneNumber(707,867,5309)));
    }
}
