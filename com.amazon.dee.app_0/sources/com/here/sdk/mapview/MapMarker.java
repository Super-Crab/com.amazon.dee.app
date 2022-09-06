package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Metadata;
/* loaded from: classes3.dex */
public final class MapMarker extends NativeBase {
    protected MapMarker(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapMarker.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapMarker.disposeNativeHandle(j2);
            }
        });
    }

    public MapMarker(@NonNull GeoCoordinates geoCoordinates, @NonNull MapImage mapImage) {
        this(make(geoCoordinates, mapImage), (Object) null);
        cacheThisInstance();
    }

    public MapMarker(@NonNull GeoCoordinates geoCoordinates, @NonNull MapImage mapImage, @NonNull Anchor2D anchor2D) {
        this(make(geoCoordinates, mapImage, anchor2D), (Object) null);
        cacheThisInstance();
    }

    private native void cacheThisInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private static native long make(@NonNull GeoCoordinates geoCoordinates, @NonNull MapImage mapImage);

    private static native long make(@NonNull GeoCoordinates geoCoordinates, @NonNull MapImage mapImage, @NonNull Anchor2D anchor2D);

    native void destroyGraphics();

    public native boolean equals(Object obj);

    native void generateGraphics(@NonNull PointDataSource pointDataSource, long j);

    @NonNull
    public native Anchor2D getAnchor();

    @NonNull
    public native GeoCoordinates getCoordinates();

    public native int getDrawOrder();

    native long getId();

    @NonNull
    public native MapImage getImage();

    @Nullable
    public native Metadata getMetadata();

    public native int hashCode();

    native boolean isVisible();

    public native void setAnchor(@NonNull Anchor2D anchor2D);

    public native void setCoordinates(@NonNull GeoCoordinates geoCoordinates);

    public native void setDrawOrder(int i);

    public native void setImage(@NonNull MapImage mapImage);

    public native void setMetadata(@Nullable Metadata metadata);

    native void setVisible(boolean z);
}
