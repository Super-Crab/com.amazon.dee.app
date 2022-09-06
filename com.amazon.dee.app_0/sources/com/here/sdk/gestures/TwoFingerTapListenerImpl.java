package com.here.sdk.gestures;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.core.Point2D;
/* loaded from: classes3.dex */
class TwoFingerTapListenerImpl extends NativeBase implements TwoFingerTapListener {
    protected TwoFingerTapListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.gestures.TwoFingerTapListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                TwoFingerTapListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.gestures.TwoFingerTapListener
    public native void onTwoFingerTap(@NonNull Point2D point2D);
}
