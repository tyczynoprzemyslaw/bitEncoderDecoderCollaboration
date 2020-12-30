package com.facebook.bitEncoderDecoder.bitwise;

public class CharToBinConverter {

    public static String charHexToBin(Character ch) {
        String bin = Integer.toBinaryString(ch);
        StringBuilder result = new StringBuilder();
        if (bin.length() == 8) {
            return result.append(bin).toString();
        } else {
            int desiredLength = 8;
            int missingZeros = desiredLength - bin.length();
            for (int i = 0; i < missingZeros; i++) {
                result.append("0");
            }
        }
        result.append(bin);
        return result.toString();
    }
}
