package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.utils.RandomProvider;

public class RawBitwiseTransmitterImpl implements Transmitter {

    private final RandomProvider randomProvider;

    public RawBitwiseTransmitterImpl(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    @Override
    public String send(String input) {
        return null;
    }
}
