package com.facebook.bitEncoderDecoder.app;

public interface SendingBox extends Encoder, Transmitter, Decoder{
    String operate(String input);
}
