package com.facebook.react.bridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
/* loaded from: classes2.dex */
public class WritableNativeMap extends ReadableNativeMap implements WritableMap {
    static {
        ReactBridge.staticInit();
    }

    public WritableNativeMap() {
        super(initHybrid());
    }

    private static native HybridData initHybrid();

    private native void mergeNativeMap(ReadableNativeMap readableNativeMap);

    private native void putNativeArray(String str, WritableNativeArray writableNativeArray);

    private native void putNativeMap(String str, WritableNativeMap writableNativeMap);

    @Override // com.facebook.react.bridge.WritableMap
    public WritableMap copy() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.merge(this);
        return writableNativeMap;
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void merge(@NonNull ReadableMap readableMap) {
        Assertions.assertCondition(readableMap instanceof ReadableNativeMap, "Illegal type provided");
        mergeNativeMap((ReadableNativeMap) readableMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putArray(@NonNull String str, @Nullable ReadableArray readableArray) {
        Assertions.assertCondition(readableArray == null || (readableArray instanceof WritableNativeArray), "Illegal type provided");
        putNativeArray(str, (WritableNativeArray) readableArray);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public native void putBoolean(@NonNull String str, boolean z);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putDouble(@NonNull String str, double d);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putInt(@NonNull String str, int i);

    @Override // com.facebook.react.bridge.WritableMap
    public void putMap(@NonNull String str, @Nullable ReadableMap readableMap) {
        Assertions.assertCondition(readableMap == null || (readableMap instanceof WritableNativeMap), "Illegal type provided");
        putNativeMap(str, (WritableNativeMap) readableMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public native void putNull(@NonNull String str);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putString(@NonNull String str, @Nullable String str2);
}
