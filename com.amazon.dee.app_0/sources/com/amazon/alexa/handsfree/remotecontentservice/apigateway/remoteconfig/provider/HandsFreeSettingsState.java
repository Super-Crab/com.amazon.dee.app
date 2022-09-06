package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider;

import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public enum HandsFreeSettingsState {
    INACTIVE(0),
    ACTIVE(1),
    REMOTELY_DISABLED(2),
    LOCALLY_DISABLED(3);
    
    private int mValue;

    /* loaded from: classes8.dex */
    public interface OnStateChangedListener {
        void onStateChanged(HandsFreeSettingsState handsFreeSettingsState);
    }

    HandsFreeSettingsState(int i) {
        this.mValue = i;
    }

    public static HandsFreeSettingsState get(int i) {
        HandsFreeSettingsState[] values;
        for (HandsFreeSettingsState handsFreeSettingsState : values()) {
            if (handsFreeSettingsState.mValue == i) {
                return handsFreeSettingsState;
            }
        }
        throw new NoSuchElementException(i + " does not have a HandsFreeSettingsState representation");
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean isEnabled() {
        return this == ACTIVE || this == INACTIVE;
    }

    public boolean isRemotelyDisabled() {
        return this == REMOTELY_DISABLED;
    }
}
