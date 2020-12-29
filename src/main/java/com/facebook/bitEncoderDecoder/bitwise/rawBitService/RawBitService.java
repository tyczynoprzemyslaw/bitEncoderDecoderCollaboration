package com.facebook.bitEncoderDecoder.bitwise.rawBitService;

import com.facebook.bitEncoderDecoder.exception.InputCorruptedException;
import com.facebook.bitEncoderDecoder.exception.InputNotSentCorrectlyException;

public class RawBitService {

    public String decode(String input) {
        char[] chars = input.toCharArray();
        char[] result = new char[chars.length];

        for (int i = 0; i < chars.length; i++) {
            result[i] = decodeChar(chars[i]);
        }
        return String.valueOf(result);
    }

    public char decodeChar(char symbol) {
        int source = getFirstByte(symbol);
        checkForTransmissionErrors(source);
        symbol = removeNoise(symbol);
        return symbol;
    }

    public char removeNoise(char symbol) {
        int noisePosition = -1;
        int result = 0;
        int correctedBit = 0;
        for (int i = 0; i < 8; i += 2) {
            int currentBit = symbol >> (7 - i) & 1;
            if (isBitPairOdd(symbol, i)) {
                noisePosition = i / 2;
            } else {
                correctedBit ^= currentBit;
            }
            result = result | (currentBit << (i / 2));
        }
        if (noisePosition < 0) {
            throw new InputNotSentCorrectlyException();
        }
        if (noisePosition < 3) {
            result = result | (correctedBit << noisePosition);
        }
        return (char) result;
    }

    public int getFirstByte(char symbol) {
        return symbol & 0b11111111;
    }

    public void checkForTransmissionErrors(int source) {
        int countErrors = 0;
        for (int i = 0; i < 8 && countErrors < 1; i += 2) {
            if (isBitPairOdd(source, i)) {
                countErrors++;
            }
        }
        if (countErrors == 0) {
            throw new InputNotSentCorrectlyException();
        }
        if (countErrors > 1) {
            throw new InputCorruptedException();
        }
    }

    private boolean isBitPairOdd(int source, int counter) {
        int firstBit = (source >> counter) & 1;
        int secondBit = (source >> counter + 1) & 1;
        return firstBit != secondBit;
    }
}
