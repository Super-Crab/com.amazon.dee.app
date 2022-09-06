package com.facebook.react.bridge;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
/* loaded from: classes2.dex */
public class WritableNativeArray extends ReadableNativeArray implements WritableArray {
    static {
        ReactBridge.staticInit();
    }

    public WritableNativeArray() {
        super(initHybrid());
    }

    private static native HybridData initHybrid();

    private native void pushNativeArray(WritableNativeArray writableNativeArray);

    private native void pushNativeMap(WritableNativeMap writableNativeMap);

    @Override // com.facebook.react.bridge.WritableArray
    public void pushArray(@Nullable ReadableArray readableArray) {
        Assertions.assertCondition(readableArray == null || (readableArray instanceof WritableNativeArray), "Illegal type provided");
        pushNativeArray((WritableNativeArray) readableArray);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushBoolean(boolean z);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushDouble(double d);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushInt(int i);

    @Override // com.facebook.react.bridge.WritableArray
    public void pushMap(@Nullable ReadableMap readableMap) {
        Assertions.assertCondition(readableMap == null || (readableMap instanceof WritableNativeMap), "Illegal type provided");
        pushNativeMap((WritableNativeMap) readableMap);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushNull();

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushString(@Nullable String str);
}
