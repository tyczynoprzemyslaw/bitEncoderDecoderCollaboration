package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Encoder;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RawBitwiseEncoderImpl implements Encoder {

    private char[] outputMessage;

    @Override
    public String encode(String input) {
        if (input == null || input.isBlank()){
            return "";
        }
        return encodeChars(input);
    }

    private String encodeChars(String input) {
        char[] inputMessage = input.toCharArray();

        int leftover = 0;
        for (int i = 0; i < inputMessage.length; i++) {
            int outputCharAsInt = 0;
            int position = 7 - leftover;

            while (position > 0) {
                int[] triple = new int[3];
                for (int j = 0; j < 3; j++) {
                    if (position >= 0) {
                        triple[j] = extractBit(inputMessage[i], position);
                    } else if (i < inputMessage.length - 1){
                        triple[j] = extractBit(inputMessage[i+1], 8- position);
                        leftover++;
                    }
                    position--;
                }
                int[] bits = fillParityBit(triple);
                outputCharAsInt = doubleBits(bits);

                addCharToMessage((char) outputCharAsInt);
                System.out.println(input + " i: " + i + " triple: " + Arrays.toString(triple));
                System.out.println(Integer.toBinaryString(outputCharAsInt));
            }
        }

        return String.valueOf(outputMessage);
    }

    @Test
    public void shouldExtractBitWork() {
        char[] sourceChars = {(char) 0b0010_0110 /* & */, (char) 0b0100_0110 /* F */, (char) 0b0100_0110 /* F */, (char) 0b0100_0110 /* F */};
        int[] sourcePointers = {7, 6, 2, 1};
        int[] expectedResults = {0, 1, 1, 1};

        int counter = 0;
        for (char ignored : sourceChars) {
            assertEquals(expectedResults[counter], extractBit(sourceChars[counter], sourcePointers[counter]));
            counter++;
        }
    }

    private int extractBit(char symbol, int position) {
        return (symbol >> position) & 1;
    }

    @Test
    public void shouldDoubleBitsWork() {
        int[][] sourceBits = {
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 0, 1}
        };
        int[] expected = {
                0b1111_0000,
                0b0000_0000,
                0b1111_1111,
                0b0011_0011
        };
        int counter = 0;
        for (int ignored : expected) {
            assertEquals(expected[counter], doubleBits(sourceBits[counter]));
            counter++;
        }
    }

    private int doubleBits(int[] binaryArray) {
        int output = 0;
        for (int i = 0; i < 8; i++) {
            output = output | (binaryArray[i / 2] << 7 - i);
        }
        return output;
    }

    @Test
    public void shouldFillParityBitWork() {
        int[][] sourceBits = {
                {0, 0, 1},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        int[][] expectedBits = {
                {0, 0, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };
        int counter = 0;
        for (int[] ignored : sourceBits) {
            assertArrayEquals(expectedBits[counter], fillParityBit(sourceBits[counter++]));
        }
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
