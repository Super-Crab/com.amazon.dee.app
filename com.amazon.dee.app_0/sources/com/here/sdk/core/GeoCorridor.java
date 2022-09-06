package com.here.sdk.core;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class GeoCorridor {
    @NonNull
    public final List<GeoCoordinates> polyline;
    public final int radiusInMeters;

    @Deprecated
    public GeoCorridor(@NonNull List<GeoCoordinates> list, double d) {
        GeoCorridor make = make(list, d);
        this.polyline = make.polyline;
        this.radiusInMeters = make.radiusInMeters;
    }

    public GeoCorridor(@NonNull List<GeoCoordinates> list, int i) {
        GeoCorridor make = make(list, i);
        this.polyline = make.polyline;
        this.radiusInMeters = make.radiusInMeters;
    }

    private static native GeoCorridor make(@NonNull List<GeoCoordinates> list, double d);

    private static native GeoCorridor make(@NonNull List<GeoCoordinates> list, int i);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GeoCorridor)) {
            return false;
        }
        GeoCorridor geoCorridor = (GeoCorridor) obj;
        return Objects.equals(this.polyline, geoCorridor.polyline) && this.radiusInMeters == geoCorridor.radiusInMeters;
    }

    public int hashCode() {
        List<GeoCoordinates> list = this.polyline;
        return (((list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31) + this.radiusInMeters;
    }
}
