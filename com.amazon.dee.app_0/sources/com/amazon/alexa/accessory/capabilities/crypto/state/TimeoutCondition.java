package com.amazon.alexa.accessory.capabilities.crypto.state;
/* loaded from: classes.dex */
public interface TimeoutCondition {
    KeyExchangeState getOnTimeoutState();

    long getTimeoutMs();
}
