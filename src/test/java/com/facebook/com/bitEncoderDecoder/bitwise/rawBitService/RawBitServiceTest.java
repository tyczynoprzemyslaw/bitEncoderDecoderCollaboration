package com.facebook.com.bitEncoderDecoder.bitwise.rawBitService;

import com.facebook.bitEncoderDecoder.bitwise.rawBitService.RawBitService;
import com.facebook.bitEncoderDecoder.exception.InputCorruptedException;
import com.facebook.bitEncoderDecoder.exception.InputNotSentCorrectlyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RawBitServiceTest {

    RawBitService rawBitService = new RawBitService();

    @DisplayName("Should getFirstByte work")
    @ParameterizedTest
    @MethodSource("getFirstByteArgumentsProvider")
    void getFirstByte(int expected, char given) {
        assertEquals(expected, rawBitService.getFirstByte(given));
    }

    private static Stream<Arguments> getFirstByteArgumentsProvider() {
        return Stream.of(
                Arguments.of(0b11001100, (char) 0b11001100),
                Arguments.of(0b1100, (char) 0b1100),
                Arguments.of(0b10010101, (char) 0b100010010101),
                Arguments.of(0, (char) 0)
        );
    }

    @DisplayName("Should checkForTransmissionErrors() work")
    @ParameterizedTest
    @MethodSource("checkForTransmissionErrorsArgumentsProvider")
    void checkForTransmissionErrors(Exception exception, int given) {
        assertThrows(exception.getClass(), () -> rawBitService.checkForTransmissionErrors(given));
    }

    private static Stream<Arguments> checkForTransmissionErrorsArgumentsProvider() {
        return Stream.of(
                Arguments.of(new InputCorruptedException(), 0b1010),
                Arguments.of(new InputCorruptedException(), 0b01010100),
                Arguments.of(new InputNotSentCorrectlyException(), 0b11001100),
                Arguments.of(new InputNotSentCorrectlyException(), 0b00111100)
        );
    }

    @DisplayName("Should removeNoise() return char without noise")
    @ParameterizedTest
    @MethodSource("removeNoiseArgumentsProvider")
    void removeNoise(char expected, char given) {
        assertEquals(expected, rawBitService.removeNoise(given));
    }

    private static Stream<Arguments> removeNoiseArgumentsProvider() {
        return Stream.of(
                Arguments.of((char) 0b11001100, (char) 0b01001100),
                Arguments.of((char) 0b11001100, (char) 0b11101100),
                Arguments.of((char) 0b11001100, (char) 0b11000100),
                Arguments.of((char) 0b11001100, (char) 0b11001110),
                Arguments.of((char) 0b00000000, (char) 0b10000000),
                Arguments.of((char) 0b00000000, (char) 0b00010000),
                Arguments.of((char) 0b00000000, (char) 0b00001000),
                Arguments.of((char) 0b00000000, (char) 0b00000010)
        );
    }

    @DisplayName("Should setZerosBitPair work")
    @ParameterizedTest
    @MethodSource("setZerosBitPairArgumentsProvider")
    void setZerosBitPair(int expected, int given, int index) {
        assertEquals(expected, rawBitService.setZerosBitPair(given, index));
    }

    private static Stream<Arguments> setZerosBitPairArgumentsProvider() {
        return Stream.of(
                Arguments.of(0b00111111, 0b11111111, 0),
                Arguments.of(0b10001111, 0b10101111, 2),
                Arguments.of(0b00000011, 0b00001111, 4),
                Arguments.of(0b11001100, 0b11001101, 6),
                Arguments.of(0, 0, 0)
        );
    }

    @DisplayName("Should setOnesBitPair work")
    @ParameterizedTest
    @MethodSource("setOnesBitPairArgumentsProvider")
    void setOnesBitPair(int expected, int given, int index) {
        assertEquals(expected, rawBitService.setOnesBitPair(given, index));
    }

    private static Stream<Arguments> setOnesBitPairArgumentsProvider() {
        return Stream.of(
                Arguments.of(0b11111111, 0b01111111, 0),
                Arguments.of(0b10111111, 0b10111111, 2),
                Arguments.of(0b10001111, 0b10000011, 4),
                Arguments.of(0b11001111, 0b11001101, 6),
                Arguments.of(0b11111111, 0b11111111, 0)
        );
    }

    @DisplayName("Should decodeChar return char with 3 bits of information")
    @ParameterizedTest
    @MethodSource("decodeCharArgumentsProvider")
    void decodeChar(char expected, char given) {
        assertEquals(expected, rawBitService.decodeChar(given));
    }

    private static Stream<Arguments> decodeCharArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        (char) Integer.parseInt("10100000", 2),
                        (char) Integer.parseInt("11001000", 2)
                ),
                Arguments.of(
                        (char) Integer.parseInt("00000000", 2),
                        (char) Integer.parseInt("10000000", 2)
                ),
                Arguments.of(
                        (char) Integer.parseInt("10000000", 2),
                        (char) Integer.parseInt("11100011", 2)
                ),
                Arguments.of(
                        (char) Integer.parseInt("00100000", 2),
                        (char) Integer.parseInt("00001110", 2)
                ),
                Arguments.of(
                        (char) Integer.parseInt("01000000", 2),
                        (char) Integer.parseInt("01110011", 2)
                ),
                Arguments.of(
                        (char) Integer.parseInt("11100000", 2),
                        (char) Integer.parseInt("11111011", 2)
                )
        );
    }

    @DisplayName("Should decode() follow proper algorithm")
    @ParameterizedTest
    @MethodSource("decodeArgumentsProvider")
    void decode(String input, String expected) {
        assertEquals(expected, rawBitService.decode(input));
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
        assertThrows(exception.getClass(), () -> rawBitService.decode(input));
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
