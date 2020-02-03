package com.forstudy.efjava.ch02.item09;

import java.io.*;

/**
* @author halfdev
* @since 2020-02-03
* 자원을 생성하고 해제하는 작업을 코드로 작성하는 것이 간편해졌다.
 * 단, 이 구조를 사용하기 위해서는 해당 자원이 AutoCloseable 인터페이스를 구현해야 한다.
 * 우리가 자주 사용하는 라이브러리에 있는 자원 클래스들은 대부분 AutoCloseable 인터페이스를 이미 구현하고 있다.
*/
public class TryWithResourceCopy {
    private static final int BUFFER_SIZE = 8 * 1024;

    // 복수의 자원을 처리하는 try-with-resources
    static void copy(String src, String dst) throws IOException {
        // 하나의 자원만 새용했지만, 여러 개의 자원을 선언할 수 있다.
        try(InputStream in = new FileInputStream(src);
            OutputStream os = new FileOutputStream(dst)) {
            byte [] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                os.write(buf, 0, n);
            }
        }
    }

    // try-with-resources 를 catch 절과 함께 쓰는 모습
    static String firstLineOffline(String path, String defaultVal) {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }

    public static void main(String[] args) throws IOException {
        String src = args[0];
        String dst = args[1];
        copy(src, dst);
    }
}
