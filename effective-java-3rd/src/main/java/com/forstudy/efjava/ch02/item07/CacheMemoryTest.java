package com.forstudy.efjava.ch02.item07;

import java.util.Map;
import java.util.WeakHashMap;

/**
* @author halfdev
* @since 2020-02-02
* 캐시 메모리 누수 주의
 * Tip : Java Reference -> Strong References, Soft References, Weak References
 * https://d2.naver.com/helloworld/329631
 * https://d2.naver.com/helloworld/1329
*/
public class CacheMemoryTest {
    public static void main(String[] args) {
        Map<Key, String> map = new WeakHashMap<>();

        Key key = new Key("name");

        map.put(key, "halfdev");
        // Key{name='name'}=halfdev
        print(map);

        // Key Object Reference null
        key = null;
        // Force GC
        System.gc();
        // 빈 값 출력
        print(map);
    }

    public static void print(Map<?,?> map) {
        map.entrySet().stream().forEach(System.out::println);
    }


}

class Key {

    private String name;

    public Key(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Key{" +
                "name='" + name + '\'' +
                '}';
    }
}
