package com.facebook.bitEncoderDecoder.bitwise.rawBitService;

import com.facebook.bitEncoderDecoder.exception.InputCorruptedException;
import com.facebook.bitEncoderDecoder.exception.InputNotSentCorrectlyException;

public class RawBitService {

    public String decode(String input) {
        char[] chars = input.toCharArray();
        char[] result = new char[chars.length];

        int bitCounter = 7;
        for (int i = 0; i < chars.length; i++) {
            int currentByte = decodeChar(chars[i]);
            if (bitCounter > 1) {
                for (int j = 0; j < 3; j++) {
                    setBit(result[i], bitCounter--, getBit(currentByte, 7 - j));
                }

            }
            result[i] = decodeChar(chars[i]);
        }
        return String.valueOf(result);
    }

    public char decodeChar(char symbol) {
        int source = getFirstByte(symbol);
        checkForTransmissionErrors(source);
        symbol = removeNoise(symbol);
        symbol = rearrangeSignificantBits(symbol);
        return symbol;
    }

    public char removeNoise(char symbol) {
        int noisePosition = -1;
        int result = symbol;
        int correctedBit = 0;
        for (int i = 0; i < 8; i += 2) {
            int currentBit = getBit(symbol, 7 - i);
            if (isBitPairOdd(symbol, i)) {
                noisePosition = i;
            } else {
                correctedBit ^= currentBit;
            }
        }
        if (noisePosition < 0) {
            throw new InputNotSentCorrectlyException();
        }
        result = setZerosBitPair(result, noisePosition);
        if (correctedBit == 1) {
            result = setOnesBitPair(result, noisePosition);
        }
        return (char) result;
    }

    public int setOnesBitPair(int number, int index) {
        number = setBit(number, 7 - index, 1);
        number = setBit(number, 7 - index - 1, 1);
        return number;
    }

    public int setZerosBitPair(int number, int index) {
        number = setBit(number, 7 - index, 0);
        number = setBit(number, 7 - index - 1, 0);
        return number;
    }

    public int getFirstByte(char symbol) {
        return symbol & 0b11111111;
    }

    public void checkForTransmissionErrors(int source) {
        int countErrors = 0;
        for (int i = 0; i < 8; i += 2) {
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
        int firstBit = getBit(source, 7 - counter);
        int secondBit = getBit(source, 7 - counter - 1);
        return firstBit != secondBit;
    }

    private char rearrangeSignificantBits(char symbol) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            int currentBit = getBit(symbol, 7 - i * 2);
            result = setBit(result, 7 - i, currentBit);
        }
        return (char) result;
    }

    public int getBit(int source, int position) {
        return (source >> position) & 1;
    }

    public int setBit(int source, int position, int bit) {
        int bitmask = 0b11111111 ^ (1 << position);
        source = source & bitmask;
        return source | (bit << position);
    }
}
