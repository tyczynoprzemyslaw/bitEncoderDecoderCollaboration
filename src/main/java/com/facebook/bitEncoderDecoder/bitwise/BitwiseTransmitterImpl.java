package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.utils.RandomProvider;
import com.facebook.bitEncoderDecoder.utils.RandomProviderImpl;

public class BitwiseTransmitterImpl implements Transmitter {

    private final RandomProvider randomProvider;

    public BitwiseTransmitterImpl(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    @Override
    public String send(String input) {
        return null;
    }
}
