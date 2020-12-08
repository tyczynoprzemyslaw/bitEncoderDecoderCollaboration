package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Encoder;

public class RawBitwiseEncoderImpl implements Encoder {

    @Override
    public String encode(String input) {
        if (input == null || input.isBlank()){
            return "";
        }
        return encodeChars(input);
    }

    private String encodeChars(String input) {
        char[] chars = input.toCharArray();

        return String.valueOf(chars);
    }
}
