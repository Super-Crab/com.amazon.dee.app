package com.amazonaws.mobileconnectors.s3.transfermanager;
@Deprecated
/* loaded from: classes13.dex */
public enum PauseStatus {
    SUCCESS,
    NOT_STARTED,
    CANCELLED_BEFORE_START,
    NO_EFFECT,
    CANCELLED;

    public boolean isCancelled() {
        return this == CANCELLED || this == CANCELLED_BEFORE_START;
    }

    public boolean isPaused() {
        return this == SUCCESS;
    }

    public boolean unchanged() {
        return this == NOT_STARTED || this == NO_EFFECT;
    }
}
