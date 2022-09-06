package com.here.sdk.routing;

import com.facebook.imageutils.JfifUtil;
/* loaded from: classes3.dex */
final class TbtManeuver {
    int distanceToNextManeuverInMeters;
    int linkIndex;
    long sectionIndex;

    TbtManeuver(int i, long j, int i2) {
        this.distanceToNextManeuverInMeters = i;
        this.sectionIndex = j;
        this.linkIndex = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TbtManeuver)) {
            return false;
        }
        TbtManeuver tbtManeuver = (TbtManeuver) obj;
        return this.distanceToNextManeuverInMeters == tbtManeuver.distanceToNextManeuverInMeters && this.sectionIndex == tbtManeuver.sectionIndex && this.linkIndex == tbtManeuver.linkIndex;
    }

    public int hashCode() {
        long j = this.sectionIndex;
        return ((((this.distanceToNextManeuverInMeters + JfifUtil.MARKER_EOI) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.linkIndex;
    }
}
