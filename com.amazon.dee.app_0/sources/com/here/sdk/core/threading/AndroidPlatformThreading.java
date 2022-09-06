package com.here.sdk.core.threading;
/* loaded from: classes3.dex */
public class AndroidPlatformThreading implements PlatformThreading {
    @Override // com.here.sdk.core.threading.PlatformThreading
    public TaskHandle postToMainThread(Runnable runnable) {
        return postToMainThread(runnable, 0L);
    }

    @Override // com.here.sdk.core.threading.PlatformThreading
    public TaskHandle postToMainThread(Runnable runnable, long j) {
        return MainThreadTaskRunner.post(runnable, j);
    }

    @Override // com.here.sdk.core.threading.PlatformThreading
    public TaskHandle runOnMainThread(Runnable runnable) {
        return MainThreadTaskRunner.run(runnable);
    }
}
