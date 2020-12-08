package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Encoder;

public class BitwiseEncoderImpl implements Encoder {

    @Override
    public String encode(String input) {
        return null;
    }


    private String charHexToBin(Character ch) {
        String bin = Integer.toBinaryString(ch);
        StringBuilder result = new StringBuilder();
        if (bin.length() == 6) {
            return result.append("00").append(bin).toString();
        } else if (bin.length() == 8) {
            return result.append(bin).toString();
        }
        return result.append("0").append(bin).toString();
    }
}
