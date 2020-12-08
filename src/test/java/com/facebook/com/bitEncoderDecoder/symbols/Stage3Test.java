package com.facebook.com.bitEncoderDecoder.symbols;

import com.facebook.bitEncoderDecoder.exception.InputNotEncodedCorrectly;
import com.facebook.bitEncoderDecoder.symbols.Stage1;
import com.facebook.bitEncoderDecoder.symbols.Stage3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Stage3Test {

    Stage3 stage3 = new Stage3();

    @DisplayName("Should encode() work with correct inputs")
    @ParameterizedTest
    @MethodSource("encodeArgumentsProvider")
    void encode(String expected, String given) {
        assertEquals(expected, stage3.decode(given));
    }

    private static Stream<Arguments> encodeArgumentsProvider() {
        return Stream.of(
                Arguments.of("abc", "aalbobxcc"),
                Arguments.of("test", ";tteXessSTtt"),
                Arguments.of("k12", "Lkk1102_2")
        );
    }

    @DisplayName("Should encode() return String shorter by a multiplication (encoding) factor")
    @ParameterizedTest
    @ValueSource(strings = {"AArrBB_CC", ",., P Aaal]leek", ";CChthrraazzPąąsSs1zzcc0zZz!?!"})
    void encodeShortensInput(String source) {
        int expectedLength = source.length() / Stage1.MULTIPLICATION_FACTOR;
        assertEquals(expectedLength, stage3.decode(source).length());
    }

    @DisplayName("Should encode() throw exception when input is not encoded correctly")
    @ParameterizedTest
    @ValueSource(strings = {"AcrrBB_CC", ",., P Afal]ldek", ";CChthrraazzPgąsSs1zzcc0zZz!?!"})
    void encodeThrowException(String source) {
        assertThrows(InputNotEncodedCorrectly.class, () -> {
            stage3.decode(source);
        });
    }
}
