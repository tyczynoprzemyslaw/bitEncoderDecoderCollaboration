package com.facebook.com.bitEncoderDecoder.service;

import com.facebook.bitEncoderDecoder.app.Decoder;
import com.facebook.bitEncoderDecoder.app.Encoder;
import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.service.SendingBox;
import com.facebook.bitEncoderDecoder.service.SymbolSendingBoxImpl;

import static org.mockito.Mockito.mock;

public class SymbolSendingBoxImplTest {

    Encoder encoder = mock(Encoder.class);
    Transmitter transmitter = mock(Transmitter.class);
    Decoder decoder = mock(Decoder.class);

    SendingBox sendingBox = new SymbolSendingBoxImpl(encoder, transmitter, decoder);


}
