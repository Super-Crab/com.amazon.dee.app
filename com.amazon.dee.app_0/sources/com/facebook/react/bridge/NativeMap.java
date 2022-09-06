package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
/* loaded from: classes2.dex */
public abstract class NativeMap {
    private HybridData mHybridData;

    static {
        ReactBridge.staticInit();
    }

    public NativeMap(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public native String toString();
}
