package com.facebook.com.bitEncoderDecoder.symbols;

import com.facebook.bitEncoderDecoder.symbols.SymbolTransmitter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SymbolTransmitterTest {

    SymbolTransmitter symbolTransmitter = new SymbolTransmitter();

    @Test
    public void shouldSendGivenEmptyReturnEmpty() {
        // given
        String input = "";
        String outputString = "";

        // when
        String actual = symbolTransmitter.send(input);

        // then
        assertEquals(outputString, actual);
    }


    @Test
    public void shouldSendGivenStringReturnStringCoded() {
        // given
        String inputString = "aaabbbccc";

        // when
        String actual = symbolTransmitter.send(inputString);
        boolean hasNoise = true;

        for (int i = 0; i < actual.length() && hasNoise; i += 3){
            char charPos0 = actual.charAt(i);
            char charPos1 = actual.charAt(i + 1);
            char charPos2 = actual.charAt(i + 2);

            if (charPos0 == charPos1) {
                if (charPos2 == charPos0) {
                    hasNoise = false;
                }
            } else {
                if ((charPos0 != charPos2) && (charPos1 != charPos2)){
                    hasNoise = false;
                }
            }
        }

        // then
        assertTrue(hasNoise);
    }

    @Test
    public void shouldSendGivenNullThrowIllegalArgumentException() {
        // given
        String input = null;

        // then
        assertThrows(IllegalArgumentException.class, () -> symbolTransmitter.send(input));
    }

    @Test
    public void shouldSendGivenStringReturnTheSameLengthString() {
        // given
        String input = "xuxpiplkk";

        // when
        String actual = symbolTransmitter.send(input);

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
    public void shouldOutputLengthBeDivisibleBy3(){
        // given
        String input = "aaaiiioooppp";
        int expected = input.length();

        // when
        int actual = symbolTransmitter.send(input).length();

        // then
        assertEquals(expected, actual);

    }


    @Test
    public void shouldSendGivenEmptyThrowIllegalArgumentException() {
        // given
        String input = " ";

        // then
        assertThrows(IllegalArgumentException.class, () -> symbolTransmitter.send(input));
    }


}
