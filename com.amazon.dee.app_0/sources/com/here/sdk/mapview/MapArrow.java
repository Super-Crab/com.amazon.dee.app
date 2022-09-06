package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import com.here.NativeBase;
import com.here.sdk.core.Color;
import com.here.sdk.core.GeoPolyline;
/* loaded from: classes3.dex */
class MapArrow extends NativeBase {
    protected MapArrow(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapArrow.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapArrow.disposeNativeHandle(j2);
            }
        });
    }

    MapArrow(@NonNull GeoPolyline geoPolyline, double d, @NonNull Color color) {
        this(make(geoPolyline, d, color), null);
        cacheThisInstance();
    }

    MapArrow(@NonNull GeoPolyline geoPolyline, double d, @NonNull Color color, double d2, @NonNull Color color2) {
        this(make(geoPolyline, d, color, d2, color2), null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make(@NonNull GeoPolyline geoPolyline, double d, @NonNull Color color);

    private static native long make(@NonNull GeoPolyline geoPolyline, double d, @NonNull Color color, double d2, @NonNull Color color2);

    public native boolean equals(Object obj);

    public native int hashCode();
}
