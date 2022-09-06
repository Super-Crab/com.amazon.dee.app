package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.Color;
import com.here.sdk.core.GeoPolygon;
import com.here.sdk.core.Metadata;
/* loaded from: classes3.dex */
public final class MapPolygon extends NativeBase {
    protected MapPolygon(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapPolygon.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapPolygon.disposeNativeHandle(j2);
            }
        });
    }

    public MapPolygon(@NonNull GeoPolygon geoPolygon, @NonNull Color color) {
        this(make(geoPolygon, color), (Object) null);
        cacheThisInstance();
    }

    MapPolygon(@NonNull GeoPolygon geoPolygon, @NonNull Color color, double d) {
        this(make(geoPolygon, color, d), (Object) null);
        cacheThisInstance();
    }

    MapPolygon(@NonNull GeoPolygon geoPolygon, @NonNull Color color, @NonNull Color color2, double d) {
        this(make(geoPolygon, color, color2, d), (Object) null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make(@NonNull GeoPolygon geoPolygon, @NonNull Color color);

    private static native long make(@NonNull GeoPolygon geoPolygon, @NonNull Color color, double d);

    private static native long make(@NonNull GeoPolygon geoPolygon, @NonNull Color color, @NonNull Color color2, double d);

    native void destroyGraphics();

    public native boolean equals(Object obj);

    native void generateGraphics(@NonNull PolygonDataSource polygonDataSource, long j);

    @NonNull
    public native Color getFillColor();

    @NonNull
    public native GeoPolygon getGeometry();

    native long getId();

    @NonNull
    native Color getLineColor();

    native double getLineWidth();

    @Nullable
    public native Metadata getMetadata();

    native boolean hasFill();

    native boolean hasLine();

    public native int hashCode();

    native boolean isVisible();

    public native void setFillColor(@NonNull Color color);

    public native void setGeometry(@NonNull GeoPolygon geoPolygon);

    native void setLineColor(@NonNull Color color);

    native void setLineWidth(double d);

    public native void setMetadata(@Nullable Metadata metadata);

    native void setVisible(boolean z);

    @Deprecated
    public native void updateGeometry(@NonNull GeoPolygon geoPolygon);
}
