package com.facebook.bitEncoderDecoder.exception;

public class InputNotSentCorrectlyException extends RuntimeException {
    public InputNotSentCorrectlyException() {
        super("None transmission errors detected. Transmitter fails.");
    }
}
