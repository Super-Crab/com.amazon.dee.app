package com.facebook.react.fabric.events;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.fabric.FabricSoLoader;
@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes2.dex */
public class EventEmitterWrapper {
    private final HybridData mHybridData = initHybrid();

    static {
        FabricSoLoader.staticInit();
    }

    private EventEmitterWrapper() {
    }

    private static native HybridData initHybrid();

    private native void invokeEvent(@NonNull String str, @NonNull NativeMap nativeMap);

    public void invoke(@NonNull String str, @Nullable WritableMap writableMap) {
        invokeEvent(str, writableMap == null ? new WritableNativeMap() : (NativeMap) writableMap);
    }
}
