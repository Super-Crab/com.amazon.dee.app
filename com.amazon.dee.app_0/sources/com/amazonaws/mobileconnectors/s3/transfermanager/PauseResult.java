package com.amazonaws.mobileconnectors.s3.transfermanager;
@Deprecated
/* loaded from: classes13.dex */
public final class PauseResult<T> {
    private final T infoToResume;
    private final PauseStatus pauseStatus;

    public PauseResult(PauseStatus pauseStatus, T t) {
        if (pauseStatus != null) {
            this.pauseStatus = pauseStatus;
            this.infoToResume = t;
            return;
        }
        throw new IllegalArgumentException();
    }

    public T getInfoToResume() {
        return this.infoToResume;
    }

    public PauseStatus getPauseStatus() {
        return this.pauseStatus;
    }

    public PauseResult(PauseStatus pauseStatus) {
        this(pauseStatus, null);
    }
}
