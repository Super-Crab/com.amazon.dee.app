package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
/* loaded from: classes.dex */
public class TransitionResponse {
    private final KeyExchangeState newState;
    private final Accessories.Response response;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransitionResponse(KeyExchangeState keyExchangeState, Accessories.Response response) {
        Preconditions.notNull(keyExchangeState, "newState");
        Preconditions.notNull(response, "response");
        this.newState = keyExchangeState;
        this.response = response;
    }

    public KeyExchangeState getNewState() {
        return this.newState;
    }

    public Accessories.Response getResponse() {
        return this.response;
    }
}
