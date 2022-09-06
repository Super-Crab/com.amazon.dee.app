package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Metadata;
/* loaded from: classes3.dex */
public final class MapMarker3D extends NativeBase {
    protected MapMarker3D(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapMarker3D.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapMarker3D.disposeNativeHandle(j2);
            }
        });
    }

    public MapMarker3D(@NonNull GeoCoordinates geoCoordinates, @NonNull MapMarker3DModel mapMarker3DModel) {
        this(make(geoCoordinates, mapMarker3DModel), (Object) null);
        cacheThisInstance();
    }

    public MapMarker3D(@NonNull GeoCoordinates geoCoordinates, @NonNull MapMarker3DModel mapMarker3DModel, double d) {
        this(make(geoCoordinates, mapMarker3DModel, d), (Object) null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make(@NonNull GeoCoordinates geoCoordinates, @NonNull MapMarker3DModel mapMarker3DModel);

    private static native long make(@NonNull GeoCoordinates geoCoordinates, @NonNull MapMarker3DModel mapMarker3DModel, double d);

    native void destroyGraphics();

    public native boolean equals(Object obj);

    native void generateGraphics(@NonNull PointDataSource pointDataSource, long j);

    public native double getBearing();

    @NonNull
    public native GeoCoordinates getCoordinates();

    native long getId();

    @Nullable
    public native Metadata getMetadata();

    public native double getPitch();

    public native double getRoll();

    public native double getScale();

    public native int hashCode();

    public native void setBearing(double d);

    public native void setCoordinates(@NonNull GeoCoordinates geoCoordinates);

    public native void setMetadata(@Nullable Metadata metadata);

    public native void setPitch(double d);

    public native void setRoll(double d);

    public native void setScale(double d);
}
