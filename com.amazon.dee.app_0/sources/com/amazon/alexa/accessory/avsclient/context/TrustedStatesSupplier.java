package com.amazon.alexa.accessory.avsclient.context;
/* loaded from: classes.dex */
public interface TrustedStatesSupplier {
    void activate();

    void deactivate();

    TrustedStates getContext();
}
