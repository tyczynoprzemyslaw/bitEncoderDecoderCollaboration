package com.facebook.bitEncoderDecoder.service;

import com.facebook.bitEncoderDecoder.app.Decoder;
import com.facebook.bitEncoderDecoder.app.Encoder;
import com.facebook.bitEncoderDecoder.app.Transmitter;

public class SymbolSendingBoxImpl implements SendingBox {
    private final Encoder encoder;
    private final Transmitter transmitter;
    private final Decoder decoder;

    public SymbolSendingBoxImpl(Encoder encoder, Transmitter transmitter, Decoder decoder) {
        this.encoder = encoder;
        this.transmitter = transmitter;
        this.decoder = decoder;
    }

    @Override
    public String operate(String input) {
        return null;
    }

    @Override
    public String decode(String input) {
        return null;
    }

    @Override
    public String send(String input) {
        return null;
    }

    @Override
    public String encode(String input) {
        return null;
    }
}
