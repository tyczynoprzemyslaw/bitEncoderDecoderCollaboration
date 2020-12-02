package com.facebook.bitEncoderDecoder.service;

import com.facebook.bitEncoderDecoder.app.Decoder;
import com.facebook.bitEncoderDecoder.app.Encoder;
import com.facebook.bitEncoderDecoder.app.Transmitter;

public interface SendingBox extends Encoder, Transmitter, Decoder {
    String operate(String input);
}
