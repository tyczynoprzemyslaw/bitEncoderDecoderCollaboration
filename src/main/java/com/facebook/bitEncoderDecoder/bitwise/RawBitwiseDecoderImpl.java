package com.facebook.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Decoder;
import com.facebook.bitEncoderDecoder.bitwise.rawBitService.RawBitService;
import com.facebook.bitEncoderDecoder.exception.InputNotSentCorrectlyException;

public class RawBitwiseDecoderImpl implements Decoder {

    private final RawBitService rawBitService = new RawBitService();

    @Override
    public String decode(String input) {
        if (input == null) {
            throw new InputNotSentCorrectlyException();
        }
        return rawBitService.decode(input);
    }


}
