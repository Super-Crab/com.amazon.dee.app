package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import com.here.sdk.gestures.Gestures;
import com.here.sdk.gestures.InternalGestureDetector;
import com.here.sdk.mapview.MapViewBase;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class MapViewInternal extends NativeBase implements MapViewBase {

    @FunctionalInterface
    /* loaded from: classes3.dex */
    interface TakeScreenshotCallback {
        void onScreenshotTaken(@Nullable byte[] bArr);
    }

    /* loaded from: classes3.dex */
    static class TakeScreenshotCallbackImpl extends NativeBase implements TakeScreenshotCallback {
        protected TakeScreenshotCallbackImpl(long j, Object obj) {
            super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapViewInternal.TakeScreenshotCallbackImpl.1
                @Override // com.here.NativeBase.Disposer
                public void disposeNative(long j2) {
                    TakeScreenshotCallbackImpl.disposeNativeHandle(j2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static native void disposeNativeHandle(long j);

        @Override // com.here.sdk.mapview.MapViewInternal.TakeScreenshotCallback
        public native void onScreenshotTaken(@Nullable byte[] bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapViewInternal() {
        this(create(), null);
        cacheThisInstance();
    }

    protected MapViewInternal(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapViewInternal.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapViewInternal.disposeNativeHandle(j2);
            }
        });
    }

    private native void cacheThisInstance();

    private static native long create();

    static native void destroyAll();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    static native long getInstanceCount();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean hasInstance(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean hasInstance(@NonNull MapViewInternal mapViewInternal);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void invalidateViews();

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static native MapViewInternal restoreInstance(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long storeInstance(@NonNull MapViewInternal mapViewInternal);

    @Override // com.here.sdk.mapview.MapViewBase
    public native void addMapRepresentable(@NonNull MapRepresentable mapRepresentable);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void addWatermark(@NonNull Point2D point2D);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void attachHarpToRenderTarget(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void destroy();

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void detachHarpFromRenderTarget();

    @Override // com.here.sdk.mapview.MapViewBase
    @Nullable
    public native Point2D geoToViewCoordinates(@NonNull GeoCoordinates geoCoordinates);

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public native MapCamera getCamera();

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public native Gestures getGestures();

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public native InternalGestureDetector getInternalGestureDetector();

    @Override // com.here.sdk.mapview.MapViewBase
    @NonNull
    public native MapScene getMapScene();

    @Override // com.here.sdk.mapview.MapViewBase
    public native double getPixelScale();

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean isContinuousRendering();

    @Override // com.here.sdk.mapview.MapViewBase
    public native void pickMapItems(@NonNull Point2D point2D, double d, @NonNull MapViewBase.PickMapItemsCallback pickMapItemsCallback);

    native void redraw();

    native void reloadWatermark(@NonNull String str);

    @Override // com.here.sdk.mapview.MapViewBase
    public native void removeMapRepresentable(@NonNull MapRepresentable mapRepresentable);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void setContinuousRendering(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void setDisplayMetrics(double d, long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void setFirstFrameRenderedListener(@Nullable FirstFrameRenderedListener firstFrameRenderedListener);

    native void setRenderTargetUpdatedListener(@Nullable RenderTargetUpdatedListener renderTargetUpdatedListener);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void setViewSize(long j, long j2);

    @Override // com.here.sdk.mapview.MapViewBase
    public native void setWatermarkPosition(@NonNull WatermarkPlacement watermarkPlacement, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void takeScreenshot(@NonNull TakeScreenshotCallback takeScreenshotCallback);

    @Override // com.here.sdk.mapview.MapViewBase
    @Nullable
    public native GeoCoordinates viewToGeoCoordinates(@NonNull Point2D point2D);
}
