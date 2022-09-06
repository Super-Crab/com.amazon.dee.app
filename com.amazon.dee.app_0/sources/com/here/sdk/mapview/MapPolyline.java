package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.Color;
import com.here.sdk.core.GeoPolyline;
import com.here.sdk.core.Metadata;
/* loaded from: classes3.dex */
public final class MapPolyline extends NativeBase {
    protected MapPolyline(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapPolyline.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapPolyline.disposeNativeHandle(j2);
            }
        });
    }

    public MapPolyline(@NonNull GeoPolyline geoPolyline, double d, @NonNull Color color) {
        this(make(geoPolyline, d, color), null);
        cacheThisInstance();
    }

    public MapPolyline(@NonNull GeoPolyline geoPolyline, double d, @NonNull Color color, double d2, @NonNull Color color2) {
        this(make(geoPolyline, d, color, d2, color2), null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make(@NonNull GeoPolyline geoPolyline, double d, @NonNull Color color);

    private static native long make(@NonNull GeoPolyline geoPolyline, double d, @NonNull Color color, double d2, @NonNull Color color2);

    native void destroyGraphics();

    public native boolean equals(Object obj);

    native void generateGraphics(@NonNull LineDataSource lineDataSource, long j);

    @Nullable
    public native Color getDashFillColor();

    @Nullable
    public native DashPattern getDashPattern();

    public native int getDrawOrder();

    @NonNull
    public native GeoPolyline getGeometry();

    native long getId();

    @NonNull
    public native LineCap getLineCap();

    @NonNull
    public native Color getLineColor();

    public native double getLineWidth();

    @Nullable
    public native Metadata getMetadata();

    @NonNull
    public native Color getOutlineColor();

    public native double getOutlineWidth();

    native boolean hasOutline();

    public native int hashCode();

    native boolean isDashOutlineEnabled();

    native boolean isVisible();

    public native void setDashFillColor(@Nullable Color color);

    native void setDashOutlineEnabled(boolean z);

    public native void setDashPattern(@Nullable DashPattern dashPattern);

    public native void setDrawOrder(int i);

    public native void setGeometry(@NonNull GeoPolyline geoPolyline);

    public native void setLineCap(@NonNull LineCap lineCap);

    public native void setLineColor(@NonNull Color color);

    public native void setLineWidth(double d);

    public native void setMetadata(@Nullable Metadata metadata);

    public native void setOutlineColor(@NonNull Color color);

    public native void setOutlineWidth(double d);

    native void setVisible(boolean z);

    @Deprecated
    public native void updateGeometry(@NonNull GeoPolyline geoPolyline);
}
