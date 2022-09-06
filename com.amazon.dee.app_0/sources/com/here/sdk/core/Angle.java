package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
public final class Angle extends NativeBase {
    protected Angle(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.Angle.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Angle.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    public static native Angle fromDegrees(double d);

    @NonNull
    public static native Angle fromRadians(double d);

    public native double getDegrees();

    public native double getRadians();
}
