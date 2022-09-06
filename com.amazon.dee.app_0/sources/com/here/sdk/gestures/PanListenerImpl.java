package com.here.sdk.gestures;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.core.Point2D;
/* loaded from: classes3.dex */
class PanListenerImpl extends NativeBase implements PanListener {
    protected PanListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.gestures.PanListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                PanListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.gestures.PanListener
    public native void onPan(@NonNull GestureState gestureState, @NonNull Point2D point2D, @NonNull Point2D point2D2, double d);
}
