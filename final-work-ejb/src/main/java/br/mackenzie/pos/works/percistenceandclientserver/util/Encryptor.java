package br.mackenzie.pos.works.percistenceandclientserver.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    private static final String ENCRYPTION_ALGORITHM = "MD5";

    public static String encrypt(String unencryptedString) {
        try {
            MessageDigest digest;
            digest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            return new BigInteger(1, digest.digest(unencryptedString.getBytes())).toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Invalid encryption algorithm.", ex);
        }
    }
}