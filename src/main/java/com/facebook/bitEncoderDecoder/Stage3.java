package com.facebook.bitEncoderDecoder;

import com.facebook.bitEncoderDecoder.exception.InputNotEncodedCorrectly;

import java.util.*;

public class Stage3 {


    public String decode(String input) {
        InputValidator.validateInput(input);
        String[] arrayWithChars = Utils.prepareSegmentedInput(input);
        StringBuilder decodedInput = new StringBuilder();
        for (int i = 0; i < arrayWithChars.length; i++) {
            char doubledChar = getDoubledChar(arrayWithChars[i]);
            decodedInput.append(doubledChar);
        }

        return decodedInput.toString();
    }

    private char getDoubledChar(String tripled) {
        char doubledChar = ' ';
        Set<Character> mapWithChars = new HashSet<>();
        for (int i = 0; i < tripled.length(); i++) {
            if (mapWithChars.contains(tripled.charAt(i))) {
                return tripled.charAt(i);
            } else {
                mapWithChars.add(tripled.charAt(i));
            }
        }
        if (doubledChar == ' ') {
            throw new InputNotEncodedCorrectly();
        }
        return doubledChar;
    }

}
