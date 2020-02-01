package com.forstudy.efjava.ch02.item05;

import org.springframework.stereotype.Component;

@Component
public class DictionaryKR implements Lexicon {

    @Override
    public void print() {
        System.out.println("KR DICTIONARY");
    }
}
