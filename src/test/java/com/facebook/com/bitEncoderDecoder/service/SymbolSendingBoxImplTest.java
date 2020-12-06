package com.facebook.com.bitEncoderDecoder.service;

import com.facebook.bitEncoderDecoder.app.Decoder;
import com.facebook.bitEncoderDecoder.app.Encoder;
import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.service.SendingBox;
import com.facebook.bitEncoderDecoder.service.SymbolSendingBoxImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SymbolSendingBoxImplTest {

    Encoder encoder = mock(Encoder.class);
    Transmitter transmitter = mock(Transmitter.class);
    Decoder decoder = mock(Decoder.class);

    SendingBox sendingBox = new SymbolSendingBoxImpl(encoder, transmitter, decoder);

    String message = "Ala ma kota";

    @DisplayName("Should encode() call encoder impl with same argument")
    @Test
    public void shouldEncodeWork(){
        // when
        sendingBox.encode(message);

        // then
        verify(encoder, times(1)).encode(message);
    }

    @DisplayName("Should encode() return encoded message")
    @Test
    public void shouldEncodeReturnOutput(){
        // given
        String expected = "KotKotKot";
        when(encoder.encode(message)).thenReturn(expected);

        // when
        String actual = sendingBox.encode(message);

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("Should send() call transmitter with proper argument")
    @Test
    public void shouldSendWork(){
        // when
        sendingBox.send(message);

        // then
        verify(transmitter, times(1)).send(message);
    }

    @DisplayName("Should send() return corrupted message")
    @Test
    public void shouldSendReturnOutput(){
        // given
        String expected = "AbcAbc";
        when(transmitter.send(message)).thenReturn(expected);

        // when
        String actual = sendingBox.send(message);

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("Should decode() calls decoder with proper argument")
    @Test
    public void shouldDecodeWork(){
        // when
        sendingBox.decode(message);

        // then
        verify(decoder, times(1)).decode(message);
    }

    @DisplayName("Should decode() returns decoded message")
    @Test
    public void shouldDecodeReturnOutput(){
        // given
        String expected = "groggpngrgreg...";
        when(decoder.decode(message)).thenReturn(expected);

        // when
        String actual = sendingBox.decode(message);

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("Should operate call three dependencies with arg")
    @Test
    public void shouldOperateWork(){
        // when
        sendingBox.operate(message);

        // then
        verify(encoder, times(1)).encode(any());
        verify(transmitter, times(1)).send(any());
        verify(decoder, times(1)).decode(any());
    }

    @DisplayName("Should operate return message")
    @Test
    public void shouldOperateReturnMessage(){
        // given
        String expected = "abc";
        when(encoder.encode(expected)).thenReturn(message);
        when(transmitter.send(message)).thenReturn(message);
        when(decoder.decode(message)).thenReturn(expected);

        // when
        String actual = sendingBox.operate(expected);

        // then
        assertEquals(expected, actual);
    }
}
