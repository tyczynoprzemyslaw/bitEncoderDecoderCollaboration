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
        if (input.isBlank()) {
            return "";
        }
        char[] array = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            array[i] = mutate(input.charAt(i));
        }
        return String.valueOf(array);
    }

    private char mutate(char c) {
        char reduced = (char) (c & 0b11111111);
        return (char) (reduced ^ (0b1 << 7 - randomProvider.getRandom(8)));
    }
}
