package com.amazon.alexa.accessory.capabilities.calling;
/* loaded from: classes.dex */
public interface CallRecipient {

    /* loaded from: classes.dex */
    public interface Call {
        String getCallerNumber();
    }

    void handleIncomingCall(Call call);
}
