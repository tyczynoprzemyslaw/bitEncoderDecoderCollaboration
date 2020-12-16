package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Encoder;

public class BitwiseEncoderImpl implements Encoder {
    public static final int MULTIPLICATION_FACTOR = 2;

    @Override
    public String encode(String input) {
        StringBuilder result = new StringBuilder();
        String binary = getBinaryString(input);
        String[] triplets = getTriplets(binary);

        triplets = checkTripletsForParity(triplets);

        triplets = makeTripletDoubled(triplets);

        return convertBinToHexChar(triplets, result);
    }

    private String convertBinToHexChar(String[] strings, StringBuilder result) {
        for (String str : strings) {
            int charCode = Integer.parseInt(str, 2);
            String singleChar = Character.toString((char) charCode);
            result.append(singleChar);
        }
        return result.toString();
    }


    private String[] makeTripletDoubled(String[] triplets) {
        for (int i = 0; i < triplets.length; i++) {
            String temp = triplets[i];
            triplets[i] = doubleTriplet(temp);
        }
        return triplets;
    }

    private String[] checkTripletsForParity(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            String temp = strings[i];
            strings[i] = checkIfTripletEven(temp);
        }
        return strings;
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

    private String checkIfTripletEven(String triplet) {
        if (triplet.length() < 3) {
            StringBuilder newTriplet = new StringBuilder(triplet);
            while (newTriplet.length() < 3) {
                newTriplet.append("0");
            }
            triplet = newTriplet.toString();
        }
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
