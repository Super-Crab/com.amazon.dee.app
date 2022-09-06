package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.CountryCode;
import com.here.sdk.core.GeoBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class AvoidanceOptions {
    @NonNull
    public List<GeoBox> avoidAreas;
    @NonNull
    public List<CountryCode> countries;
    @NonNull
    public List<RoadFeatures> roadFeatures;

    public AvoidanceOptions() {
        this.roadFeatures = new ArrayList();
        this.countries = new ArrayList();
        this.avoidAreas = new ArrayList();
    }

    public AvoidanceOptions(@NonNull List<RoadFeatures> list, @NonNull List<CountryCode> list2, @NonNull List<GeoBox> list3) {
        this.roadFeatures = list;
        this.countries = list2;
        this.avoidAreas = list3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AvoidanceOptions)) {
            return false;
        }
        AvoidanceOptions avoidanceOptions = (AvoidanceOptions) obj;
        return Objects.equals(this.roadFeatures, avoidanceOptions.roadFeatures) && Objects.equals(this.countries, avoidanceOptions.countries) && Objects.equals(this.avoidAreas, avoidanceOptions.avoidAreas);
    }

    public int hashCode() {
        List<RoadFeatures> list = this.roadFeatures;
        int i = 0;
        int hashCode = ((list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        List<CountryCode> list2 = this.countries;
        int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<GeoBox> list3 = this.avoidAreas;
        if (list3 != null) {
            i = list3.hashCode();
        }
        return hashCode2 + i;
    }
}
