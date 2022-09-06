package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.mapview.Animation;
/* loaded from: classes3.dex */
class AnimationStateListenerImpl extends NativeBase implements AnimationStateListener {
    protected AnimationStateListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.AnimationStateListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                AnimationStateListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.mapview.AnimationStateListener
    public native void onAnimationStateChange(@NonNull Animation.AnimationState animationState);
}
