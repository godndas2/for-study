package com.forstudy.efjava.ch02.item05;

import org.springframework.stereotype.Component;

import java.util.List;

/**
* @author halfdev
* @since 2020-02-02
* 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
 * 사용하는 자원에 따라 동작이 달라지는 클래스의 경우에는 정적 유틸클래스나 싱글턴 방식은 적합하지 않다.
 * SpellChecker Class 가 여러 인스턴스를 지원하며,
 * 클라이언트가 원하는 자원(dictionary)를 사용하도록 하기 위해서는
 * 인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식이다.
*/
@Component
public class SpellChecker {

    private Lexicon lexicon;

    public SpellChecker(Lexicon lexicon) {
        this.lexicon = lexicon;
    }

    public boolean isValid(String word) {
        lexicon.print();
        return true;
    }

    public List<String> suggestions(String typo) {
        throw new UnsupportedOperationException();
    }
}
