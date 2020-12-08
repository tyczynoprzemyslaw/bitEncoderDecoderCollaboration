package com.facebook.com.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Encoder;
import com.facebook.bitEncoderDecoder.bitwise.BitwiseEncoderImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitwiseEncoderImplTest {
    Encoder encoder = new BitwiseEncoderImpl();

    @DisplayName("Should encoder() implements proper bitwise strategy")
    @ParameterizedTest
    @MethodSource("encodeArgumentsProvider")
    void encode(String source, String expected){
        assertEquals(expected, encoder.encode(source));
    }
    private static Stream<Arguments> encodeArgumentsProvider(){
        return Stream.of(
                Arguments.of(
                        String.valueOf(
                                (char) Integer.parseInt("10100011", 2)
                        ),
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("11001100", 2), //101P
                                        (char) Integer.parseInt("00000000", 2), //000P
                                        (char) Integer.parseInt("11110000", 2)  //110P
                                }

                        )
                ),
                Arguments.of(
                        String.valueOf(
                                new char[]{
                                        (char) Integer.parseInt("01110100", 2),
                                        (char) Integer.parseInt("01100101", 2),
                                        (char) Integer.parseInt("01010011", 2),
                                        (char) Integer.parseInt("01110100", 2)
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
                        "",
                        ""
                )
        );
    }
}
