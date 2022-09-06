package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.core.threading.PlatformThreading;
/* loaded from: classes3.dex */
public final class Authentication extends NativeBase {
    protected Authentication(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.Authentication.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Authentication.disposeNativeHandle(j2);
            }
        });
    }

    @NonNull
    public static native AuthenticationData authenticate(@NonNull SDKNativeEngine sDKNativeEngine) throws AuthenticationException;

    public static native void authenticate(@NonNull SDKNativeEngine sDKNativeEngine, @NonNull AuthenticationCallback authenticationCallback);

    static native void authenticate(@NonNull SDKNativeEngine sDKNativeEngine, @NonNull AuthenticationCallback authenticationCallback, @NonNull PlatformThreading platformThreading);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);
}
