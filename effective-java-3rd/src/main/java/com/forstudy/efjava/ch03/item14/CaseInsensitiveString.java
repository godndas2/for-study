package com.forstudy.efjava.ch03.item14;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {

    private final String str;

    public CaseInsensitiveString(String str) {
        this.str = Objects.requireNonNull(str);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CaseInsensitiveString && ((CaseInsensitiveString) o).str.equalsIgnoreCase(str);
    }

    @Override
    public int hashCode() {
        return str.hashCode();
    }

    // class 비교
    @Override
    public int compareTo(CaseInsensitiveString o) {
        return String.CASE_INSENSITIVE_ORDER.compare(str, o.str);
    }

    public static void main(String[] args) {
        Set<CaseInsensitiveString> str = new TreeSet<>();
        for (String arg : args) {
            str.add(new CaseInsensitiveString(arg));
        }
        System.out.println(str);
    }
}
