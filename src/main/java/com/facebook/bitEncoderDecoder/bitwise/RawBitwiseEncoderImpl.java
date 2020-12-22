package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Encoder;

public class RawBitwiseEncoderImpl implements Encoder {
    private char[] outputMessage;

    @Override
    public String encode(String input) {
        if (input == null || input.isEmpty()){
            return "";
        }
        return encodeChars(input);
    }

    private String encodeChars(String input) {
        char[] inputMessage = input.toCharArray();

        int leftover = 0;
        for (int i = 0; i < inputMessage.length; i++) {
            int outputCharAsInt;
            int position = 7 - leftover;
            leftover = 0;

            while (position >= 0) {
                int[] triple = new int[3];
                for (int j = 0; j < 3; j++) {
                    if (position >= 0) {
                        triple[j] = extractBit(inputMessage[i], position);
                    } else {
                        if (i < inputMessage.length - 1){
                            triple[j] = extractBit(inputMessage[i + 1], 7 - leftover);
                            leftover++;
                        }

                    }
                    position--;
                }
                int[] bits = fillParityBit(triple);
                outputCharAsInt = doubleBits(bits);
                addCharToMessage((char) outputCharAsInt);
            }
        }
        return String.valueOf(outputMessage);
    }

    private int extractBit(char symbol, int position) {
        return (symbol >> position) & 1;
    }

    private int doubleBits(int[] binaryArray) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            result = result | (binaryArray[i / 2] << 7 - i);
        }
        return result;
    }

    private int[] fillParityBit(int[] binaryArray) {
        int[] temp = new int[binaryArray.length + 1];
        System.arraycopy(binaryArray, 0, temp, 0, binaryArray.length);
        temp[3] = temp[0] ^ temp[1] ^ temp[2];
        return temp;
    }

    private void addCharToMessage(char symbol) {
        if (outputMessage == null) {
            outputMessage = new char[0];
        }
        char[] temp = new char[outputMessage.length + 1];
        System.arraycopy(outputMessage, 0, temp, 0, outputMessage.length);
        temp[outputMessage.length] = symbol;
        outputMessage = temp;
    }
}
