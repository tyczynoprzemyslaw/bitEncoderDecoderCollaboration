package com.facebook.bitEncoderDecoder.exception;

public class InputCorruptedException extends RuntimeException {
    public InputCorruptedException() {
        super("There are too many transmission errors. Decoding impossible.");
    }
}
