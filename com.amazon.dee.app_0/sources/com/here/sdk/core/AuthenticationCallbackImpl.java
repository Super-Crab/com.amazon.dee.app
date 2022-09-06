package com.here.sdk.core;

import androidx.annotation.Nullable;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class AuthenticationCallbackImpl extends NativeBase implements AuthenticationCallback {
    protected AuthenticationCallbackImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.AuthenticationCallbackImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                AuthenticationCallbackImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.AuthenticationCallback
    public native void onTokenReceived(@Nullable AuthenticationError authenticationError, @Nullable AuthenticationData authenticationData);
}
