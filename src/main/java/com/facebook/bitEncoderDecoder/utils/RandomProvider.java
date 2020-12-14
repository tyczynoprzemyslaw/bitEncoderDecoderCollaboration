package com.facebook.bitEncoderDecoder.utils;

public interface RandomProvider {
    int getRandom(int rangeTo);
    int getRandom(int rangeFrom, int rangeTo);
}
