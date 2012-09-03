package me.vgv.requestlog;

import java.util.Random;

/**
 * @author Vasily Vasilkov (vgv@vgv.me)
 */
public final class KeysGenerator {

    private static final Random rnd = new Random();
    private static final String ALPHABET = "23456789abcdefghijkmnpqrstuvwxyz";
    private static final int ALPHABET_LENGTH = ALPHABET.length();

    public static String generateUniqueKey(int length) {
        StringBuilder key = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char nextChar = ALPHABET.charAt(rnd.nextInt(ALPHABET_LENGTH));
            key.append(nextChar);
        }
        return key.toString();
    }

}
