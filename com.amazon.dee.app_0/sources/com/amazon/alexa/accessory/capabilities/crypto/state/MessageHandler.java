package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
/* loaded from: classes.dex */
public interface MessageHandler<T> {
    TransitionResponse getResponse(T t, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics);
}
