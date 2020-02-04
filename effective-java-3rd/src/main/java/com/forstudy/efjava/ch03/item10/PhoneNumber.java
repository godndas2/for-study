package com.forstudy.efjava.ch03.item10;

public final class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "Prefix");
        this.lineNum = rangeCheck(lineNum, 999, "가입자 번호");
    }

    public static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhoneNumber))
            return false;

        PhoneNumber phoneNumber = (PhoneNumber) o;
        return phoneNumber.lineNum == lineNum && phoneNumber.prefix == prefix
                && phoneNumber.areaCode == areaCode;
    }
}
