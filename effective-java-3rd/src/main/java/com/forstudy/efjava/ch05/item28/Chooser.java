package com.forstudy.efjava.ch05.item28;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
* @author halfdev
* @since 2020-02-26
* 리스트 기반 Chooser - 타입 안전성 확보!
*/
public class Chooser<T> {

    private final List<T> choiceList;

    public Chooser(Collection<T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6);

        Chooser<Integer> chooser = new Chooser<>(intList);

        for (int i = 0; i < 10; i++) {
            Number choice = chooser.choose();
            System.out.println(choice);
        }
    }

}
