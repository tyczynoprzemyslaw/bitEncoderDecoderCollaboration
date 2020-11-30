package com.facebook.com.bitEncoderDecoder;

import com.facebook.bitEncoderDecoder.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    App app = new App();

    @DisplayName("Output should be equal to input ")
    @ParameterizedTest
    @MethodSource("sendMessageArgumentsProvider")
    void sendMessage(String input) {
        assertEquals(input, app.sendMessage(input));
    }
    private static Stream<Arguments> sendMessageArgumentsProvider(){
        return Stream.of(
                Arguments.of("123456!q"),
                Arguments.of("Test of a lot of spaces"),
                Arguments.of("SmallAndBigLetters")
        );
    }

}
