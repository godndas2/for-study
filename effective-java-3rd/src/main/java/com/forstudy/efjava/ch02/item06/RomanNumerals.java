package com.forstudy.efjava.ch02.item06;

import java.util.regex.Pattern;

/**
* @author halfdev
* @since 2020-02-02
* 불필요한 객체 생성을 피하라
 *
 * // String 리터럴로 선언해서 동일한 문자열이면 같은 객체를 사용하는 방식
 * String s = "Effective Java";
 *
 * // String 객체를 매번 생성하는 방식
 * String s2 = new String("Effective Java");
 *
 * 자주 사용한다면 클래스 필드에 미리 생성해 캐싱해두고 필요할 때마다 재사용하는 것이 좋다.
 * 미리 생성하는 것조차도 비효율적이다면, 지연 초기화(lazy initialization)로 불필요한 초기화를 없앨 수 있다.
*/
public class RomanNumerals {

    // 문자열이 로마숫자인지 체크 (비효율)
    public static boolean isRomanNumeralsSLOW(String str) {
        return str.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // Pattern 인스턴스를 캐싱해서 재사용하는 코드 (효율)
    public static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"
    );

    public static boolean isRomanNumerals(String str) {
        return ROMAN.matcher(str).matches();
    }

    public static void main(String[] args) {
        int numSets = Integer.parseInt(args[0]);
        int numReps = Integer.parseInt(args[1]);
        boolean b = false;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < numReps; j++) {
                // 성능 체크
                b ^= isRomanNumerals("MCMLXXVI");
            }
            long end = System.nanoTime();
            System.out.println(((end - start) / (1_000. * numReps)) + " μs.");
        }

        // VM 최적화 방해 코드
        if (!b) System.out.println();
    }
}
