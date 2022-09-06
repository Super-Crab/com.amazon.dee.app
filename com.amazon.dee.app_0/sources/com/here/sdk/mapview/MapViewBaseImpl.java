package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import com.here.sdk.gestures.Gestures;
import com.here.sdk.mapview.MapViewBase;
/* loaded from: classes3.dex */
class MapViewBaseImpl extends NativeBase implements MapViewBase {
    protected MapViewBaseImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapViewBaseImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapViewBaseImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.mapview.MapViewBase
    public native void addMapRepresentable(@NonNull MapRepresentable mapRepresentable);

    @Override // com.here.sdk.mapview.MapViewBase
    @Nullable
    public native Point2D geoToViewCoordinates(@NonNull GeoCoordinates geoCoordinates);

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public native MapCamera getCamera();

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public native Gestures getGestures();

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public native MapScene getMapScene();

    @Override // com.here.sdk.mapview.MapViewBase
    public native double getPixelScale();

    @Override // com.here.sdk.mapview.MapViewBase
    public native void pickMapItems(@NonNull Point2D point2D, double d, @NonNull MapViewBase.PickMapItemsCallback pickMapItemsCallback);

    @Override // com.here.sdk.mapview.MapViewBase
    public native void removeMapRepresentable(@NonNull MapRepresentable mapRepresentable);

    @Override // com.here.sdk.mapview.MapViewBase
    public native void setWatermarkPosition(@NonNull WatermarkPlacement watermarkPlacement, long j);

    @Override // com.here.sdk.mapview.MapViewBase
    @Nullable
    public native GeoCoordinates viewToGeoCoordinates(@NonNull Point2D point2D);
}
