package com.amazon.alexa.accessory.capabilities.crypto.state.event;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
/* loaded from: classes.dex */
public interface KeyExchangeEventHandler<T> {
    KeyExchangeState handleEvent(T t, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics);
}
