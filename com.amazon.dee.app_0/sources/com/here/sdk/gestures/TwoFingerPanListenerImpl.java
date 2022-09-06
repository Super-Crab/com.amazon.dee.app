package com.here.sdk.gestures;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.core.Point2D;
/* loaded from: classes3.dex */
class TwoFingerPanListenerImpl extends NativeBase implements TwoFingerPanListener {
    protected TwoFingerPanListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.gestures.TwoFingerPanListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                TwoFingerPanListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.gestures.TwoFingerPanListener
    public native void onTwoFingerPan(@NonNull GestureState gestureState, @NonNull Point2D point2D, @NonNull Point2D point2D2, double d);
}
