package com.here.sdk.gestures;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.core.Point2D;
/* loaded from: classes3.dex */
class DoubleTapListenerImpl extends NativeBase implements DoubleTapListener {
    protected DoubleTapListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.gestures.DoubleTapListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                DoubleTapListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.gestures.DoubleTapListener
    public native void onDoubleTap(@NonNull Point2D point2D);
}
