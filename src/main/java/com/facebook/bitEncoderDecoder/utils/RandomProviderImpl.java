package com.facebook.bitEncoderDecoder.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomProviderImpl implements RandomProvider{

    @Override
    public int getRandom(int rangeTo) {
        return ThreadLocalRandom.current().nextInt(rangeTo);
    }

    @Override
    public int getRandom(int rangeFrom, int rangeTo) {
        return ThreadLocalRandom.current().nextInt(rangeFrom, rangeTo);
    }
}
