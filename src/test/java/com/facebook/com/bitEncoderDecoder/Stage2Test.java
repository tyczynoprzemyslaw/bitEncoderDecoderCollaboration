package com.facebook.com.bitEncoderDecoder;

import com.facebook.bitEncoderDecoder.Stage2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Stage2Test {

    Stage2 stage2 = new Stage2();

    @Test
    public void shouldSendGivenEmptyReturnEmpty() {
        // given
        String input = "";
        String outputString = "";

        // when
        String actual = stage2.send(input);

        // then
        assertEquals(outputString, actual);
    }


    @Test
    public void shouldSendGivenStringReturnStringCoded() {
        // given
        String inputString = "aaabbbccc";
        String outputString = stage2.send(inputString);
        List<String> strings = new ArrayList<>();
        List<String> outputStringsOf3Chars = new ArrayList<>();
        int index = 0;
        boolean result = true;

        // when
        while (index < inputString.length()) {
            strings.add(inputString.substring(index, Math.min(index + 3, inputString.length())));
            outputStringsOf3Chars.add(outputString.substring(index, Math.min(index + 3, inputString.length())));
            index += 3;
        }
        for (int i = 0; i < strings.size(); i++) {
            int checker = 0;
            if (strings.get(i).equals(outputStringsOf3Chars.get(i))) {
                result = false;
                continue;
            }

            for (int j = 0; j < 3; j++) {
                if ((strings.get(i)).charAt(j) == (outputStringsOf3Chars.get(i)).charAt(j)) {
                    checker++;
                }
                if (j == 2 && checker < 2) {
                    result = false;
                }
            }

        }

        // then
        assertTrue(result);
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

    @Test
    public void shouldInputLengthBeDivisibleBy3() {
        // given
        String input = "xuxpiplkk";

        // when
        boolean result = input.length() % 3 == 0;

        // then
        assertTrue(result);
    }


    @Test
    public void shouldSendGivenEmptyThrowIllegalArgumentException() {
        // given
        String input = " ";

        // then
        assertThrows(IllegalArgumentException.class, () -> stage2.send(input));
    }


}
