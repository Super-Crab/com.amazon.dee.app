package com.here.sdk.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
/* loaded from: classes3.dex */
public final class Metadata extends NativeBase {
    public Metadata() {
        this(make(), null);
        cacheThisInstance();
    }

    protected Metadata(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.Metadata.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Metadata.disposeNativeHandle(j2);
            }
        });
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make();

    @Nullable
    public native CustomMetadataValue getCustomValue(@NonNull String str);

    @Nullable
    public native Double getDouble(@NonNull String str);

    @Nullable
    native GeoCoordinates getGeoCoordinates(@NonNull String str);

    @Nullable
    public native Integer getInteger(@NonNull String str);

    @Nullable
    public native String getString(@NonNull String str);

    @Nullable
    public native MetadataType getType(@NonNull String str);

    public native void removeValue(@NonNull String str);

    public native void setCustomValue(@NonNull String str, @NonNull CustomMetadataValue customMetadataValue);

    public native void setDouble(@NonNull String str, double d);

    native void setGeoCoordinates(@NonNull String str, @NonNull GeoCoordinates geoCoordinates);

    public native void setInteger(@NonNull String str, int i);

    public native void setString(@NonNull String str, @NonNull String str2);
}
