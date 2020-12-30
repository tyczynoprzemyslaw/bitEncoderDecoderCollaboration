package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.utils.RandomProvider;

public class BitwiseTransmitterImpl implements Transmitter {

    private final RandomProvider randomProvider;

    public BitwiseTransmitterImpl(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    @Override
    public String send(String input) {
        if (input.isBlank()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        String[] chars = new String[input.length()];
        for (int i = 0; i < input.length(); i++) {
            chars[i] = CharToBinConverter.charHexToBin(input.charAt(i));
        }
        for (String str : chars) {
            int indexToChange = getIndexToChange(str);
            String stringOfChar = convertCharArrToString(changeElement(str.toCharArray(), indexToChange));
            result.append(convertBinToHexChar(stringOfChar));
        }

        return result.toString();
    }



    private int getIndexToChange(String binary) {
        return randomProvider.getRandom(0, binary.length());
    }

    private char[] changeElement(char[] chars, int indexToChange) {
        if (chars[indexToChange] == '1') {
            chars[indexToChange] = '0';
        } else {
            chars[indexToChange] = '1';
        }
        ;
        return chars;
    }
    private String convertCharArrToString(char[] chars) {
        StringBuilder result = new StringBuilder();
        for (Character ch : chars) {
            result.append(ch);
        }
        return result.toString();
    }

    private String convertBinToHexChar(String string) {
        int charCode = Integer.parseInt(string, 2);
        return Character.toString((char) charCode);
    }

}
