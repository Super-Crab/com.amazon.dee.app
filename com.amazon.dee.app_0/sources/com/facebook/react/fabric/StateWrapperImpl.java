package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.StateWrapper;
@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes2.dex */
public class StateWrapperImpl implements StateWrapper {
    private Runnable mFailureCallback = null;
    private int mUpdateStateId = 0;
    private final HybridData mHybridData = initHybrid();

    static {
        FabricSoLoader.staticInit();
    }

    private StateWrapperImpl() {
    }

    private static native HybridData initHybrid();

    @Override // com.facebook.react.uimanager.StateWrapper
    public native ReadableNativeMap getState();

    @Override // com.facebook.react.uimanager.StateWrapper
    public void updateState(@NonNull WritableMap writableMap, Runnable runnable) {
        this.mUpdateStateId++;
        this.mFailureCallback = runnable;
        updateStateWithFailureCallbackImpl((NativeMap) writableMap, this, this.mUpdateStateId);
    }

    @AnyThread
    public void updateStateFailed(int i) {
        if (i != this.mUpdateStateId) {
            return;
        }
        Runnable runnable = this.mFailureCallback;
        this.mFailureCallback = null;
        if (runnable == null) {
            return;
        }
        UiThreadUtil.runOnUiThread(runnable);
    }

    public native void updateStateImpl(@NonNull NativeMap nativeMap);

    public native void updateStateWithFailureCallbackImpl(@NonNull NativeMap nativeMap, Object obj, int i);
}
