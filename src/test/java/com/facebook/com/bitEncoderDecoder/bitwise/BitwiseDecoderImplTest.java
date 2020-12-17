package com.facebook.com.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Decoder;
import com.facebook.bitEncoderDecoder.bitwise.BitwiseDecoderImpl;
import com.facebook.bitEncoderDecoder.exception.InputCorruptedException;
import com.facebook.bitEncoderDecoder.exception.InputNotSentCorrectlyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BitwiseDecoderImplTest {

    Decoder decoder = new BitwiseDecoderImpl();

    @DisplayName("Should decode() follow proper algorithm")
    @ParameterizedTest
    @MethodSource("decodeArgumentsProvider")
    void decode(String input, String expected) {
        assertEquals(expected, decoder.decode(input));
    }

    private static Stream<Arguments> decodeArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("11011100", 2), //101P
                                        (char) Integer.parseInt("00010000", 2), //000P
                                        (char) Integer.parseInt("11110010", 2)  //110P
                                }
                        ),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("10100011", 2), //101P
                                }
                        )
                ),
                Arguments.of(
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("10111100", 2),
                                        (char) Integer.parseInt("10001100", 2),
                                        (char) Integer.parseInt("00100000", 2),
                                        (char) Integer.parseInt("11100000", 2),
                                        (char) Integer.parseInt("00111011", 2),
                                        (char) Integer.parseInt("11001000", 2),
                                        (char) Integer.parseInt("00110001", 2),
                                        (char) Integer.parseInt("00111101", 2),
                                        (char) Integer.parseInt("00111101", 2),
                                        (char) Integer.parseInt("11001110", 2),
                                        (char) Integer.parseInt("00000100", 2),
                                }
                        ),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("01110100", 2),
                                        (char) Integer.parseInt("01100101", 2),
                                        (char) Integer.parseInt("01010011", 2),
                                        (char) Integer.parseInt("01110100", 2)
                                }
                        )
                ),
                Arguments.of(
                        "",
                        ""
                ),
                Arguments.of(
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("00100000", 2),
                                        (char) Integer.parseInt("11100000", 2),
                                        (char) Integer.parseInt("11001011", 2)
                                }
                        ),
                        String.valueOf(
                                (char) Integer.parseInt("00011010", 2)
                        )
                ),
                Arguments.of(
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("00001110", 2),
                                        (char) Integer.parseInt("00001000", 2),
                                        (char) Integer.parseInt("01000000", 2),
                                }
                        ),
                        String.valueOf(
                                (char) Integer.parseInt("00100000", 2)
                        )
                ),
                Arguments.of(
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("10111100", 2),
                                        (char) Integer.parseInt("01111100", 2),
                                        (char) Integer.parseInt("11100011", 2),
                                        (char) Integer.parseInt("00101100", 2),
                                        (char) Integer.parseInt("00111011", 2),
                                        (char) Integer.parseInt("00001011", 2),
                                        (char) Integer.parseInt("00001101", 2),
                                        (char) Integer.parseInt("11110001", 2),
                                        (char) Integer.parseInt("11110010", 2),
                                        (char) Integer.parseInt("00000100", 2),
                                        (char) Integer.parseInt("11111000", 2),
                                        (char) Integer.parseInt("00010000", 2),
                                        (char) Integer.parseInt("00100000", 2),
                                        (char) Integer.parseInt("01000000", 2),
                                        (char) Integer.parseInt("01001100", 2),
                                        (char) Integer.parseInt("10001100", 2),
                                        (char) Integer.parseInt("00101111", 2),
                                        (char) Integer.parseInt("11011100", 2),
                                        (char) Integer.parseInt("00000111", 2),
                                        (char) Integer.parseInt("00110111", 2),
                                        (char) Integer.parseInt("11001110", 2),
                                        (char) Integer.parseInt("11000010", 2)
                                }
                        ),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("01101110", 2),
                                        (char) Integer.parseInt("00110100", 2),
                                        (char) Integer.parseInt("01001110", 2),
                                        (char) Integer.parseInt("11000011", 2),
                                        (char) Integer.parseInt("00000000", 2),
                                        (char) Integer.parseInt("00101101", 2),
                                        (char) Integer.parseInt("00110100", 2),
                                        (char) Integer.parseInt("10101011", 2)
                                }
                        )
                )
        );
    }

    @DisplayName("Should throw proper exception when input fails you")
    @ParameterizedTest
    @MethodSource("decodeCorruptionArgumentsProvider")
    void decodeCorruption(Exception exception, String input) {
        assertThrows(exception.getClass(), () -> decoder.decode(input));
    }
    private static Stream<Arguments> decodeCorruptionArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        new InputNotSentCorrectlyException(),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("11000011", 2)
                                }
                        )
                ),
                Arguments.of(
                        new InputNotSentCorrectlyException(),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("10111100", 2),
                                        (char) Integer.parseInt("10001100", 2),
                                        (char) Integer.parseInt("00100000", 2),
                                        (char) Integer.parseInt("11100000", 2),
                                        (char) Integer.parseInt("00111011", 2),
                                        (char) Integer.parseInt("11001000", 2),
                                        (char) Integer.parseInt("00110001", 2),
                                        (char) Integer.parseInt("00111101", 2),
                                        (char) Integer.parseInt("00111101", 2),
                                        (char) Integer.parseInt("11001110", 2),
                                        (char) Integer.parseInt("00000000", 2),
                                }
                        )
                ),
                Arguments.of(
                        new InputCorruptedException(),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("11011011", 2)
                                }
                        )
                ),
                Arguments.of(
                        new InputCorruptedException(),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("01011110", 2),
                                        (char) Integer.parseInt("01001001", 2),
                                        (char) Integer.parseInt("01000010", 2),
                                }
                        )
                )
        );
    }
}
