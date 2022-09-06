package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.Color;
/* loaded from: classes3.dex */
public final class MapMarker3DModel extends NativeBase {
    protected MapMarker3DModel(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapMarker3DModel.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapMarker3DModel.disposeNativeHandle(j2);
            }
        });
    }

    public MapMarker3DModel(@NonNull String str) {
        this(make(str), (Object) null);
        cacheThisInstance();
    }

    public MapMarker3DModel(@NonNull String str, @NonNull String str2) {
        this(make(str, str2), (Object) null);
        cacheThisInstance();
    }

    public MapMarker3DModel(@NonNull String str, @NonNull String str2, @NonNull Color color) {
        this(make(str, str2, color), (Object) null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make(@NonNull String str);

    private static native long make(@NonNull String str, @NonNull String str2);

    private static native long make(@NonNull String str, @NonNull String str2, @NonNull Color color);

    @Nullable
    native Color getColor();

    @NonNull
    native String getGeometryFilePath();

    @Nullable
    native String getTextureFilePath();
}
