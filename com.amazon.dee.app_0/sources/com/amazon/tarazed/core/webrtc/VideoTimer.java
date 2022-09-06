package com.amazon.tarazed.core.webrtc;

import androidx.annotation.VisibleForTesting;
import kotlin.Metadata;
/* compiled from: VideoTimer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\u0010\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0016J\b\u0010\u0017\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\u00020\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/VideoTimer;", "", "()V", "elapsedTimeMs", "", "isActive", "", "isActive$annotations", "isActive$TarazedMobileCore_release", "()Z", "setActive$TarazedMobileCore_release", "(Z)V", "lastStartTimeMs", "timerLength", "getTimerLength$TarazedMobileCore_release", "()J", "currentTime", "currentTime$TarazedMobileCore_release", "start", "", "start$TarazedMobileCore_release", "stop", "stop$TarazedMobileCore_release", "timeSinceLastStart", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class VideoTimer {
    private long elapsedTimeMs;
    private boolean isActive;
    private long lastStartTimeMs;

    @VisibleForTesting
    public static /* synthetic */ void isActive$annotations() {
    }

    private final long timeSinceLastStart() {
        return currentTime$TarazedMobileCore_release() - this.lastStartTimeMs;
    }

    @VisibleForTesting
    public final long currentTime$TarazedMobileCore_release() {
        return System.currentTimeMillis();
    }

    public final long getTimerLength$TarazedMobileCore_release() {
        if (this.isActive) {
            return this.elapsedTimeMs + timeSinceLastStart();
        }
        return this.elapsedTimeMs;
    }

    public final boolean isActive$TarazedMobileCore_release() {
        return this.isActive;
    }

    public final void setActive$TarazedMobileCore_release(boolean z) {
        this.isActive = z;
    }

    public final void start$TarazedMobileCore_release() {
        if (this.isActive) {
            return;
        }
        this.isActive = true;
        this.lastStartTimeMs = currentTime$TarazedMobileCore_release();
    }

    public final void stop$TarazedMobileCore_release() {
        if (!this.isActive) {
            return;
        }
        this.isActive = false;
        this.elapsedTimeMs += timeSinceLastStart();
    }
}
