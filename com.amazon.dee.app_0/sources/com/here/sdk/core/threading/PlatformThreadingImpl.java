package com.here.sdk.core.threading;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class PlatformThreadingImpl extends NativeBase implements PlatformThreading {
    protected PlatformThreadingImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.threading.PlatformThreadingImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                PlatformThreadingImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.threading.PlatformThreading
    @NonNull
    public native TaskHandle postToMainThread(@NonNull Runnable runnable);

    @Override // com.here.sdk.core.threading.PlatformThreading
    @NonNull
    public native TaskHandle postToMainThread(@NonNull Runnable runnable, long j);

    @Override // com.here.sdk.core.threading.PlatformThreading
    @NonNull
    public native TaskHandle runOnMainThread(@NonNull Runnable runnable);
}
