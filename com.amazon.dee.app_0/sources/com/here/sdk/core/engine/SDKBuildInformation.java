package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
public final class SDKBuildInformation extends NativeBase {
    protected SDKBuildInformation(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.engine.SDKBuildInformation.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                SDKBuildInformation.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    public static native SDKVersion sdkVersion();
}
