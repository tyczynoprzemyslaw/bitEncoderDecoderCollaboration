package com.facebook.com.bitEncoderDecoder;

import com.facebook.bitEncoderDecoder.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {

    @Test
    public void shouldGetRandomInRangeReturnNumbersWithinBounds(){
        int loops = 10_000;
        int LOWER = 0;
        int UPPER = 20;

        while(--loops > 0){
            System.out.println(Utils.getRandomInRange(LOWER, UPPER));
            assertTrue(Utils.getRandomInRange(LOWER, UPPER) <= UPPER);
        }
    }
}
