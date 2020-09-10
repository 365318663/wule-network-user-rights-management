package cn.itcast.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassWordEncodingUtils {
    static  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}
