package com.facebook.bitEncoderDecoder.exception;

public class InputNotEncodedCorrectlyException extends RuntimeException {
    public InputNotEncodedCorrectlyException() {
        super("Input hasn't been encoded correctly");
    }
}
