package com.facebook.bitEncoderDecoder;

public class Stage1 {

    public String encode(String input) {
        StringBuilder encodeString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            encodeString.append(input.charAt(i));
            encodeString.append(input.charAt(i));
            encodeString.append(input.charAt(i));
        }
        return encodeString.toString();
    }

}
