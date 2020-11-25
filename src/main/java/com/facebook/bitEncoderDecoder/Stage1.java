package com.facebook.bitEncoderDecoder;

public class Stage1 {

    public static final int MULTIPLICATION_FACTOR = 3;

    public String encode(String input) {
        StringBuilder encodeString = new StringBuilder();
        if (input == null) {
            throw new IllegalArgumentException();
        } else if (input.equalsIgnoreCase(" ")) {
            return "";
        }
        for (int i = 0; i < input.length(); i++) {
            String multipliedChar = String.valueOf(input.charAt(i)).repeat(MULTIPLICATION_FACTOR);
            encodeString.append(multipliedChar);

        }
        return encodeString.toString();
    }

}
