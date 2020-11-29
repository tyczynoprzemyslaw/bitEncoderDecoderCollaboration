package com.facebook.bitEncoderDecoder;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static int getRandomInRange(int fromInclusive, int toInclusive){
        return ThreadLocalRandom.current().nextInt(fromInclusive, toInclusive + 1);
    }

    public static char randomizeChar(char given){
        int random = 0;
        while (random == 0){
            random = getRandomInRange(-10, 10);
        }
        return (char) (given + random);
    }

    public static String[] prepareSegmentedInput(String input) {
        if (input.isBlank()) {
            return new String[0];
        }

        int numberOfTriplets = (int) Math.round(input.length() / 3.0);
        String[] target = new String[numberOfTriplets];

        StringBuilder source = new StringBuilder(input);
        int counter = 0;
        while (source.length() >= 3) {
            target[counter++] = source.substring(0, 3);
            source.delete(0, 3);
        }
        return target;
    }

}
