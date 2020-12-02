package com.facebook.bitEncoderDecoder.exception;

public class InputNotEncodedCorrectly extends RuntimeException {
    public InputNotEncodedCorrectly() {
        super("Input hasn't been encoded correctly");
    }
}
