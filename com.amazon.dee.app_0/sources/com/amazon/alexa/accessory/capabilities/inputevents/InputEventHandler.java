package com.amazon.alexa.accessory.capabilities.inputevents;

import com.amazon.alexa.accessory.protocol.Input;
/* loaded from: classes.dex */
public interface InputEventHandler {

    /* loaded from: classes.dex */
    public interface Callback {
        void onResult(Result result);
    }

    /* loaded from: classes.dex */
    public enum Result {
        SUCCESS,
        UNSUPPORTED,
        DISCARDED,
        UNKNOWN
    }

    void handleEvent(Input.IssueInputEvent issueInputEvent, Callback callback);
}
