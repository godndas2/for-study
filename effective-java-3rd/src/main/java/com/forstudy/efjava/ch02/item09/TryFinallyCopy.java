package com.forstudy.efjava.ch02.item09;

import java.io.*;

/**
* @author halfdev
* @since 2020-02-03
* try-finally 보다는 try-with-resources 를 사용하라
*/
public class TryFinallyCopy {
    private static final int BUFFER_SIZE = 8 * 1024;

    // 자원이 둘 이상이면 try-finally 방식은 지저분
    static void copy(String src, String dst) throws IOException {
        InputStream inputStream = new FileInputStream(src);
        try {
            OutputStream os = new FileOutputStream(dst);
            try {
                byte[] bytes = new byte[BUFFER_SIZE];
                int n;
                while (( n = inputStream.read(bytes)) >= 0) {
                    os.write(bytes, 0, n);
                }
            } finally {
                os.close();
            }
        } finally {
            inputStream.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String src = args[0];
        String dst = args[1];
        copy(src,dst);
    }

}
