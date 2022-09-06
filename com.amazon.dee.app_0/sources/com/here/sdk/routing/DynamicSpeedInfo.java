package com.here.sdk.routing;

import com.facebook.imageutils.JfifUtil;
/* loaded from: classes3.dex */
final class DynamicSpeedInfo {
    double baseSpeedInMetersPerSecond;
    double trafficSpeedInMetersPerSecond;
    int turnTimeInSeconds;

    DynamicSpeedInfo(double d, double d2, int i) {
        this.baseSpeedInMetersPerSecond = d;
        this.trafficSpeedInMetersPerSecond = d2;
        this.turnTimeInSeconds = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DynamicSpeedInfo)) {
            return false;
        }
        DynamicSpeedInfo dynamicSpeedInfo = (DynamicSpeedInfo) obj;
        return Double.compare(this.baseSpeedInMetersPerSecond, dynamicSpeedInfo.baseSpeedInMetersPerSecond) == 0 && Double.compare(this.trafficSpeedInMetersPerSecond, dynamicSpeedInfo.trafficSpeedInMetersPerSecond) == 0 && this.turnTimeInSeconds == dynamicSpeedInfo.turnTimeInSeconds;
    }

    public int hashCode() {
        return ((((((int) (Double.doubleToLongBits(this.baseSpeedInMetersPerSecond) ^ (Double.doubleToLongBits(this.baseSpeedInMetersPerSecond) >>> 32))) + JfifUtil.MARKER_EOI) * 31) + ((int) (Double.doubleToLongBits(this.trafficSpeedInMetersPerSecond) ^ (Double.doubleToLongBits(this.trafficSpeedInMetersPerSecond) >>> 32)))) * 31) + this.turnTimeInSeconds;
    }
}
