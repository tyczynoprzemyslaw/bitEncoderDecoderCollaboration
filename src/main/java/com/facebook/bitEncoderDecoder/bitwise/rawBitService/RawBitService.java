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
        return symbol;
    }

    public int getFirstByte(char symbol) {
        return symbol & 0b11111111;
    }

    public void checkForTransmissionErrors(int source) {
        int countErrors = 0;
        for (int i = 0; i < 8 && countErrors < 1; i += 2) {
            int firstBit = (source >> i) & 1;
            int secondBit = (source >> i + 1) & 1;
            if (firstBit != secondBit) {
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
}
