package com.facebook.com.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.bitwise.BitwiseTransmitterImpl;
import com.facebook.bitEncoderDecoder.utils.RandomProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BitwiseTransmitterImplTest {

    RandomProvider randomProvider = mock(RandomProvider.class);
    Transmitter transmitter = new BitwiseTransmitterImpl(randomProvider);

    @DisplayName("Should send() outputs changed string")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "test", "Ala ma kota"})
    void send(String source){
        assertNotEquals(source, transmitter.send(source));
    }

    @DisplayName("Should send() change only 1 bit in each byte")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "test", "Ala ma kota"})
    void sendAndCheckBites(String source){
        // given
        int[] onesInSource = countOnesInString(source);

        // when
        String target = transmitter.send(source);
        int[] onesInTarget = countOnesInString(target);

        // then
        assertEquals(onesInSource.length, onesInTarget.length);
        for (int i = 0; i < onesInSource.length; i++){
            boolean takenIsOneBit = onesInSource[i] - 1 == onesInTarget[i];
            boolean addedIsOneBit = onesInSource[i] + 1 == onesInTarget[i];
            assertTrue(takenIsOneBit || addedIsOneBit);
        }
    }

    private int[] countOnesInString(String source){
        if (source == null || source.isBlank()){
            return new int[0];
        }
        int[] onesInSource = new int[source.length()];
        for (int i = 0; i < onesInSource.length; i++){
            onesInSource[i] = countOnesInChar(source.charAt(i));
        }
        return onesInSource;
    }

    private int countOnesInChar(char input){
        int ones = 0;
        input = (char) (input & 0b11111111);
        int charLength = Integer.toBinaryString(input).length();
        int charLengthWithoutOnes = Integer.toBinaryString(input).replaceAll("1", "").length();
        ones += charLength - charLengthWithoutOnes;
        return ones;
    }

    @DisplayName("Should send() given empty or blank return empty")
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "             "})
    void sendBlanks(String source){
        // given
        String expected = "";

        // when
        String target = transmitter.send(source);

        // then
        assertEquals(expected, target);
    }

    @DisplayName("Should send() change determined bit while mocking RandomProvider")
    @ParameterizedTest
    @MethodSource("sendMocksArgumentsProvider")
    void sendMocks(int positionForChange, String expected, String given) {
        // given
        when(randomProvider.getRandom(anyInt())).thenReturn(positionForChange);
        when(randomProvider.getRandom(anyInt(), anyInt())).thenReturn(positionForChange);

        // when
        String actual = transmitter.send(given);

        // then
        assertEquals(expected, actual);
    }
    private static Stream<Arguments> sendMocksArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        0,
                        String.valueOf(
                                (char) Integer.parseInt("10111100", 2)
                        ),
                        String.valueOf(
                                (char) Integer.parseInt("00111100", 2)
                        )
                ),
                Arguments.of(
                        1,
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("01111100", 2),
                                        (char) Integer.parseInt("10001100", 2),
                                        (char) Integer.parseInt("01000000", 2),
                                        (char) Integer.parseInt("10110000", 2),
                                        (char) Integer.parseInt("01110011", 2),
                                        (char) Integer.parseInt("10001100", 2),
                                        (char) Integer.parseInt("01110011", 2),
                                        (char) Integer.parseInt("01111100", 2),
                                        (char) Integer.parseInt("01111100", 2),
                                        (char) Integer.parseInt("10001100", 2),
                                        (char) Integer.parseInt("01000000", 2),
                                }
                        ),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("00111100", 2),
                                        (char) Integer.parseInt("11001100", 2),
                                        (char) Integer.parseInt("00000000", 2),
                                        (char) Integer.parseInt("11110000", 2),
                                        (char) Integer.parseInt("00110011", 2),
                                        (char) Integer.parseInt("11001100", 2),
                                        (char) Integer.parseInt("00110011", 2),
                                        (char) Integer.parseInt("00111100", 2),
                                        (char) Integer.parseInt("00111100", 2),
                                        (char) Integer.parseInt("11001100", 2),
                                        (char) Integer.parseInt("00000000", 2),
                                }
                        )
                ),
                Arguments.of(
                        5,
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("11001000", 2),
                                        (char) Integer.parseInt("00000100", 2),
                                        (char) Integer.parseInt("11110100", 2)
                                }

                        ),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("11001100", 2),
                                        (char) Integer.parseInt("00000000", 2),
                                        (char) Integer.parseInt("11110000", 2)
                                }

                        )
                ),
                Arguments.of(
                        6,
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("00110001", 2),
                                        (char) Integer.parseInt("11110010", 2),
                                        (char) Integer.parseInt("00001101", 2)
                                }
                        ),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("00110011", 2),
                                        (char) Integer.parseInt("11110000", 2),
                                        (char) Integer.parseInt("00001111", 2)
                                }
                        )
                )

        );
    }
}
