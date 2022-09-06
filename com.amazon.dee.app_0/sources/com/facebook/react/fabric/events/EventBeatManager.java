package com.facebook.react.fabric.events;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.fabric.FabricSoLoader;
import com.facebook.react.uimanager.events.BatchEventDispatchedListener;
@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes2.dex */
public class EventBeatManager implements BatchEventDispatchedListener {
    private final HybridData mHybridData = initHybrid();
    private final ReactApplicationContext mReactApplicationContext;

    static {
        FabricSoLoader.staticInit();
    }

    public EventBeatManager(@NonNull ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    private static native HybridData initHybrid();

    private native void tick();

    @Override // com.facebook.react.uimanager.events.BatchEventDispatchedListener
    public void onBatchEventDispatched() {
        tick();
    }
}
