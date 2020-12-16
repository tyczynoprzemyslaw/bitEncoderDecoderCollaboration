package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.utils.RandomProvider;
import com.facebook.bitEncoderDecoder.utils.RandomProviderImpl;

import java.util.Arrays;

public class BitwiseTransmitterImpl implements Transmitter {

    private final RandomProvider randomProvider;

    public BitwiseTransmitterImpl(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    @Override
    public String send(String input) {
        if (input.isBlank()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        String[] chars = new String[input.length()];


        return null;
    }

    private String charHexToBin(Character ch) {
        String bin = Integer.toBinaryString(ch);
        StringBuilder result = new StringBuilder();
        if (bin.length() == 8) {
            return result.append(bin).toString();
        } else {
            int desiredLength = 8;
            int missingZeros = desiredLength - bin.length();
            for (int i = 0; i < missingZeros; i++) {
                result.append("0");
            }
        }
        result.append(bin);
        return result.toString();
    }


}
