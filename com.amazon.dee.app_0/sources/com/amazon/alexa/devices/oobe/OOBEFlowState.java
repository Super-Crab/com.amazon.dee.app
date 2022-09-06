package com.amazon.alexa.devices.oobe;

import java.io.Serializable;
/* loaded from: classes6.dex */
public class OOBEFlowState implements Serializable {
    private String mFlowId;
    private String mLastInCompleteModule;
    private OOBEState mOOBEState;

    public OOBEFlowState(OOBEState oOBEState, String str, String str2) {
        this.mOOBEState = oOBEState;
        this.mLastInCompleteModule = str;
        this.mFlowId = str2;
    }

    public String getFlowId() {
        return this.mFlowId;
    }

    public String getLastInCompleteModule() {
        return this.mLastInCompleteModule;
    }

    public OOBEState getOOBEState() {
        return this.mOOBEState;
    }
}
