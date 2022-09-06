package com.here.sdk.core.threading;
/* loaded from: classes3.dex */
public interface TaskHandle {
    boolean cancel();

    boolean isCancelled();

    boolean isFinished();
}
