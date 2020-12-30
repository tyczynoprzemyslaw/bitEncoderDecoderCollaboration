package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Decoder;
import com.facebook.bitEncoderDecoder.exception.InputCorruptedException;
import com.facebook.bitEncoderDecoder.exception.InputNotSentCorrectlyException;

public class BitwiseDecoderImpl implements Decoder {


    @Override
    public String decode(String input) {
        StringBuilder result = new StringBuilder();
        String[] binaryChars = getStrings(input);
        StringBuilder repairedBinary = new StringBuilder();
        for (String binary : binaryChars) {
            int indexOfPair = 0;
            StringBuilder source = new StringBuilder(binary);
            String[] pairs = new String[4];


            while (source.length() >= 2) {
                String pair = source.substring(0, 2);
                source.delete(0, 2);
                pairs[indexOfPair] = pair;
                indexOfPair++;
            }

            checkIfInputEncodedCorrectly(pairs);

            int noisedIndex = getNoisedIndex(pairs);
            if (noisedIndex > 3) {
                throw new InputNotSentCorrectlyException();
            }

            pairs = repairPairs(noisedIndex, pairs);
            repairedBinary.append(pairs[0].substring(1)).append(pairs[1].substring(1)).append(pairs[2].substring(1));
        }

        while (repairedBinary.length() > 8) {
            String binaryChar = repairedBinary.substring(0, 8);
            result.append(convertBinToHexChar(binaryChar));
            repairedBinary.delete(0, 8);
        }

        return result.toString();
    }


    private boolean checkIfBitIsCorrect(String pair) {
        int firstChar = 0;
        int secondChar = 1;
        return pair.charAt(firstChar) == pair.charAt(secondChar);
    }


    private String[] getStrings(String input) {
        String[] chars = new String[input.length()];
        for (int i = 0; i < input.length(); i++) {
            chars[i] = CharToBinConverter.charHexToBin(input.charAt(i));
        }
        return chars;
    }

    private int getNoisedIndex(String[] pairs) {
        int noisedPair = 0;
        for (String pair : pairs) {
            if (checkIfBitIsCorrect(pair)) {
                noisedPair++;
            } else {
                return noisedPair;
            }
        }
        return noisedPair;
    }


    private String[] repairPairs(int noisedPair, String[] pairs) {
        String firstPair = pairs[0];
        String secondPair = pairs[1];
        String thirdPair = pairs[2];
        String parityPair = pairs[3];

        int sum = 0;

        int firstPairValue = Integer.parseInt(String.valueOf(firstPair.charAt(0)));
        int secondPairValue = Integer.parseInt(String.valueOf(secondPair.charAt(0)));
        int thirdPairValue = Integer.parseInt(String.valueOf(thirdPair.charAt(0)));

        switch (noisedPair) {
            case 0 -> {
                sum = secondPairValue + thirdPairValue;
                pairs[0] = repairPair(sum, parityPair);
            }
            case 1 -> {
                sum = firstPairValue + thirdPairValue;
                pairs[1] = repairPair(sum, parityPair);
            }
            case 2 -> {
                sum = firstPairValue + secondPairValue;
                pairs[2] = repairPair(sum, parityPair);
            }
        }
        return pairs;
    }

    private String repairPair(int sum, String parityPair) {
        StringBuilder repairedPair = new StringBuilder();
        int parity = Integer.parseInt(String.valueOf(parityPair.charAt(0)));
        if (sum % 2 == parity) {
            repairedPair.append("00");
        } else {
            repairedPair.append("11");
        }
        return repairedPair.toString();
    }

    private String convertBinToHexChar(String string) {
        int charCode = Integer.parseInt(string, 2);
        return Character.toString((char) charCode);
    }

    private void checkIfInputEncodedCorrectly(String[] pairs) {
        int numberOfNoisedBits = 0;
        for (String pair : pairs) {
            if (!checkIfBitIsCorrect(pair)) {
                numberOfNoisedBits++;
            }
            if (numberOfNoisedBits > 1) {
                throw new InputCorruptedException();
            }
        }
    }
}
