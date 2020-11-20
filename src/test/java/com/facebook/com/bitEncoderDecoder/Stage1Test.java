package com.facebook.com.bitEncoderDecoder;

import com.facebook.bitEncoderDecoder.Stage1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Stage1Test {

    Stage1 stage1 = new Stage1();

    @Test
    public void shouldEncodeGivenEmptyReturnEmpty() {
        // given
        String input = "";
        String expected = "";

        // when
        String actual = stage1.encode(input);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeGivenStringReturnStringTripled() {
        // given
        String input = "abc";
        String expected = "aaabbbccc";

        // when
        String actual = stage1.encode(input);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeGivenSpecialCharsReturnStringTripled() {
        // given
        String input = ";156!.";
        String expected = ";;;111555666!!!...";

        // when
        String actual = stage1.encode(input);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeGivenNullThrowIllegalArgumentException() {
        // given
        String input = null;

        // then
        assertThrows(IllegalArgumentException.class, () -> stage1.encode(input));
    }

    @Test
    public void shouldEncodeGivenStringReturnTripleLengthString() {
        // given
        String input = "Ala ma kota a kot ma Alę";

        // when
        String actual = stage1.encode(input);

        // then
        assertEquals(input.length(), actual.length() / 2);
    }

    @Test
    public void shouldEncodeGivenSpaceReturnEmpty() {
        // given
        String input = " ";
        String expected = "";

        // when
        String actual = stage1.encode(input);

        // then
        assertEquals(expected, actual);
    }
}
