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
}
