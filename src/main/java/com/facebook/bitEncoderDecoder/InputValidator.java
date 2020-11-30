package com.facebook.bitEncoderDecoder;

public class InputValidator {
    public static void validateInput(String input) {
        if (input == null){
            throw new IllegalArgumentException("input is null");
        }
        if (input.length() % 3 != 0){
            throw new IllegalArgumentException("input is not encoded correctly");
        }
    }
}
