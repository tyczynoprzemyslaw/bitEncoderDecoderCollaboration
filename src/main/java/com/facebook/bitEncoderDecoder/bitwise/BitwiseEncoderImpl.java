package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Encoder;

public class BitwiseEncoderImpl implements Encoder {
    public static final int MULTIPLICATION_FACTOR = 2;

    @Override
    public String encode(String input) {
        return null;
    }


    private String charHexToBin(Character ch) {
        String bin = Integer.toBinaryString(ch);
        StringBuilder result = new StringBuilder();
        if (bin.length() == 6) {
            return result.append("00").append(bin).toString();
        } else if (bin.length() == 8) {
            return result.append(bin).toString();
        }
        return result.append("0").append(bin).toString();
    }

    private String getBinaryString(String input) {
        try {
            return Integer.toBinaryString(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            StringBuilder binary = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                binary.append(charHexToBin(input.charAt(i)));
            }
            return binary.toString();
        }
    }

    private String checkIfEven(String triplet) {
        StringBuilder result = new StringBuilder(triplet);
        int sum = 0;
        for (int i = 0; i < triplet.length(); i++) {
            sum += Integer.parseInt(String.valueOf(triplet.charAt(i)));
        }
        if (sum % 2 == 0) {
            result.append("0");

        } else {
            result.append("1");
        }
        while (result.length() < 4) {
            result.append('0');
        }
        return result.toString();
    }

    private String[] getTriplets(String input) {
        int numberOfTriplets = (input.length() / 3) + 1;
        int lastPlace = numberOfTriplets - 1;
        if (input.isBlank()) {
            return new String[0];
        }

        String[] triplets = new String[numberOfTriplets];

        StringBuilder source = new StringBuilder(input);
        int counter = 0;
        while (source.length() >= 3) {
            triplets[counter++] = source.substring(0, 3);
            source.delete(0, 3);
        }
        triplets[lastPlace] = source.toString();
        return triplets;
    }

    private String doubleTriplet(String input) {
        StringBuilder encodeString = new StringBuilder();
        if (input == null) {
            throw new IllegalArgumentException();
        } else if (input.equalsIgnoreCase(" ")) {
            return "";
        }
        for (int i = 0; i < input.length(); i++) {
            String multipliedChar = String.valueOf(input.charAt(i)).repeat(MULTIPLICATION_FACTOR);
            encodeString.append(multipliedChar);
        }
        return encodeString.toString();
    }

}
