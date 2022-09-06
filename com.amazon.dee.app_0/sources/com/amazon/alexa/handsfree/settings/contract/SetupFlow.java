package com.amazon.alexa.handsfree.settings.contract;
/* loaded from: classes8.dex */
public enum SetupFlow {
    DOUBLE_MICROPHONE(false),
    SINGLE_MICROPHONE(false),
    DOUBLE_MICROPHONE_1PSV(false),
    SINGLE_MICROPHONE_1PSV(false),
    DOUBLE_MICROPHONE_AIS(true),
    SINGLE_MICROPHONE_AIS(true),
    DEFAULT(false),
    AIS(true);
    
    private final boolean mIsAIS;

    SetupFlow(boolean z) {
        this.mIsAIS = z;
    }

    public boolean isAIS() {
        return this.mIsAIS;
    }
}
