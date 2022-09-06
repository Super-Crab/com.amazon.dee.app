package com.here.sdk.core.threading;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
public final class Threading extends NativeBase {
    protected Threading(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.threading.Threading.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Threading.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    public static native PlatformThreading getPlatformThreading();

    public static native void setPlatformThreading(@NonNull PlatformThreading platformThreading);
}
