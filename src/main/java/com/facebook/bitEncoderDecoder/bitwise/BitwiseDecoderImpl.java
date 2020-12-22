package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Decoder;

public class BitwiseDecoderImpl implements Decoder {


    @Override
    public String decode(String input) {

        String[] binaryChars = getStrings(input);
        return null;
    }


    private String[] getStrings(String input) {
        String[] chars = new String[input.length()];
        for (int i = 0; i < input.length(); i++) {
            chars[i] = CharToBinConverter.charHexToBin(input.charAt(i));
        }
        return chars;
    }

}
