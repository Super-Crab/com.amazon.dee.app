package com.here.sdk.gestures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
/* loaded from: classes3.dex */
public final class Gestures extends NativeBase {
    protected Gestures(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.gestures.Gestures.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Gestures.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    public native void disableDefaultAction(@NonNull GestureType gestureType);

    public native void enableDefaultAction(@NonNull GestureType gestureType);

    @Nullable
    public native DoubleTapListener getDoubleTapListener();

    @Nullable
    public native LongPressListener getLongPressListener();

    @Nullable
    public native PanListener getPanListener();

    @Nullable
    public native PinchRotateListener getPinchRotateListener();

    @Nullable
    public native TapListener getTapListener();

    @Nullable
    public native TwoFingerPanListener getTwoFingerPanListener();

    @Nullable
    public native TwoFingerTapListener getTwoFingerTapListener();

    public native void setDoubleTapListener(@Nullable DoubleTapListener doubleTapListener);

    public native void setLongPressListener(@Nullable LongPressListener longPressListener);

    public native void setPanListener(@Nullable PanListener panListener);

    public native void setPinchRotateListener(@Nullable PinchRotateListener pinchRotateListener);

    public native void setTapListener(@Nullable TapListener tapListener);

    public native void setTwoFingerPanListener(@Nullable TwoFingerPanListener twoFingerPanListener);

    public native void setTwoFingerTapListener(@Nullable TwoFingerTapListener twoFingerTapListener);
}
