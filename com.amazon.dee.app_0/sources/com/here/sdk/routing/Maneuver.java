package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoCoordinates;
import java.util.List;
/* loaded from: classes3.dex */
public final class Maneuver extends NativeBase {
    private List<GeoCoordinates> cache_getPolyline;
    private boolean is_cached_getPolyline;

    protected Maneuver(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.routing.Maneuver.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Maneuver.disposeNativeHandle(j2);
            }
        });
        this.is_cached_getPolyline = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private native List<GeoCoordinates> getPolyline_private();

    @NonNull
    public native ManeuverAction getAction();

    @NonNull
    public native GeoCoordinates getCoordinates();

    @Nullable
    public native String getCountryCode();

    public native int getLengthInMeters();

    @Nullable
    public native String getNextRoadName();

    @Nullable
    public native String getNextRoadNameLanguageCode();

    @Nullable
    public native String getNextRoadNumber();

    @NonNull
    public native RoadTexts getNextRoadTexts();

    @Nullable
    public native RoadType getNextRoadType();

    @NonNull
    public List<GeoCoordinates> getPolyline() {
        if (!this.is_cached_getPolyline) {
            this.cache_getPolyline = getPolyline_private();
            this.is_cached_getPolyline = true;
        }
        return this.cache_getPolyline;
    }

    @Nullable
    public native String getRoadName();

    @Nullable
    public native String getRoadNameLanguageCode();

    @Nullable
    public native String getRoadNumber();

    @NonNull
    public native RoadTexts getRoadTexts();

    @Nullable
    public native RoadType getRoadType();

    @Nullable
    native TbtManeuver getTbtManeuver();

    @Nullable
    native TbtManeuverAction getTbtManeuverAction();

    @NonNull
    public native String getText();
}
