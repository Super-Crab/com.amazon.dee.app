package com.here.sdk.gestures;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import java.util.Map;
/* loaded from: classes3.dex */
public final class InternalGestureDetector extends NativeBase {
    protected InternalGestureDetector(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.gestures.InternalGestureDetector.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                InternalGestureDetector.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public native Gestures getGestures();

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void processTouchEvent(@NonNull Map<Long, TouchPoint> map, long j, @NonNull TouchPhase touchPhase);

    native void setPixelScale(float f);
}
