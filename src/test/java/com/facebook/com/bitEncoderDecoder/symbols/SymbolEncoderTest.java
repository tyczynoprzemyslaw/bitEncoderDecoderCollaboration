package com.facebook.com.bitEncoderDecoder.symbols;

import com.facebook.bitEncoderDecoder.symbols.SymbolEncoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SymbolEncoderTest {

    SymbolEncoder symbolEncoder = new SymbolEncoder();

    @Test
    public void shouldEncodeGivenEmptyReturnEmpty() {
        // given
        String input = "";
        String expected = "";

        // when
        String actual = symbolEncoder.encode(input);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeGivenStringReturnStringTripled() {
        // given
        String input = "abc";
        String expected = "aaabbbccc";

        // when
        String actual = symbolEncoder.encode(input);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeGivenSpecialCharsReturnStringTripled() {
        // given
        String input = ";156!.";
        String expected = ";;;111555666!!!...";

        // when
        String actual = symbolEncoder.encode(input);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeGivenNullThrowIllegalArgumentException() {
        // given
        String input = null;

        // then
        assertThrows(IllegalArgumentException.class, () -> symbolEncoder.encode(input));
    }

    @Test
    public void shouldEncodeGivenStringReturnTripleLengthString() {
        // given
        String input = "Ala ma kota a kot ma Alę";

        // when
        String actual = symbolEncoder.encode(input);

        // then
        assertEquals(input.length(), actual.length() / SymbolEncoder.MULTIPLICATION_FACTOR);
    }

    @Test
    public void shouldEncodeGivenSpaceReturnEmpty() {
        // given
        String input = " ";
        String expected = "";

        // when
        String actual = symbolEncoder.encode(input);

        // then
        assertEquals(expected, actual);
    }
}
