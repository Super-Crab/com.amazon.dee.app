package com.here.sdk.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCoordinates;
import java.util.List;
/* loaded from: classes3.dex */
public final class Place extends NativeBase {
    protected Place(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.search.Place.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Place.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    public native List<GeoCoordinates> getAccessPoints();

    @NonNull
    public native Address getAddress();

    @Nullable
    public native GeoBox getBoundingBox();

    @NonNull
    @Deprecated
    public native GeoCoordinates getCoordinates();

    @NonNull
    public native Details getDetails();

    @Nullable
    public native Integer getDistanceInMeters();

    @Nullable
    public native GeoCoordinates getGeoCoordinates();

    @NonNull
    public native String getId();

    @NonNull
    public native String getTitle();

    @NonNull
    public native PlaceType getType();
}
