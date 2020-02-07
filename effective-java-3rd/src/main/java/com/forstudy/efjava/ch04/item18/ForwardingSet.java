package com.forstudy.efjava.ch04.item18;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
* @author halfdev
* @since 2020-02-07
* 상속보다는 컴포지션을 사용하라
 *
 * Composition : 기존 클래스를 확장하는 대신, 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조한다.
 * 새 클래스의 인스턴스 메서드(전달메서드, forwarding method)들은
 * 기존 클래스의 대응하는 메서드를 호출해 그 결과를 반환(전달, forwarding)한다.
*/
public class ForwardingSet<E> implements Set<E> {

    private final Set<E> s;

    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    public void clear() {
        s.clear();
    }

    public boolean contains(Object o) {
        return s.contains(o);
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }

    public int size() {
        return s.size();
    }

    public Iterator<E> iterator() {
        return s.iterator();
    }

    public boolean add(E e)           { return s.add(e);      }

    public boolean remove(Object o)   { return s.remove(o);   }

    public boolean containsAll(Collection<?> c)
    { return s.containsAll(c); }

    public boolean addAll(Collection<? extends E> c)
    { return s.addAll(c);      }

    public boolean removeAll(Collection<?> c)
    { return s.removeAll(c);   }

    public boolean retainAll(Collection<?> c)
    { return s.retainAll(c);   }

    public Object[] toArray()          { return s.toArray();  }

    public <T> T[] toArray(T[] a)      { return s.toArray(a); }

    @Override
    public boolean equals(Object o) { return s.equals(o);  }

    @Override
    public int hashCode()    { return s.hashCode(); }

    @Override
    public String toString() { return s.toString(); }

}
