package com.facebook.com.bitEncoderDecoder;

import com.facebook.bitEncoderDecoder.Stage2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Stage2Test {

    Stage2 stage2 = new Stage2();

    @Test
    public void shouldSendGivenEmptyReturnEmpty() {
        // given
        String input = "";
        String expected = "";

        // when
        String actual = stage2.send(input);

        // then
        assertEquals(expected, actual);
    }

    @Disabled
    @Test
    public void shouldSendGivenStringReturnStringCoded() {
        // given
        String inputString = "aaabbbccc";
        String expected = "auatbblcc";
        int[] result = new int[expected.length() / 3];
        int[] expectedArray = new int[expected.length() / 3];

        // when
        for (int c : expectedArray) {
            c = 2;
        }
        for (int i = 0; i < inputString.length(); i += 3) {
            for (int j = i; j < inputString.length(); j++) {
                if (inputString.charAt(j) == expected.charAt(j)) {
                    result[i]++;
                }
            }
        }

        // then
        assertArrayEquals(result, expectedArray);
    }


    @Test
    public void shouldSendGivenNullThrowIllegalArgumentException() {
        // given
        String input = null;

        // then
        assertThrows(IllegalArgumentException.class, () -> stage2.send(input));
    }

    @Test
    public void shouldSendGivenStringReturnTheSameLengthString() {
        // given
        String input = "xuxpiplkk";

        // when
        String actual = stage2.send(input);

        // then
        assertEquals(input.length(), actual.length());
    }
/*  //i didn't know we should keep it
    @Test
    public void shouldSendGivenSpaceReturnEmpty() {
        // given
        String input = " ";
        String expected = "";

        // when
        String actual = stage1.encode(input);

        // then
        assertEquals(expected, actual);
    }

 */
}
