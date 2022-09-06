package com.here.sdk.core.threading;
/* loaded from: classes3.dex */
public final class ThreadingInitializer {
    private ThreadingInitializer() {
    }

    public static void initialize() {
        Threading.setPlatformThreading(new AndroidPlatformThreading());
    }
}
