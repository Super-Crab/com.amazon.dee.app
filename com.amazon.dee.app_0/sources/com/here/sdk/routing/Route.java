package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.LanguageCode;
import java.util.List;
/* loaded from: classes3.dex */
public final class Route extends NativeBase {
    private List<GeoCoordinates> cache_getPolyline;
    private List<Section> cache_getSections;
    private boolean is_cached_getPolyline;
    private boolean is_cached_getSections;

    protected Route(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.routing.Route.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Route.disposeNativeHandle(j2);
            }
        });
        this.is_cached_getSections = false;
        this.is_cached_getPolyline = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private native List<GeoCoordinates> getPolyline_private();

    private native List<Section> getSections_private();

    @NonNull
    public native GeoBox getBoundingBox();

    @Nullable
    native Double getConsumptionInKilowattHours();

    public native int getDurationInSeconds();

    @NonNull
    public native LanguageCode getLanguage();

    public native int getLengthInMeters();

    @NonNull
    public native OptimizationMode getOptimizationMode();

    @NonNull
    public List<GeoCoordinates> getPolyline() {
        if (!this.is_cached_getPolyline) {
            this.cache_getPolyline = getPolyline_private();
            this.is_cached_getPolyline = true;
        }
        return this.cache_getPolyline;
    }

    @NonNull
    public List<Section> getSections() {
        if (!this.is_cached_getSections) {
            this.cache_getSections = getSections_private();
            this.is_cached_getSections = true;
        }
        return this.cache_getSections;
    }

    public native int getTrafficDelayInSeconds();

    @NonNull
    public native TransportMode getTransportMode();
}
