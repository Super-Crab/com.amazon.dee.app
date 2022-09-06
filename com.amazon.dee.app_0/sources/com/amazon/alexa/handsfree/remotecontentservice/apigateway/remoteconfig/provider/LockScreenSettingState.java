package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider;

import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public enum LockScreenSettingState {
    INACTIVE(0),
    ACTIVE(1),
    REMOTELY_DISABLED(2);
    
    private final int mValue;

    LockScreenSettingState(int i) {
        this.mValue = i;
    }

    public static LockScreenSettingState get(int i) {
        LockScreenSettingState[] values;
        for (LockScreenSettingState lockScreenSettingState : values()) {
            if (lockScreenSettingState.mValue == i) {
                return lockScreenSettingState;
            }
        }
        throw new NoSuchElementException(i + " does not have a LockScreenSettingState representation");
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean isEnabled() {
        return this == ACTIVE || this == INACTIVE;
    }
}
