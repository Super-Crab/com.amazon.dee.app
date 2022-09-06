package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import com.here.sdk.gestures.Gestures;
/* loaded from: classes3.dex */
public interface MapViewBase {

    @FunctionalInterface
    /* loaded from: classes3.dex */
    public interface PickMapItemsCallback {
        void onPickMapItems(@Nullable PickMapItemsResult pickMapItemsResult);
    }

    /* loaded from: classes3.dex */
    public static class PickMapItemsCallbackImpl extends NativeBase implements PickMapItemsCallback {
        protected PickMapItemsCallbackImpl(long j, Object obj) {
            super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapViewBase.PickMapItemsCallbackImpl.1
                @Override // com.here.NativeBase.Disposer
                public void disposeNative(long j2) {
                    PickMapItemsCallbackImpl.disposeNativeHandle(j2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static native void disposeNativeHandle(long j);

        @Override // com.here.sdk.mapview.MapViewBase.PickMapItemsCallback
        public native void onPickMapItems(@Nullable PickMapItemsResult pickMapItemsResult);
    }

    void addMapRepresentable(@NonNull MapRepresentable mapRepresentable);

    @Nullable
    Point2D geoToViewCoordinates(@NonNull GeoCoordinates geoCoordinates);

    @NonNull
    MapCamera getCamera();

    @NonNull
    Gestures getGestures();

    @NonNull
    MapScene getMapScene();

    double getPixelScale();

    void pickMapItems(@NonNull Point2D point2D, double d, @NonNull PickMapItemsCallback pickMapItemsCallback);

    void removeMapRepresentable(@NonNull MapRepresentable mapRepresentable);

    void setWatermarkPosition(@NonNull WatermarkPlacement watermarkPlacement, long j);

    @Nullable
    GeoCoordinates viewToGeoCoordinates(@NonNull Point2D point2D);
}
