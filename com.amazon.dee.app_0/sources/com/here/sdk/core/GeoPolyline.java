package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.errors.InstantiationErrorException;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class GeoPolyline {
    @NonNull
    public final List<GeoCoordinates> vertices;

    public GeoPolyline(@NonNull List<GeoCoordinates> list) throws InstantiationErrorException {
        this.vertices = make(list).vertices;
    }

    private static native GeoPolyline make(@NonNull List<GeoCoordinates> list) throws InstantiationErrorException;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GeoPolyline) {
            return Objects.equals(this.vertices, ((GeoPolyline) obj).vertices);
        }
        return false;
    }

    public native long getNearestIndexTo(@NonNull GeoCoordinates geoCoordinates);

    @NonNull
    native GeoCoordinates getNearestTo(@NonNull GeoCoordinates geoCoordinates);

    public int hashCode() {
        List<GeoCoordinates> list = this.vertices;
        return (list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI;
    }
}
