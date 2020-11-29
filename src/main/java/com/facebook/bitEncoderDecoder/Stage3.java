package com.facebook.bitEncoderDecoder;

import java.util.*;

public class Stage3 {


    public  String decode(String input) {
        InputValidator.validateInput(input);
        String[] arrayWithChars = Utils.prepareSegmentedInput(input);
        StringBuilder decodedInput = new StringBuilder();
        for (int i = 0; i < arrayWithChars.length; i++) {
            char doubledChar = doubledChar(arrayWithChars[i]);
            decodedInput.append(doubledChar);
        }

        return decodedInput.toString();
    }

    private  char doubledChar(String trippled) {
        char doubledChar = ' ';
        Set<Character> mapWithChars = new HashSet<>();
        for (int i = 0; i < trippled.length(); i++) {
            if (mapWithChars.contains(trippled.charAt(i))) {
                return trippled.charAt(i);
            } else {
                mapWithChars.add(trippled.charAt(i));
            }
        }
        return doubledChar;
    }

}
