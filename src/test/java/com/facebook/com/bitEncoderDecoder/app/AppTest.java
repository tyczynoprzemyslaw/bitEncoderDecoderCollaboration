package com.facebook.com.bitEncoderDecoder.app;

import com.facebook.bitEncoderDecoder.app.App;
import com.facebook.bitEncoderDecoder.service.SendingBox;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class AppTest {

    SendingBox sendingBox = mock(SendingBox.class);

    App app = new App(sendingBox);

    @DisplayName("Should app.operate() invoke sendingBox.operate()")
    @Test
    public void shouldOperateBeCalled(){
        // when
        app.operate("test");

        // then
        verify(sendingBox, times(1)).operate(anyString());
    }

    @DisplayName("Should app.operate() return sendingBox.operate() output")
    @Test
    public void shouldOperateReturnsString(){
        // given
        String expected = "abc";
        when(sendingBox.operate(anyString())).thenReturn(expected);

        // when
        String actual = app.operate(expected);

        // then
        assertEquals(expected, actual);
    }
}
