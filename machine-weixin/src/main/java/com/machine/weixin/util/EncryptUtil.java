package com.machine.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

    private static final String ZERO = "0";
    private static final String ALGORITHM_MD5 = "MD5";
    private static final String ALGORITHM_SHA1 = "SHA-1";
    private static final String ALGORITHM_SHA256 = "SHA-256";


    public static String encrypt_MD5(String password) {
        return encrypt(ALGORITHM_MD5, password);
    }

    public static String encrypt_SHA1(String password) {
        return encrypt(ALGORITHM_SHA1, password);
    }

    public static String encrypt_SHA256(String password) {
        return encrypt(ALGORITHM_SHA256, password);
    }

    public static String encrypt(String algorithm, String password) {
        if (password == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(password.getBytes());
            return byte2hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append(ZERO);
            }
            sign.append(hex);
        }
        return sign.toString();
    }
}
