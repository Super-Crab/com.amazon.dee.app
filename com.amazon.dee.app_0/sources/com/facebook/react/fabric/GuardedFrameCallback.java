package com.facebook.react.fabric;

import androidx.annotation.NonNull;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.ChoreographerCompat;
/* loaded from: classes2.dex */
public abstract class GuardedFrameCallback extends ChoreographerCompat.FrameCallback {
    @NonNull
    private final NativeModuleCallExceptionHandler mExceptionHandler;

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public GuardedFrameCallback(@NonNull ReactContext reactContext) {
        this(reactContext.getExceptionHandler());
    }

    @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
    public final void doFrame(long j) {
        try {
            doFrameGuarded(j);
        } catch (RuntimeException e) {
            this.mExceptionHandler.handleException(e);
        }
    }

    protected abstract void doFrameGuarded(long j);

    protected GuardedFrameCallback(@NonNull NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mExceptionHandler = nativeModuleCallExceptionHandler;
    }
}
