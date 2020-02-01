package com.forstudy.efjava.ch02.item05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpellCheckerMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        SpellChecker spellChecker = applicationContext.getBean(SpellChecker.class);

        spellChecker.isValid("HELLO");
    }
}
