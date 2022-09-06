package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
/* loaded from: classes3.dex */
public final class DashPattern {
    public final double firstDashLength;
    public final double firstGapLength;

    public DashPattern(double d) {
        DashPattern make = make(d);
        this.firstGapLength = make.firstGapLength;
        this.firstDashLength = make.firstDashLength;
    }

    public DashPattern(double d, double d2) {
        DashPattern make = make(d, d2);
        this.firstGapLength = make.firstGapLength;
        this.firstDashLength = make.firstDashLength;
    }

    private static native DashPattern make(double d);

    private static native DashPattern make(double d, double d2);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DashPattern)) {
            return false;
        }
        DashPattern dashPattern = (DashPattern) obj;
        return Double.compare(this.firstGapLength, dashPattern.firstGapLength) == 0 && Double.compare(this.firstDashLength, dashPattern.firstDashLength) == 0;
    }

    @NonNull
    native List<Double> getDashArray();

    public int hashCode() {
        return ((((int) (Double.doubleToLongBits(this.firstGapLength) ^ (Double.doubleToLongBits(this.firstGapLength) >>> 32))) + JfifUtil.MARKER_EOI) * 31) + ((int) (Double.doubleToLongBits(this.firstDashLength) ^ (Double.doubleToLongBits(this.firstDashLength) >>> 32)));
    }
}
