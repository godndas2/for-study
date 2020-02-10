package com.forstudy.efjava.ch04.item20;

import java.util.Map;
import java.util.Objects;

/**
* @author halfdev
* @since 2020-02-10
* 추상 클래스보다는 인터페이스를 우선하라
 *
 * 추상 골격 클래스를 사용하면 인터페이스와 추상 클래스의 장점을 모두 취할 수 있다. 인터페이스로는 타입을 정의하고,
 * 구현 방법이 명백한 것은 디폴트 메서드로 제공한다. 그리고 골격 구현 클래스에서는 나머지 메서드들을 모두 구현한다.
 * 컬렉션 프레임워크의 AbstractCollection, AbstractSet, AbstractList 등은 컬렉션 인터페이스의 골격 구현이다.
 * AbstractList 추상 클래스는 List 인터페이스를 구현하고 AbstractCollection 클래스를 상속 받는다.
*/
public abstract class AbstractMapEntry<K,V> implements Map.Entry<K,V> {

    // 변경 가능한 Entry 는 이 Method 를 반드시 재정의한다.
    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    // Map.Entry.equals 의 일반 규약을 구현한다.
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Map.Entry)) return false;
        Map.Entry<?,?> e = (Map.Entry)o;

        return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());
    }

    // Map.Entry.hashCode 의 일반 규약을 구현한다.
    @Override
    public int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
