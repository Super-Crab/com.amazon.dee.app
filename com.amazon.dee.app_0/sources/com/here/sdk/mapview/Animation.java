package com.here.sdk.mapview;

import androidx.annotation.Nullable;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class Animation extends NativeBase {

    /* loaded from: classes3.dex */
    enum AnimationState {
        STARTED(0),
        FINISHED(1),
        CANCELLED(2);
        
        final int value;

        AnimationState(int i) {
            this.value = i;
        }
    }

    protected Animation(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.Animation.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Animation.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    native void cancel();

    @Nullable
    native AnimationStateListener getStateChangeListener();

    native void setStateChangeListener(@Nullable AnimationStateListener animationStateListener);

    native void start();
}
