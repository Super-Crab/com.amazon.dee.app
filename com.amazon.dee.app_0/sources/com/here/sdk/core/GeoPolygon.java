package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.errors.InstantiationErrorException;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class GeoPolygon {
    @NonNull
    public final List<GeoCoordinates> vertices;

    public GeoPolygon(@NonNull GeoCircle geoCircle) {
        this.vertices = make(geoCircle).vertices;
    }

    public GeoPolygon(@NonNull List<GeoCoordinates> list) throws InstantiationErrorException {
        this.vertices = make(list).vertices;
    }

    private static native GeoPolygon make(@NonNull GeoCircle geoCircle);

    private static native GeoPolygon make(@NonNull List<GeoCoordinates> list) throws InstantiationErrorException;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GeoPolygon) {
            return Objects.equals(this.vertices, ((GeoPolygon) obj).vertices);
        }
        return false;
    }

    public int hashCode() {
        List<GeoCoordinates> list = this.vertices;
        return (list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI;
    }
}
